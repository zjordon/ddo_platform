/**
 * 
 */
package com.jason.ddoMsg.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.ChannelRequest;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.task.handler.ChannelRequestHandler;

/**
 * 处理异常退出的请求任务
 * 从数据库中获取异常中断的任务进行处理，同时只有一个线程进行处理
 * @author jasonzhang
 *
 */
public class ExcepInterRequestTask extends AbstractTask {
	private static final Logger logger = Logger.getLogger(ExcepInterRequestTask.class);

	protected int executeTask() {
		int nums = 0;
		logger.debug("start execute ExcepInterRequestTask");
		List<ChannelRequest> channelRequestList = null;
		try {
			channelRequestList = CacheManager.getInstance().getChannelRequestCache().getUnexpectedEndRequests(100);
		} catch (CacheException e) {
			logger.error("exception when execute ExcepInterRequestTask", e);
		}
		if (channelRequestList != null && !channelRequestList.isEmpty()) {
			ChannelRequestHandler requestHandler = ChannelRequestHandler.getInstance();
			for (ChannelRequest request : channelRequestList) {
				if (!super.stop) {
					requestHandler.handle(request);
				} else {
					logger.info("the task is stop when running");
					break;
				}
			}
			nums =  channelRequestList.size();
		}
		logger.debug("end execute ExcepInterRequestTask");
		return nums;
	}
}
