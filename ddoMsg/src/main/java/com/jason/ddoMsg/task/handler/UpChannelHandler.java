/**
 * 
 */
package com.jason.ddoMsg.task.handler;

import java.util.Date;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.DdoMsg;
import com.jason.ddoMsg.bean.msg.DeliverResponse;
import com.jason.ddoMsg.bean.msg.UpChannelRecord;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.externalInterface.ChannelUpInterface;
import com.jason.ddoMsg.util.UUIDGenerator;

/**
 * 上行渠道消息处理
 * @author jasonzhang
 *
 */
public class UpChannelHandler {
	private static final Logger logger = Logger.getLogger(UpChannelHandler.class);
	private final static UpChannelHandler instance = new UpChannelHandler();
	
	private UpChannelHandler() {}
	
	public final static UpChannelHandler getInstance() {
		return instance;
	}

	/**
	 * 上行渠道
	 * @param ddoMsg ddo消息
	 * @param instruct 指令内容
	 * @param upUrl 提交到的url
	 */
	public void handle(DdoMsg ddoMsg, String instruct, String upUrl, String channelRecordId) {
		Date sendTime = new Date();
		DeliverResponse response = ChannelUpInterface.getInstance().deliverMsg(ddoMsg.getRequestId(), ddoMsg.getMsisdn(),instruct, ddoMsg.getReturnMsgCode(), upUrl);
		//判断是否上行成功
		boolean needRepeat = false;
		if (!"ok".equalsIgnoreCase(response.getMsg())) {
			needRepeat = true;
			logger.info(response.getMsg());
		}
		Date responseTime = new Date();
		if (channelRecordId == null) {
			
			UpChannelRecord upChannelRecord = new UpChannelRecord();
			upChannelRecord.setId((new UUIDGenerator()).generate());
			upChannelRecord.setCreateDate(sendTime);
			upChannelRecord.setDdoMsgId(ddoMsg.getId());
			upChannelRecord.setResponseTime(responseTime);
			upChannelRecord.setSendTime(sendTime);
			if (response.getStatusCode() == 200) {
				upChannelRecord.setProcessResult(new Integer(1));
				upChannelRecord.setResultCode(response.getMsg());
			} else {
				upChannelRecord.setProcessResult(new Integer(2));
				upChannelRecord.setResultCode(Integer.toString(response.getStatusCode()));
			}
			
			
			try {
				CacheManager.getInstance().getUpChannelRecordCache().saveUpChannelRecord(upChannelRecord, needRepeat);
			} catch (CacheException e) {
				logger.error("exception when handle upchannel", e);
			}
		} else {
			//属于重发上行记录，只需要更新原记录的返回状态码，发送时间，请求返回时间,是否需要重发
			String resultCode = null;
			if (response.getStatusCode() == 200) {
				resultCode = response.getMsg();
			} else {
				resultCode = Integer.toString(response.getStatusCode());
			}
			try {
				CacheManager.getInstance().getUpChannelRecordCache().updateResultCode(channelRecordId, resultCode, needRepeat, sendTime, responseTime);
			} catch (CacheException e) {
				logger.error("exception when handle upchannel", e);
			}
		}
		
	}
}
