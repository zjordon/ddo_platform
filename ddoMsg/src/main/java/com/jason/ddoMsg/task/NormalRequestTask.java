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
	
	private int queueIdx = 0;
	
	public NormalRequestTask(int queueIdx) {
		this.queueIdx = queueIdx;
	}

	protected int executeTask() {
		int nums = 0;
		logger.info("start execute NormalRequestTask");
		List<ChannelRequest> requestList = ChannelRequestQueue.getInstance().getRequests(this.queueIdx, 100);
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
		
		logger.info("end execute NormalRequestTask");
		return nums;
	}
}
