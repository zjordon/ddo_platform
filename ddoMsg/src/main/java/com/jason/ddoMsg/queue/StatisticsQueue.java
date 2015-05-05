/**
 * 
 */
package com.jason.ddoMsg.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.statistics.BillResultRecord;
import com.jason.ddoMsg.bean.statistics.SendRecord;
import com.jason.ddoMsg.bean.statistics.SendResultRecord;

/**
 * 统计队列
 * @author jasonzhang
 *
 */
public class StatisticsQueue {

	private static final Logger logger = Logger.getLogger(StatisticsQueue.class);
	
	private final static StatisticsQueue instance = new StatisticsQueue();
	
	private StatisticsQueue(){
		this.sendRecordQueue = new ConcurrentLinkedQueue<SendRecord>();
		this.sendResultRecordQueue = new ConcurrentLinkedQueue<SendResultRecord>();
		this.billResultRecordQueue = new ConcurrentLinkedQueue<BillResultRecord>();
	}
	
	public final static StatisticsQueue getInstance() {
		return instance;
	}
	
	private ConcurrentLinkedQueue<SendRecord> sendRecordQueue;
	private ConcurrentLinkedQueue<SendResultRecord> sendResultRecordQueue;
	private ConcurrentLinkedQueue<BillResultRecord> billResultRecordQueue;
	
	public void addSendRecord(SendRecord record) {
		this.sendRecordQueue.add(record);
	}
	
	public void addSendResultRecord(SendResultRecord record) {
		this.sendResultRecordQueue.add(record);
	}
	
	public void addBillResultRecord(BillResultRecord record) {
		this.billResultRecordQueue.add(record);
	}
	
	public List<SendRecord> getSendRecords(int num) {
		List<SendRecord> list = new ArrayList<SendRecord>(num);
		for (int i=0; i<num; i++) {
			SendRecord record = this.sendRecordQueue.poll();
			if (record != null) {
				list.add(record);
			} else {
				break;
			}
			
		}
		logger.info("fetch SendRecord size is " + list.size());
		return list;
	}
	
	public List<SendResultRecord> getSendResultRecords(int num) {
		List<SendResultRecord> list = new ArrayList<SendResultRecord>(num);
		for (int i=0; i<num; i++) {
			SendResultRecord record = this.sendResultRecordQueue.poll();
			if (record != null) {
				list.add(record);
			} else {
				break;
			}
			
		}
		logger.info("fetch SendResultRecord size is " + list.size());
		return list;
	}
	
	public List<BillResultRecord> getBillResultRecords(int num) {
		List<BillResultRecord> list = new ArrayList<BillResultRecord>(num);
		for (int i=0; i<num; i++) {
			BillResultRecord record = this.billResultRecordQueue.poll();
			if (record != null) {
				list.add(record);
			} else {
				break;
			}
			
		}
		logger.info("fetch BillResultRecord size is " + list.size());
		return list;
	}
	
}
