/**
 * 
 */
package com.jason.ddoMsg.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.ChannelRequest;

/**
 * 请求队列
 * @author jasonzhang
 *
 */
public class ChannelRequestQueue {
	private static final Logger logger = Logger.getLogger(ChannelRequestQueue.class);
	
	private final static ChannelRequestQueue instance = new ChannelRequestQueue();
	
	private ConcurrentLinkedQueue<ChannelRequest> queue = null;
	
	private ChannelRequestQueue(){
		queue = new ConcurrentLinkedQueue<ChannelRequest>();
	}

	public final static ChannelRequestQueue getInstance() {
		return instance;
	}
	
	/**
	 * 新增请求
	 * @param request 请求
	 */
	public void addRequest(ChannelRequest request) {
		logger.info("add request to queue");
		this.queue.add(request);
	}
	/**
	 * 获取请求列表
	 * @param nums 获取数目
	 * @return
	 */
	public List<ChannelRequest> getRequests(int nums) {
		List<ChannelRequest> requestList = new ArrayList<ChannelRequest>(nums);
		for (int i=0; i<nums; i++) {
			ChannelRequest request = this.queue.poll();
			if (request != null) {
				requestList.add(request);
			} else {
				break;
			}
			
		}
		logger.info("fetch requestList size is " + requestList.size());
		return requestList;
	}
	
	public int getSize() {
		return this.queue.size();
	}
}
