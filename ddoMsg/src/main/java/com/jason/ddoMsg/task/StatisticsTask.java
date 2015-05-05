/**
 * 
 */
package com.jason.ddoMsg.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.statistics.BillResultRecord;
import com.jason.ddoMsg.bean.statistics.SendRecord;
import com.jason.ddoMsg.bean.statistics.SendResultRecord;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.queue.StatisticsQueue;

/**
 * @author jasonzhang
 *
 */
public class StatisticsTask extends AbstractTask {
	private static final Logger logger = Logger.getLogger(StatisticsTask.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.task.AbstractTask#executeTask()
	 */
	@Override
	protected int executeTask() {
		int num = 0;
		List<SendRecord> sendRecordList = StatisticsQueue.getInstance().getSendRecords(500);
		try {
			CacheManager.getInstance().getStatisticsCache().saveSendRecordList(sendRecordList);
			num += sendRecordList.size();
		} catch (CacheException e) {
			logger.error("exception when process saveSendRecordList in StatisticsTask", e);
		}
		sendRecordList.clear();
		List<SendResultRecord> sendResultRecordList = StatisticsQueue.getInstance().getSendResultRecords(500);
		try {
			CacheManager.getInstance().getStatisticsCache().saveSendResultRecordList(sendResultRecordList);
			num += sendResultRecordList.size();
		} catch (CacheException e) {
			logger.error("exception when process saveSendResultRecordList in StatisticsTask", e);
		}
		sendResultRecordList.clear();
		List<BillResultRecord> billResultRecordList = StatisticsQueue.getInstance().getBillResultRecords(500);
		try {
			CacheManager.getInstance().getStatisticsCache().saveBillResultRecordList(billResultRecordList);
			num += billResultRecordList.size();
		} catch (CacheException e) {
			logger.error("exception when process saveSendResultRecordList in StatisticsTask", e);
		}
		billResultRecordList.clear();
		return num;
	}

}
