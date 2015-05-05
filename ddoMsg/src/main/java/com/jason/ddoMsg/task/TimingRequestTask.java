/**
 * 
 */
package com.jason.ddoMsg.task;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;





import com.jason.ddoMsg.bean.msg.ChannelRequest;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.task.handler.ChannelRequestHandler;

/**
 * 处理定时发送请求任务
 * 从数据库中获取需要定时发送的请求进行处理，一般同时只有一个线程在处理
 * @author jasonzhang
 *
 */
public class TimingRequestTask extends AbstractTask {
	private static final Logger logger = Logger.getLogger(TimingRequestTask.class);

	protected int executeTask() {
		int nums = 0;
		logger.debug("start execute TimingRequestTask");
		List<ChannelRequest> channelRequestList = null;
		try {
			channelRequestList = CacheManager.getInstance().getChannelRequestCache().getPendingTimingRequests(new Date(), 100);
		} catch (CacheException e) {
			logger.error("exception when execute TimingRequestTask", e);
		}
		if (channelRequestList != null && !channelRequestList.isEmpty()) {
			ChannelRequestHandler requestHandler = ChannelRequestHandler.getInstance();
			for (ChannelRequest request : channelRequestList) {
				requestHandler.handle(request, true);
			}
			nums =  channelRequestList.size();
		}
		logger.debug("end execute TimingRequestTask");
		return nums;
	}
}
