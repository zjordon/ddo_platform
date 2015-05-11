/**
 * 
 */
package com.jason.ddoMsg.task.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.channel.BillBusiness;
import com.jason.ddoMsg.bean.channel.Channel;
import com.jason.ddoMsg.bean.channel.ChannelBusiness;
import com.jason.ddoMsg.bean.msg.ChannelRequest;
import com.jason.ddoMsg.bean.msg.DdoMsg;
import com.jason.ddoMsg.bean.msg.RequestMsisdn;
import com.jason.ddoMsg.bean.statistics.SendRecord;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ElementNotFoundException;
import com.jason.ddoMsg.queue.StatisticsQueue;
import com.jason.ddoMsg.util.DateUtil;
import com.jason.ddoMsg.util.DmMobile;
import com.jason.ddoMsg.util.UUIDGenerator;

/**
 * 渠道请求处理
 * 
 * @author jasonzhang
 *
 */
public class ChannelRequestHandler {
	private static final Logger logger = Logger
			.getLogger(ChannelRequestHandler.class);
	private final static ChannelRequestHandler instance = new ChannelRequestHandler();

	private ChannelRequestHandler() {
	}

	public final static ChannelRequestHandler getInstance() {
		return instance;
	}

	public void handle(ChannelRequest channelRequest) {
		this.handle(channelRequest, false);
	}

	/**
	 * 处理渠道请求
	 * 
	 * @param channelRequet
	 * @param isTiming
	 *            是否定时发送的请求
	 */
	public void handle(ChannelRequest channelRequest, boolean isTiming) {
		if (isTiming) {
			channelRequest.setBeginTime(new Date());
			Channel channel = this
					.getChannel(channelRequest.getChannelId());
			ChannelBusiness channelBusiness = this
					.getChannelBusiness(channelRequest
							.getSourceType().intValue(),
							channelRequest.getContent(),
							channelRequest.getProductId(),
							channelRequest.getChannelId());
			this.processChannelRequest(channelRequest, channelBusiness, channel);
			channelRequest.setEndTime(new Date());
			this.updateRequestState(channelRequest);
		} else {
			if (this.isNormal(channelRequest.getReturnState().intValue())) {
				channelRequest.setBeginTime(new Date());
				Channel channel = this
						.getChannel(channelRequest.getChannelId());
				if (channel != null) {
					// 判断渠道是否被关停
					if (this.isChannelClose(channel)) {
						// 对应渠道被关停
						channelRequest.setState(new Integer(2));
						channelRequest.setProcessResult(new Integer(2));
					} else {
						// 渠道状态正常
						// 获取对应的渠道计费业务
						ChannelBusiness channelBusiness = this
								.getChannelBusiness(channelRequest
										.getSourceType().intValue(),
										channelRequest.getContent(),
										channelRequest.getProductId(),
										channelRequest.getChannelId());
						if (channelBusiness != null) {
							// 判断产品是否被关停
							if (this.isProductClose(channelBusiness)) {
								// 产品被关停
								channelRequest.setState(new Integer(2));
								channelRequest.setProcessResult(new Integer(3));
							} else {
								// 产品状态正常
								// 判断是否立即发送
								if (this.isSendImmediately(channelRequest
										.getDstime())) {
									// 立即发送
									// 判断是否异常中断的请求
									if (this.isAbnormalInterrupt(channelRequest)) {
										// 是异常中断的请求
										// 从数据库中获取对应的未发送的消息列表
										List<DdoMsg> notSendDdoMsgList = this
												.getNotSendDdoMsg(channelRequest
														.getId());
										if (notSendDdoMsgList != null) {
											if (notSendDdoMsgList.isEmpty()) {
												this.processChannelRequest(
														channelRequest,
														channelBusiness,
														channel);
											} else {
												this.handlerDdoMsgList(
														channelRequest,
														notSendDdoMsgList,
														channel, channelRequest
																.getContent());

											}
										} else {
											// 获取数据时发生异常
											channelRequest
													.setState(new Integer(2));
											channelRequest
													.setProcessResult(new Integer(
															12));
										}
									} else {
										// 不是异常中断的请求
										this.processChannelRequest(
												channelRequest,
												channelBusiness, channel);
									}
								} else {
									// 定时发送
									// 把状态改为未处理
									channelRequest.setState(new Integer(0));
								}
							}
						} else {
							// 找不到对应的渠道计费业务，应该是系统有问题
							channelRequest.setState(new Integer(2));
							channelRequest.setProcessResult(new Integer(11));

						}
					}
				} else {
					// 根据渠道id找不到对应的渠道，应该是系统问题
					channelRequest.setState(new Integer(2));
					channelRequest.setProcessResult(new Integer(10));

				}
				channelRequest.setEndTime(new Date());
				this.updateRequestState(channelRequest);
			} else {
				// TODO 不是正常请求，是否要特殊处理？
			}
		}
		

	}

	/**
	 * 根据请求返回状态判断请求是否是正常请求
	 * 
	 * @param retCode
	 * @return
	 */
	private boolean isNormal(int retCode) {
		return retCode == 1;
	}

	private Channel getChannel(String channelId) {
		Channel channel = null;
		try {
			channel = CacheManager.getInstance().getChannelCache()
					.getChannel(channelId);
		} catch (ElementNotFoundException e) {
			logger.error("exception when getChannel", e);
		}
		return channel;
	}

	/**
	 * 判断渠道是否被关停
	 * 
	 * @param channel
	 * @return
	 */
	private boolean isChannelClose(Channel channel) {
		return (channel.getState().intValue() == 0 || channel.getCloseState()
				.intValue() == 1);
	}

	private ChannelBusiness getChannelBusiness(int sourceType, String instruct,
			String productId, String channelId) {
		ChannelBusiness chanelBusiness = null;
		if (sourceType == 1) {
			// 来自渠道，根据渠道id和产品id获取渠道计费业务
			// 先根据产品id获取对应的计费业务
			BillBusiness billBusiness;
			try {
				billBusiness = CacheManager.getInstance()
						.getBillBusinessCache().getByChannelBillCode(productId);
				chanelBusiness = CacheManager
						.getInstance()
						.getChannelBusinessCache()
						.getCBByBusinessIdAChannelId(billBusiness.getId(),
								channelId);
			} catch (ElementNotFoundException e) {
				logger.error("exception when getChannelBusiness", e);
			}

		} else {
			// 来自特服号，直接根据指令获取对应的渠道计费业务
			try {
				chanelBusiness = CacheManager.getInstance()
						.getChannelBusinessCache()
						.getChannelBusinessByInstruct(instruct);
			} catch (ElementNotFoundException e) {
				logger.error("exception when getChannelBusiness", e);
			}
		}
		return chanelBusiness;
	}

	/**
	 * 判断产品是否被关停
	 * 
	 * @param channelBusiness
	 * @return
	 */
	private boolean isProductClose(ChannelBusiness channelBusiness) {
		return channelBusiness.getCloseState().intValue() == 1;
	}

	/**
	 * 判断是否立即处理请求
	 * 
	 * @param dstTime
	 * @return
	 */
	private boolean isSendImmediately(Date dstTime) {
		return (dstTime == null || dstTime.getTime() <= System
				.currentTimeMillis());
	}

	/**
	 * 是否异常中断的请求
	 * 
	 * @param channelRequest
	 * @return
	 */
	private boolean isAbnormalInterrupt(ChannelRequest channelRequest) {
		return channelRequest.getState().intValue() == 3;
	}

	private List<DdoMsg> getDdoMsgs(ChannelRequest channelRequest,
			String billBusinessId) {
		List<RequestMsisdn> msisdnList = channelRequest.getRequestMsisdns();
		List<DdoMsg> ddoMsgList = new ArrayList<DdoMsg>(msisdnList.size());
		for (RequestMsisdn requestMsisdn : msisdnList) {
			DdoMsg ddoMsg = new DdoMsg();
			ddoMsg.setId((new UUIDGenerator()).generate());
			ddoMsg.setRequestId(channelRequest.getId());
			ddoMsg.setBillingBusinessId(billBusinessId);
			ddoMsg.setChannelId(channelRequest.getChannelId());
			ddoMsg.setMsisdn(requestMsisdn.getMsisdn());
			ddoMsg.setSendResult(new Integer(0));
			ddoMsg.setCreateDate(new Date());
			//获取手机号码对应归属的省份和地市编码
			DmMobile dmMobile = CacheManager.getInstance().getDmMobileCache().getDmMobile(ddoMsg.getMsisdn().longValue());
			if (dmMobile != null) {
				ddoMsg.setMsisdnCityCode(Integer.toString(dmMobile.cityCode));
				ddoMsg.setMsisdnProvinceCode(Integer.toString(dmMobile.provinceCode));
			}
			
			ddoMsgList.add(ddoMsg);
		}
		return ddoMsgList;
	}

	private void saveDdoMsgList(List<DdoMsg> ddoMsgList) {
		try {
			CacheManager.getInstance().getDdoMsgCache().saveMsgList(ddoMsgList);
		} catch (CacheException e) {
			logger.error("exception when saveDdoMsgList", e);
		}
	}

	private List<DdoMsg> getNotSendDdoMsg(String requestId) {
		List<DdoMsg> ddoMsgList = null;
		try {
			ddoMsgList = CacheManager.getInstance().getDdoMsgCache()
					.getDdoMsgByReqId(requestId);
		} catch (CacheException e) {
			logger.error("exception when getNotSendDdoMsg", e);
		}
		return ddoMsgList;
	}

	private void processChannelRequest(ChannelRequest channelRequest,
			ChannelBusiness channelBusiness, Channel channel) {
		// 把请求解析成消息
		List<DdoMsg> ddoMsgList = this.getDdoMsgs(channelRequest,
				channelBusiness.getBillBusinessId());
		this.saveDdoMsgList(ddoMsgList);
		this.handlerDdoMsgList(channelRequest, ddoMsgList, channel,
				channelRequest.getContent());
		// 标记请求为处理成功
		channelRequest.setState(new Integer(2));
		channelRequest.setProcessResult(new Integer(1));
	}

	private void handlerDdoMsgList(ChannelRequest channelRequest,
			List<DdoMsg> ddoMsgList, Channel channel, String instruct) {
		DdoMsgHandler ddoMsgHandler = DdoMsgHandler.getInstance();
		for (DdoMsg ddoMsg : ddoMsgList) {
			//把消息加入统计队列 20150423
			SendRecord sendRecord = new SendRecord();
			sendRecord.setId((new UUIDGenerator()).generate());
			sendRecord.setDdoMsgId(ddoMsg.getId());
			sendRecord.setMsisdn(ddoMsg.getMsisdn());
			sendRecord.setChannelId(ddoMsg.getChannelId());
			sendRecord.setBillingBusinessId(ddoMsg.getBillingBusinessId());
			sendRecord.setSendDate(DateUtil.getDateIntNum(ddoMsg.getCreateDate()));
			//统计增加手机号码归属省份字段20150511
			sendRecord.setMsisdnProvinceCode(ddoMsg.getMsisdnProvinceCode());
			StatisticsQueue.getInstance().addSendRecord(sendRecord);
			ddoMsgHandler.handle(ddoMsg, channel, instruct, channelRequest.getSourceType().intValue());
		}
		// 标记请求为处理成功
		channelRequest.setState(new Integer(2));
		channelRequest.setProcessResult(new Integer(1));
	}
	
	private void updateRequestState(ChannelRequest channelRequest) {
		if(channelRequest.getProcessResult() != null) {
			try {
				CacheManager.getInstance().getChannelRequestCache().updateProcessResult(channelRequest.getId(), channelRequest.getState(), channelRequest.getBeginTime(), channelRequest.getEndTime(), channelRequest.getProcessResult());
			} catch (CacheException e) {
				logger.error("exception when getNotSendDdoMsg", e);
			}
		} else {
			try {
				CacheManager.getInstance().getChannelRequestCache().updateState(channelRequest.getId(), channelRequest.getState());
			} catch (CacheException e) {
				logger.error("exception when getNotSendDdoMsg", e);
			}
		}
		
	}

}
