/**
 * 
 */
package com.jason.ddoMsg.externalInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.channel.BillBusiness;
import com.jason.ddoMsg.bean.channel.ChannelBusiness;
import com.jason.ddoMsg.bean.channel.ChannelDayLimit;
import com.jason.ddoMsg.bean.channel.ChannelMonthLimit;
import com.jason.ddoMsg.bean.channel.ChannelUser;
import com.jason.ddoMsg.bean.event.Event;
import com.jason.ddoMsg.bean.msg.ChannelRequest;
import com.jason.ddoMsg.bean.msg.RequestMsisdn;
import com.jason.ddoMsg.cache.BlackListCache;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ElementNotFoundException;
import com.jason.ddoMsg.event.EventException;
import com.jason.ddoMsg.event.EventManager;
import com.jason.ddoMsg.queue.ChannelRequestQueue;
import com.jason.ddoMsg.util.DateUtil;
import com.jason.ddoMsg.util.UUIDGenerator;

/**
 * 接收渠道请求接口
 * @author jasonzhang
 *
 */
public class ChannelRequestInterface {
	private static final Logger logger = Logger.getLogger(ChannelRequestInterface.class);
	
	private final static ChannelRequestInterface instance = new ChannelRequestInterface();
	
	private ChannelRequestInterface(){}
	
	public final static ChannelRequestInterface getInstance() {
		return instance;
	}

	/**
	 * 接收请求
	 * @param username 用户名
	 * @param pass 密码
	 * @param msisdn 手机号
	 * @param instruct 发送指令
	 * @param productId 产品id
	 * @param dstTime 定时时间
	 * @param sourceType 来源类型
	 * @return
	 */
	public String receiveRequest(String username, String pass, String msisdn, String instruct, String productId, String dstTime, int sourceType) {
		String ret = "";
		int retState = 0;
		int processResult = 1;
		ChannelUser channelUser = null;
		Date currentDate = new Date();
		ChannelRequest channelRequest = this.getChannelRequest(username, pass, msisdn, instruct, productId, dstTime, sourceType, currentDate);
		if (this.isStopReceiveRequest()) {
			retState = 500;
			ret = "500";
		} else {
			if (this.isParamInvalid(username, pass, msisdn, instruct, productId, dstTime, sourceType)) {
				retState = 12;
				ret = "12";
			} else {
				channelUser = this.getChannelUser(username);
				if (channelUser != null) {
					if (this.isPasswordInvalid(channelUser, pass)) {
						//密码不正确
						retState = -1;
						ret = "-1";
					} else {
						List<Long> msisdns = this.filterBlackList(msisdn);
						if (this.isValidMsisdnEmpty(msisdns)) {
							//有效手机号码为空(可能号码在黑名单里面)
							retState = 6;
							ret = "6";
						} else {
							this.setRequestMsisdns(channelRequest, msisdns);
							if (this.isUserDisable(channelUser)) {
								//未开通接口提交权限或用户已被禁用
								retState = 9;
								ret = "9";
							} else {
								if (this.isExceedMaxNum(msisdns)) {
									//发送号码过多
									retState = 10;
									ret = "10";
								} else {
									BillBusiness billBusiness = null;
									if (sourceType == 1) {
										//来自渠道，从产品id中获取计费业务
										billBusiness = this.getBillBusinessByProductId(productId);
										if (billBusiness == null) {
											//产品ID异常
											retState = 11;
											ret = "11";
										}
									} else {
										//来自特服号，从指令中获取计费业务
										ChannelBusiness channelBusiness = this.getChannelBusiness(instruct);
										if (channelBusiness == null) {
											//发送指令无效
											retState = 7;
											ret = "7";
										} else {
											billBusiness = this.getBillBusinessById(channelBusiness.getBillBusinessId());
											if (billBusiness == null) {
												//根据指令可以找到渠道业务，但找不到计费业务，系统有问题，返回系统内部错误
												retState = 500;
												ret = "500";
												processResult = 7;
											} else {
												//回填渠道计费业务编码
												channelRequest.setProductId(billBusiness.getChannelBillCode());
											}
										}
									}
									if (retState == 0) {
										//指令或产品id都正常，继续验证日限额或月限额是否超过限制
										ChannelMonthLimit channelMonthLimit = this.getChannelMonthLimit(channelUser.getChannelId());
										if (channelMonthLimit == null) {
											//根据渠道id找不到对应的月限额，系统应该出错了，返回内部错误
											retState = 500;
											ret = "500";
											processResult = 4;
										} else {
											//计算本次的费用
											long totalPrice = this.calculateCost(msisdns.size(), billBusiness.getPrice().longValue());
											if (this.isExceedMonthLimit(totalPrice, channelMonthLimit)) {
												//渠道达到月消费限额
												retState = 4;
												ret = "4";
												processResult = 8;
											} else {
												ChannelDayLimit channelDayLimit = this.getChannelDayLimit(channelUser.getChannelId());
												if (channelDayLimit == null) {
													//根据渠道id找不到对应的日限额，系统应该出错了，返回内部错误
													retState = 500;
													ret = "500";
													processResult = 5;
												} else {
													if (this.isExceedDayLimit(totalPrice, channelDayLimit)) {
														//渠道达到日消费限额
														retState = 4;
														ret = "4";
														processResult = 9;
													} else {
														//扣除渠道的日消费额和月消费额
														try {
															this.deductCost(totalPrice, channelUser.getChannelId(), channelRequest.getId(), currentDate);
														} catch (CacheException e) {
															//扣除费用时了出错，系统应该有问题，返回内部错误
															retState = 500;
															ret = "500";
															processResult = 6;
														}
													}
												}
												
											}
										}
									}
									
								}
							}
						}
					}
				} else {
					//用户名不存在
					retState = -1;
					ret = "-1";
				}
			}
			if (retState == 0) {
				//处理过程中无其它异常，处理成功
				retState = 1;
				ret = "1," + channelRequest.getId();
				
			}
			channelRequest.setProcessResult(processResult);
			if (channelUser != null) {
				channelRequest.setChannelId(channelUser.getChannelId());
			}
			
			channelRequest.setReturnState(new Integer(retState));
			this.addRequestToQueue(channelRequest);
		}
		
		return ret;
	}
	
	private ChannelRequest getChannelRequest(String username, String pass, String msisdn, String instruct, String productId, String dstTime, int sourceType, Date currentDate) {
		ChannelRequest channelRequest = new ChannelRequest();
		channelRequest.setId((new UUIDGenerator()).generate());
		channelRequest.setUsername(username);
		channelRequest.setPassword(pass);
		channelRequest.setContent(instruct);
		channelRequest.setProductId(productId);
		channelRequest.setSourceType(new Integer(sourceType));
		channelRequest.setRequestTime(currentDate);
		//初始状态即为正在处理
		channelRequest.setState(new Integer(1));
		return channelRequest;
	}
	/**
	 * 是否处于停止接收请求状态，用于更新部署或其它需要停止接收渠道请求的时候用
	 * @return
	 */
	private boolean isStopReceiveRequest() {
		return CacheManager.getInstance().getConfigCache().isStopAll();
	}
	
	/**
	 * 验证参数是否异常
	 * @param username
	 * @param pass
	 * @param msisdn
	 * @param instruct
	 * @param productId
	 * @param dstTime
	 * @param sourceType
	 * @return
	 */
	private boolean isParamInvalid(String username, String pass, String msisdn, String instruct, String productId, String dstTime, int sourceType) {
		Date dstTimeDate = null;
		if (StringUtils.isBlank(username) || StringUtils.isBlank(pass) || StringUtils.isBlank(msisdn)) {
			//验证用户名，密码，手机号码是否为空
			return true;
		} else if (StringUtils.isNotBlank(dstTime)) {
			//如果定时发送时间不为空，则验证时间格式是否正确
			dstTimeDate = DateUtil.parseDate(dstTime);
			if (dstTimeDate == null) {
				return true;
			}
		} else if (sourceType == 1) {
			//如果来自渠道则验证产品id是否填写
			if (StringUtils.isBlank(productId)) {
				return true;
			}
		} else if (sourceType == 2) {
			if (StringUtils.isBlank(instruct)) {
				return true;
			}
		}
		return false;
	}
	
	private ChannelUser getChannelUser(String username) {
		ChannelUser channelUser = null;
		try {
			channelUser = CacheManager.getInstance().getChannelUserCache().getChannelUserByUsername(username);
		} catch (ElementNotFoundException e) {
			logger.warn("not found the channelUser with username " + username);
		}
		return channelUser;
	}
	/**
	 * 验证密码是否正确
	 * @param channelUser
	 * @param pass
	 * @return
	 */
	private boolean isPasswordInvalid(ChannelUser channelUser, String pass) {
		return !channelUser.getPassword().equalsIgnoreCase(pass);
	}
	/**
	 * 黑名单过滤
	 * @param msisdnParam
	 * @return
	 */
	private List<Long> filterBlackList(String msisdnParam) {
		List<Long> msisdnList = new ArrayList<Long>();
		String[] msisdnArray = StringUtils.split(msisdnParam, ',');
		BlackListCache blackListCache = CacheManager.getInstance().getBlackListCache();
		long msisdn = 0L;
		NumberFormatException expactException = null;
		for (String msisdnStr : msisdnArray) {
			expactException = null;
			try {
				msisdn = Long.parseLong(msisdnStr.trim());
			} catch (NumberFormatException e) {
				expactException = e;
			}
			if (expactException != null) {
				logger.warn("the msisdn is not numberformat with " + msisdnStr);
				continue;
			}
			if (!blackListCache.isExists(msisdn)) {
				msisdnList.add(new Long(msisdn));
			} else {
				logger.info("the msisdn is in blacklist with " + msisdnStr);
			}
		}
		return msisdnList;
	}
	/**
	 * 有效手机号码是否为空
	 * @param msisdns
	 * @return
	 */
	private boolean isValidMsisdnEmpty(List<Long> msisdns) {
		return msisdns.isEmpty();
	}
	/**
	 * 验证用户是滞已经被禁用
	 * @param channelUser
	 * @return
	 */
	private boolean isUserDisable(ChannelUser channelUser) {
		return channelUser.getState().intValue() == 0;
	}
	/**
	 * 发送号码条数是否超过限制
	 * @param msisdns
	 * @return
	 */
	private boolean isExceedMaxNum(List<Long> msisdns) {
		return msisdns.size() > 2000;
	}
	/**
	 * 根据产品id获取对应计费业务
	 * @param productId
	 * @return
	 */
	private BillBusiness getBillBusinessByProductId(String productId) {
		BillBusiness billBusiness = null;
		try {
			billBusiness = CacheManager.getInstance().getBillBusinessCache().getByChannelBillCode(productId);
		} catch (ElementNotFoundException e) {
			logger.warn("the BillBusiness not found in cache with productId " + productId);
		}
		return billBusiness;
	}
	private BillBusiness getBillBusinessById(String id) {
		BillBusiness billBusiness = null;
		try {
			billBusiness = CacheManager.getInstance().getBillBusinessCache().getBillBusiness(id);
		} catch (ElementNotFoundException e) {
			logger.warn("the BillBusiness not found in cache with id " + id);
		}
		return billBusiness;
	}
	/**
	 * 根据产品指令获取对应的渠道业务
	 * @param instruct
	 * @return
	 */
	private ChannelBusiness getChannelBusiness(String instruct) {
		ChannelBusiness channelBusiness = null;
		try {
			channelBusiness = CacheManager.getInstance().getChannelBusinessCache().getChannelBusinessByInstruct(instruct);
		} catch (ElementNotFoundException e) {
			logger.warn("the ChannelBusiness not found in cache with " + instruct);
		}
		return channelBusiness;
	}
	
	private ChannelMonthLimit getChannelMonthLimit(String channelId) {
		ChannelMonthLimit channelMonthLimit = null;
		try {
			channelMonthLimit = CacheManager.getInstance().getChannelLimitCache().getChannelMonthLimit(channelId);
		} catch (ElementNotFoundException e) {
			logger.warn("the ChannelMonthLimit not found in cache with channelId " + channelId);
		}
		return channelMonthLimit;
	}
	
	private ChannelDayLimit getChannelDayLimit(String channelId) {
		ChannelDayLimit channelDayLimit = null;
		try {
			channelDayLimit = CacheManager.getInstance().getChannelLimitCache().getChannelDayLimit(channelId);
		} catch (ElementNotFoundException e) {
			logger.warn("the ChannelDayLimit not found in cache with channelId " + channelId);
		}
		return channelDayLimit;
	}
	private long calculateCost(int msisdnNum, long unitPrice) {
		return unitPrice*msisdnNum;
	}
	/**
	 * 是否超过月限额
	 * @param productId
	 * @param instruct
	 * @param sourceType
	 * @param msisdns
	 * @param channelMonthLimit
	 * @return
	 */
	private boolean isExceedMonthLimit(long totalPrice, ChannelMonthLimit channelMonthLimit) {
		return this.isExceedLimit(totalPrice, channelMonthLimit.getLimitAmount());
	}
	/**
	 * 是否超过日限额
	 * @param productId
	 * @param instruct
	 * @param sourceType
	 * @param msisdns
	 * @param channelDayLimit
	 * @return
	 */
	private boolean isExceedDayLimit(long totalPrice, ChannelDayLimit channelDayLimit) {
		return this.isExceedLimit(totalPrice, channelDayLimit.getLimitAmount());
	}
	
	private boolean isExceedLimit(long totalPrice, long limit) {
		return totalPrice > limit;
	}
	/**
	 * 扣除渠道的日限额和月限额
	 * @param totalPrice
	 * @param channelId
	 * @param requestId
	 * @throws CacheException
	 */
	private void deductCost(long totalPrice, String channelId, String requestId, Date currentDate) throws CacheException {
		int day = DateUtil.getDateIntNum(currentDate);
		int month = day/100;
		CacheManager.getInstance().getChannelLimitCache().deductChannelLimit(channelId, day, month, totalPrice, requestId, true, false);
		//产生扣费事件，真正在从数据库中进行扣除
		Event event = new Event();
		event.setId((new UUIDGenerator()).generate());
		event.setCreateDate(new Date());
		event.setEventId("DeductChannelLimitEvent");
		StringBuilder builder = new StringBuilder();
		builder.append("channelId:").append(channelId).append(",day:").append(day).append(",month:").append(month);
		builder.append(",amount:").append(totalPrice).append(",requestId:").append(requestId);
		event.setParam(builder.toString());
		try {
			EventManager.getInstance().addEvent(event);
		} catch (EventException e) {
			logger.error("error with deductCost add the event", e);
		}
	}
	
	private void setRequestMsisdns(ChannelRequest channelRequest, List<Long> msisdns) {
		List<RequestMsisdn> requestMsisdns = new ArrayList<RequestMsisdn>(msisdns.size());
		for (Long msisdn : msisdns) {
			RequestMsisdn requestMsisdn = new RequestMsisdn();
			requestMsisdn.setId((new UUIDGenerator()).generate());
			requestMsisdn.setMsisdn(msisdn);
			requestMsisdn.setRequestId(channelRequest.getId());
			requestMsisdns.add(requestMsisdn);
		}
		channelRequest.setRequestMsisdns(requestMsisdns);
	}
	/**
	 * 把请求加入队列
	 * @param channelRequest
	 */
	private void addRequestToQueue(ChannelRequest channelRequest) {
		ChannelRequestQueue.getInstance().addRequest(channelRequest);
	}
}
