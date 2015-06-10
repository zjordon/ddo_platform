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
	
//	private ConcurrentLinkedQueue<ChannelRequest> queue = null;
	private int queueNum;
	private volatile int queueSize;
//	private ConcurrentLinkedQueue<ChannelRequest>[] queueArray;
	//java不支持泛型数组，只能创建三个同样类型的队列
	private ConcurrentLinkedQueue<ChannelRequest> queueOne = null;
	private ConcurrentLinkedQueue<ChannelRequest> queueTwo = null;
	private ConcurrentLinkedQueue<ChannelRequest> queueThree = null;
	
	private ChannelRequestQueue(){
//		queue = new ConcurrentLinkedQueue<ChannelRequest>();
		queueNum = 3;
		queueSize = 0;
//		queueArray = Array.newInstance(componentType, length);
//		for (int i=0; i<queueNum;i++) {
//			queueArray[i] = new ConcurrentLinkedQueue<ChannelRequest>();
//		}
		this.queueOne = new ConcurrentLinkedQueue<ChannelRequest>();
		this.queueTwo = new ConcurrentLinkedQueue<ChannelRequest>();
		this.queueThree = new ConcurrentLinkedQueue<ChannelRequest>();
	}

	public final static ChannelRequestQueue getInstance() {
		return instance;
	}
	
	/**
	 * 新增请求
	 * @param request 请求
	 */
	public void addRequest(ChannelRequest request) {
		//logger.info("add request to queue");
		//this.queue.add(request);
		int tempNum = this.queueSize%this.queueNum;
		switch(tempNum) {
		case 0:
			this.queueOne.add(request);
			break;
		case 1:
			this.queueTwo.add(request);
			break;
		case 2:
			this.queueThree.add(request);
			break;
		default:
			break;
		}
		if (this.queueSize == Long.MAX_VALUE - 1) {
			this.queueSize = 0;
		} else {
			this.queueSize++;
		}
	}
	/**
	 * 获取请求列表
	 * @param nums 获取数目
	 * @return
	 */
	private List<ChannelRequest> getRequests(ConcurrentLinkedQueue<ChannelRequest> queue, int nums) {
		List<ChannelRequest> requestList = new ArrayList<ChannelRequest>(nums);
		//些处加入多线程同步的控制
		//synchronized(this) {
			for (int i=0; i<nums; i++) {
				ChannelRequest request = queue.poll();
				if (request != null) {
					requestList.add(request);
				} else {
					break;
				}
				
			}
		//}
		
		return requestList;
	}
	
	public List<ChannelRequest> getRequests(int queueIdx, int nums) {
		List<ChannelRequest> requestList = null;
		switch(queueIdx) {
		case 0:
			requestList = this.getRequests(this.queueOne, nums);
			break;
		case 1:
			requestList = this.getRequests(this.queueTwo, nums);
			break;
		case 2:
			requestList = this.getRequests(this.queueThree, nums);
			break;
		default:
			break;
		}
		logger.info("fetch requestList from " + queueIdx + " size is " + requestList.size());
		return requestList;
	}
	
	public int getSize() {
		return this.queueOne.size() + this.queueTwo.size() + this.queueThree.size();
	}
}
