/**
 * 
 */
package com.jason.ddoMsg.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.channel.Channel;
import com.jason.ddoMsg.bean.msg.ChannelRequest;
import com.jason.ddoMsg.bean.msg.DdoMsg;
import com.jason.ddoMsg.bean.msg.UpChannelRecord;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ElementNotFoundException;
import com.jason.ddoMsg.task.handler.UpChannelHandler;

/**
 * 重新上行渠道任务(重新上行任务)
 * 从数据库中获取需要重新上行到渠道的信息进行处理，同时只有一个线程在处理
 * @author jasonzhang
 *
 */
public class RepeatUpChannelTask extends AbstractTask {
	private static final Logger logger = Logger.getLogger(RepeatUpChannelTask.class);

	protected int executeTask() {
		int nums = 0;
		logger.debug("start execute RepeatUpChannelTask");
		List<UpChannelRecord> records = null;
		try {
			records = CacheManager.getInstance().getUpChannelRecordCache().getNeedUpChannel(100);
		} catch (CacheException e) {
			logger.error("exception when execute RepeatUpChannelTask", e);
		}
		if (records != null && !records.isEmpty()) {
			UpChannelHandler upChannelHandler = UpChannelHandler.getInstance();
			for (UpChannelRecord record : records) {
				DdoMsg ddoMsg = this.getDdoMsg(record.getDdoMsgId());
				Channel channel = this.getChannel(ddoMsg.getChannelId());
				ChannelRequest request = this.getChannelRequest(ddoMsg.getRequestId());
				if (ddoMsg != null && channel != null && request != null) {
					if (this.isNeedUpChannel(channel, request.getSourceType().intValue())) {
						upChannelHandler.handle(ddoMsg, request.getContent(), channel.getUpUrl(), record.getId());
					} else {
						try {
							CacheManager.getInstance().getUpChannelRecordCache().updateNoRepeat(record.getId());
						} catch (CacheException e) {
							logger.error("exception when execute RepeatUpChannelTask", e);
						}
					}
					
				} else {
					logger.warn("the ddoMsg or channel or request is null channel is " + channel + ", request is " + request + ", ddoMsg is " + ddoMsg);
				}
				
			}
			nums = records.size();
		}
		logger.debug("end execute RepeatUpChannelTask");
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
	
	private DdoMsg getDdoMsg(String ddoMsgId) {
		DdoMsg ddoMsg = null;
		try {
			ddoMsg = CacheManager.getInstance().getDdoMsgCache().getDdoMsg(ddoMsgId);
		} catch (CacheException e) {
			logger.error("exception when getDdoMsg", e);
		}
		return ddoMsg;
	}
	
	/**
	 * 判断是否需要上行到渠道
	 * @param channel
	 * @return
	 */
	private boolean isNeedUpChannel(Channel channel, int sourceType) {
		return (sourceType == 2 && channel.getUpUrl() != null);
	}
}
