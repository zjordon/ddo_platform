/**
 * 
 */
package com.jason.ddoMsg.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.UpChannel;

/**
 * @author jasonzhang
 *
 */
public class UpChannelQueue {

private static final Logger logger = Logger.getLogger(UpChannelQueue.class);
	
	private final static UpChannelQueue instance = new UpChannelQueue();
	
	private ConcurrentLinkedQueue<UpChannel> queue = null;
	
	private UpChannelQueue(){
		queue = new ConcurrentLinkedQueue<UpChannel>();
	}

	public final static UpChannelQueue getInstance() {
		return instance;
	}
	
	public void addUpChannel(UpChannel upChannel) {
		this.queue.add(upChannel);
	}
	
	public List<UpChannel> getUpChannels(int nums) {
		List<UpChannel> list = new ArrayList<UpChannel>(nums);
		//些处加入多线程同步的控制
		//synchronized(this) {
			for (int i=0; i<nums; i++) {
				UpChannel upChannel = this.queue.poll();
				if (upChannel != null) {
					list.add(upChannel);
				} else {
					break;
				}
				
			}
		//}
		logger.info("fetch UpChannel list size is " + list.size());
		return list;
	}
	
	public int getSize() {
		return this.queue.size();
	}
}
