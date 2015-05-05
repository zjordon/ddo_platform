/**
 * 
 */
package com.jason.ddoMsg.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.ChannelRequest;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.queue.ChannelRequestQueue;
import com.jason.ddoMsg.task.handler.ChannelRequestHandler;

/**
 * 处理正常的渠道请求任务
 * 从请求队列获取待处理的任务进行处理，有可能要启多个线程同时处理
 * @author jasonzhang
 *
 */
public class NormalRequestTask extends AbstractTask {
	private static final Logger logger = Logger.getLogger(NormalRequestTask.class);

	protected int executeTask() {
		int nums = 0;
		logger.debug("start execute NormalRequestTask");
		List<ChannelRequest> requestList = ChannelRequestQueue.getInstance().getRequests(100);
		if (!requestList.isEmpty()) {
			try {
				CacheManager.getInstance().getChannelRequestCache().saveChannelRequest(requestList);
			} catch (CacheException e) {
				logger.error("exception when execute ExcepInterRequestTask", e);
			}
			ChannelRequestHandler requestHandler = ChannelRequestHandler.getInstance();
			for (ChannelRequest request : requestList) {
				requestHandler.handle(request);
			}
			nums = requestList.size();
		}
		
		logger.debug("end execute NormalRequestTask");
		return nums;
	}
}
