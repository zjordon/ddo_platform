/**
 * 
 */
package com.jason.ddoMsg.task.handler;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.channel.BillBusiness;
import com.jason.ddoMsg.bean.channel.Channel;
import com.jason.ddoMsg.bean.channel.ProviceCloseState;
import com.jason.ddoMsg.bean.consumption.ConsumeRecord;
import com.jason.ddoMsg.bean.consumption.ConsumeTurnover;
import com.jason.ddoMsg.bean.msg.DdoMsg;
import com.jason.ddoMsg.bean.msg.DdoMsgResult;
import com.jason.ddoMsg.bean.msg.UpChannel;
import com.jason.ddoMsg.bean.statistics.SendResultRecord;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ElementNotFoundException;
import com.jason.ddoMsg.externalInterface.DdoMsgInterface;
import com.jason.ddoMsg.queue.ConsumeTurnoverQueue;
import com.jason.ddoMsg.queue.StatisticsQueue;
import com.jason.ddoMsg.queue.UpChannelQueue;
import com.jason.ddoMsg.util.DateUtil;
import com.jason.ddoMsg.util.UUIDGenerator;

/**
 * Ddo消息处理
 * 
 * @author jasonzhang
 *
 */
public class DdoMsgHandler {
	private static final Logger logger = Logger.getLogger(DdoMsgHandler.class);

	private final static DdoMsgHandler instance = new DdoMsgHandler();

	private DdoMsgHandler() {
	}

	public final static DdoMsgHandler getInstance() {
		return instance;
	}

	/**
	 * ddo消息处理
	 * 
	 * @param ddoMsg
	 * @param channel
	 */
	public void handle(DdoMsg ddoMsg, Channel channel, String instruct,
			int sourceType) {

		if (!this.isProviceClose(ddoMsg.getMsisdnProvinceCode())) {
			BillBusiness billBusiness = this.getBillBusinessById(ddoMsg
					.getBillingBusinessId());
			if (billBusiness != null) {
				ConsumeRecord consumeRecord = this.getConsumeRecord(ddoMsg
						.getMsisdn().longValue());
				boolean isNewConsumeRecord = false;
				if (consumeRecord == null) {
					consumeRecord = new ConsumeRecord();
					consumeRecord.setMsisdn(ddoMsg.getMsisdn().longValue());
					consumeRecord.setNum(1);
					consumeRecord.setAmount(billBusiness.getPrice().intValue());
					isNewConsumeRecord = true;
				} else {
					consumeRecord.increaseAmount(billBusiness.getPrice()
							.intValue());
				}
				if (!this.isOverConsumeLimit(consumeRecord)) {
					boolean needRepeatSend = false;
					ddoMsg.setSendTime(new Date());
					DdoMsgResult result = DdoMsgInterface.getInstance()
							.submitDdoMsg(ddoMsg);
					ddoMsg.setReturnMsgCode(result.getReturnMsgCode());
					ddoMsg.setSendResult(result.getSendResult());
					// 判断是否提交成功
					if (result.getSendResult() == 1) {
						// 提交成功
						// 判断是否需要重发
						if (this.isNeedRepeatSend(result.getReturnMsgCode())) {
							// 需要重发
							needRepeatSend = true;
						} else if (StringUtils.isNotBlank(result
								.getTransationId())) {
							// 不需要重发,回填ddo平台返回的事务id
							ddoMsg.setTransationId(result.getTransationId());
							// 加入统计队列，发送结果为发送成功
							this.addStatisticsQueue(ddoMsg.getId(),
									new Integer(1));
							// 更新缓存中该号码的当月消费金额和消费次数
							try {
								if (isNewConsumeRecord) {
									// 如果是新记录，则直接保存新记录

									CacheManager
											.getInstance()
											.getConsumeCache()
											.saveConsume(
													consumeRecord.getMsisdn(),
													consumeRecord.getAmount());

								} else {
									// 如果是旧记录 ，则更新
									CacheManager
											.getInstance()
											.getConsumeCache()
											.increaseConsume(
													consumeRecord.getMsisdn(),
													billBusiness.getPrice()
															.intValue());
								}
							} catch (CacheException e) {
								logger.error("excpetion when handle ddo msg", e);
							}
							//加入消费队列
							ConsumeTurnover record = new ConsumeTurnover();
							record.setId((new UUIDGenerator()).generate());
							record.setAmount(billBusiness.getPrice().intValue());
							record.setCreateDate(DateUtil.getDateIntNum(ddoMsg.getCreateDate()));
							record.setMsisdn(ddoMsg.getMsisdn().longValue());
							ConsumeTurnoverQueue.getInstnace().addConsumeTurnover(record);
						} else {
							// 加入统计队列，发送结果为发送失败
							this.addStatisticsQueue(ddoMsg.getId(),
									new Integer(0));
						}
					} else {
						// 提交失败
						needRepeatSend = true;
					}
					Date responseDate = new Date();
					if (StringUtils.isBlank(result.getTransationId())) {

						try {
							CacheManager
									.getInstance()
									.getDdoMsgCache()
									.updateMsgRetMsgCode(ddoMsg.getId(),
											result.getReturnMsgCode(),
											result.getSendResult(),
											ddoMsg.getSendTime(),
											needRepeatSend, responseDate);
						} catch (CacheException e) {
							logger.error("excpetion when handle ddo msg", e);
						}

					} else {

						try {
							CacheManager
									.getInstance()
									.getDdoMsgCache()
									.updateMsgTransationId(ddoMsg.getId(),
											result.getTransationId(),
											result.getReturnMsgCode(),
											result.getSendResult(),
											ddoMsg.getSendTime(), responseDate);
						} catch (CacheException e) {
							logger.error("excpetion when handle ddo msg", e);
						}

						
					}
					// 判断是否需要上行到渠道
					if (this.isNeedUpChannel(channel, sourceType) && !this.isNeedRepeatSend(result.getReturnMsgCode())) {
						// 需要上行到渠道
//						UpChannelHandler.getInstance().handle(ddoMsg,
//								instruct, channel.getUpUrl(), null);
						//加入到队列不直接同步到渠道，采用异步的方式同步到渠道 20150608
						UpChannelQueue.getInstance().addUpChannel(new UpChannel(ddoMsg, instruct, channel.getUpUrl()));
					}
					
				} else {
					// 已经超过限额
					this.updateSendResultFail(ddoMsg.getId(), 5);
				}

			} else {
				// 找不到对应的计费业务，一般不可能出现，如果出现，则作标记
				this.updateSendResultFail(ddoMsg.getId(), 6);
			}

		} else {
			// 只更新发送状态
			this.updateSendResultFail(ddoMsg.getId(), 2);

		}

	}

	private void updateSendResultFail(String id, int sendResult) {
		try {
			CacheManager.getInstance().getDdoMsgCache()
					.updateMsgSendResult(id, sendResult, false);
		} catch (CacheException e) {
			logger.error("excpetion when handle ddo msg", e);
		}
		// 加入统计队列
		this.addStatisticsQueue(id, new Integer(0));
	}

	private void addStatisticsQueue(String ddoMsgId, Integer sendResult) {
		// 加入统计队列
		SendResultRecord sendResultRecord = new SendResultRecord();
		sendResultRecord.setId((new UUIDGenerator()).generate());
		sendResultRecord.setDdoMsgId(ddoMsgId);
		// 村记发送结果
		sendResultRecord.setSendResult(sendResult);
		StatisticsQueue.getInstance().addSendResultRecord(sendResultRecord);
	}

	/**
	 * 对应省份是否被关停
	 * 
	 * @param proviceCode
	 * @return
	 */
	private boolean isProviceClose(String proviceCode) {
		boolean flag = true;
		try {
			ProviceCloseState proviceCloseState = CacheManager.getInstance()
					.getProviceCloseStateCache().getByProviceCode(proviceCode);
			flag = (proviceCloseState.getCloseState().intValue() == 1) ? true
					: false;
		} catch (ElementNotFoundException e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 根据ddo平台返回的消息编码判断是否需要重发
	 * 
	 * @param returnMsgCode
	 * @return
	 */
	private boolean isNeedRepeatSend(String returnMsgCode) {
		// 目前只有ddo平台返回系统内部错误的状态时才需要重发
		return "00539999".equals(returnMsgCode);
	}

	/**
	 * 判断是否需要上行到渠道
	 * 
	 * @param channel
	 * @return
	 */
	private boolean isNeedUpChannel(Channel channel, int sourceType) {
		return (sourceType == 2 && StringUtils.isNotBlank(channel.getUpUrl()));
	}

	private ConsumeRecord getConsumeRecord(long msisdn) {
		return CacheManager.getInstance().getConsumeCache()
				.getConsumeRecord(msisdn);
	}

	/**
	 * 号码消费超过限制
	 * 
	 * @return
	 */
	private boolean isOverConsumeLimit(ConsumeRecord record) {
		boolean flag = false;
		if (CacheManager.getInstance().getConfigCache()
				.getMonthDeductionAmountLimit() > 0 && record.getAmount() > CacheManager.getInstance().getConfigCache()
				.getMonthDeductionAmountLimit()) {
			flag = true;
		} else if (CacheManager.getInstance()
				.getConfigCache().getMonthDeductionNumLimit() > 0 && record.getNum() > CacheManager.getInstance()
				.getConfigCache().getMonthDeductionNumLimit()) {
			flag = true;
		}
		return flag;
	}

	private BillBusiness getBillBusinessById(String id) {
		BillBusiness billBusiness = null;
		try {
			billBusiness = CacheManager.getInstance().getBillBusinessCache()
					.getBillBusiness(id);
		} catch (ElementNotFoundException e) {
			logger.warn("the BillBusiness not found in cache with id " + id);
		}
		return billBusiness;
	}
}
