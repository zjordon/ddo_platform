/**
 * 
 */
package com.jason.ddoMsg.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.channel.Channel;
import com.jason.ddoMsg.bean.msg.ChannelRequest;
import com.jason.ddoMsg.bean.msg.DdoMsg;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ElementNotFoundException;
import com.jason.ddoMsg.task.handler.DdoMsgHandler;

/**
 * 重发消息任务
 * 从数据库中获取需要重发的消息进行处理，同时只有一个线程在处理
 * @author jasonzhang
 *
 */
public class RepeatSendDdoMsgTask extends AbstractTask {
	private static final Logger logger = Logger.getLogger(RepeatSendDdoMsgTask.class);
	protected int executeTask() {
		int nums = 0;
		logger.debug("start execute RepeatSendDdoMsgTask");
		List<DdoMsg> ddoMsgList = null;
		try {
			ddoMsgList = CacheManager.getInstance().getDdoMsgCache().getNeedRepeatMsg(100);
		} catch (CacheException e) {
			logger.error("exception when execute RepeatSendDdoMsgTask", e);
		}
		if (ddoMsgList != null && !ddoMsgList.isEmpty()) {
			DdoMsgHandler handler = DdoMsgHandler.getInstance();
			for (DdoMsg repeatMsg : ddoMsgList) {
				Channel channel = this.getChannel(repeatMsg.getChannelId());
				ChannelRequest request = this.getChannelRequest(repeatMsg.getRequestId());
				if (channel != null && request != null) {
					handler.handle(repeatMsg, channel, request.getContent(), request.getSourceType().intValue());
				} else {
					logger.warn("the channel or request is null channel is " + channel + ", request is " + request);
				}
				
			}
			nums =  ddoMsgList.size();
		}
		logger.debug("end execute RepeatSendDdoMsgTask");
		return nums;
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
	
	private ChannelRequest getChannelRequest(String requestId) {
		ChannelRequest request = null;
		try {
			request =  CacheManager.getInstance().getChannelRequestCache().getChannelRequest(requestId);
		} catch (CacheException e) {
			logger.error("exception when getChannelRequest", e);
		}
		return request;
	}
}
