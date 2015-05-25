/**
 * 
 */
package com.jason.ddoMsg.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.jason.ddoMsg.bean.consumption.ConsumeTurnover;

/**
 * 手机消费记录流水队列
 * @author jasonzhang
 *
 */
public class ConsumeTurnoverQueue {

private final static ConsumeTurnoverQueue instance = new ConsumeTurnoverQueue();
	
	private ConcurrentLinkedQueue<ConsumeTurnover> queue;
	
	private ConsumeTurnoverQueue() {
		queue = new ConcurrentLinkedQueue<ConsumeTurnover>();
	}
	
	public final static ConsumeTurnoverQueue getInstnace() {
		return instance;
	}
	
	public void addConsumeTurnover(ConsumeTurnover record) {
		this.queue.add(record);
	}
	
	public List<ConsumeTurnover> getConsumeTurnovers(int num) {
		List<ConsumeTurnover> list = new ArrayList<ConsumeTurnover>(num);
		for (int i=0; i<num; i++) {
			ConsumeTurnover record = this.queue.poll();
			if (record != null) {
				list.add(record);
			} else {
				break;
			}
		}
		return list;
	}
}
