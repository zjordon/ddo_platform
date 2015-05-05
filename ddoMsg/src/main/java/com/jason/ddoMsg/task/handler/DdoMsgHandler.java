/**
 * 
 */
package com.jason.ddoMsg.task.handler;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.channel.Channel;
import com.jason.ddoMsg.bean.channel.ProviceCloseState;
import com.jason.ddoMsg.bean.msg.DdoMsg;
import com.jason.ddoMsg.bean.msg.DdoMsgResult;
import com.jason.ddoMsg.bean.statistics.SendResultRecord;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ElementNotFoundException;
import com.jason.ddoMsg.externalInterface.DdoMsgInterface;
import com.jason.ddoMsg.queue.StatisticsQueue;
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
			boolean needRepeatSend = false;
			ddoMsg.setSendTime(new Date());
			DdoMsgResult result = DdoMsgInterface.getInstance().submitDdoMsg(
					ddoMsg);
			// 判断是否提交成功
			if (result.getSendResult() == 1) {
				// 提交成功
				// 判断是否需要重发
				if (this.isNeedRepeatSend(result.getReturnMsgCode())) {
					// 需要重发
					needRepeatSend = true;
				} else if (StringUtils.isNotBlank(result.getTransationId())) {
					// 不需要重发,回填ddo平台返回的事务id
					ddoMsg.setTransationId(result.getTransationId());
					//加入统计队列，发送结果为发送成功
					this.addStatisticsQueue(ddoMsg.getId(), new Integer(1));
				} else {
					//加入统计队列，发送结果为发送失败
					this.addStatisticsQueue(ddoMsg.getId(), new Integer(0));
				}
			} else {
				// 提交失败
				needRepeatSend = true;
			}
			Date responseDate = new Date();
			if (!StringUtils.isNotBlank(result.getTransationId())) {

				try {
					CacheManager
							.getInstance()
							.getDdoMsgCache()
							.updateMsgRetMsgCode(ddoMsg.getId(),
									result.getReturnMsgCode(),
									result.getSendResult(), ddoMsg.getSendTime(),
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
									result.getSendResult(), ddoMsg.getSendTime(), responseDate);
				} catch (CacheException e) {
					logger.error("excpetion when handle ddo msg", e);
				}

				// 判断是否需要上行到渠道
				if (this.isNeedUpChannel(channel, sourceType)) {
					// 需要上行到渠道
					UpChannelHandler.getInstance().handle(ddoMsg, instruct,
							channel.getUpUrl(), null);
				}
			}
			ddoMsg.setReturnMsgCode(result.getReturnMsgCode());
			ddoMsg.setSendResult(result.getSendResult());
		} else {
			ddoMsg.setSendResult(new Integer(2));
			// 只更新发送状态

			try {
				CacheManager.getInstance().getDdoMsgCache()
						.updateMsgSendResult(ddoMsg.getId(), 2, false);
			} catch (CacheException e) {
				logger.error("excpetion when handle ddo msg", e);
			}
			//加入统计队列
			this.addStatisticsQueue(ddoMsg.getId(), new Integer(0));

		}

	}
	
	private void addStatisticsQueue(String ddoMsgId, Integer sendResult) {
		//加入统计队列
		SendResultRecord sendResultRecord = new SendResultRecord();
		sendResultRecord.setId((new UUIDGenerator()).generate());
		sendResultRecord.setDdoMsgId(ddoMsgId);
		//村记发送结果
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
		return (sourceType == 2 && channel.getUpUrl() != null);
	}
}
