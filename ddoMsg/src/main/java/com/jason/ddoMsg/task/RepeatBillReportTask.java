/**
 * 
 */
package com.jason.ddoMsg.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.BillReport;
import com.jason.ddoMsg.cache.BillReportCache;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.task.handler.BillReportHandler;

/**
 * 处理异常状态报告任务
 * 从数据库获取异常中断的任务进行处理，同时只有一个线程进行处理
 * 从数据库中获取未找到对应消息的状态报告进行处理，同时只有一个线程进行处理 
 * @author jasonzhang
 *
 */
public class RepeatBillReportTask extends AbstractTask {
	private static final Logger logger = Logger.getLogger(RepeatBillReportTask.class);

	protected int executeTask() {
		int nums = 0;
		logger.debug("start execute RepeatBillReportTask");
		BillReportCache billReportCache = CacheManager.getInstance().getBillReportCache();
		List<BillReport> billReportList = new ArrayList<BillReport>(200);
		try {
			List<BillReport> tempList = billReportCache.getMissTransIdRecord(100);
			if (!tempList.isEmpty()) {
				billReportList.addAll(tempList);
			}
			tempList.clear();
			tempList = billReportCache.getUnexpectedEndReport(100);
			if (!tempList.isEmpty()) {
				billReportList.addAll(tempList);
			}
			tempList.clear();
		} catch (CacheException e) {
			logger.error("excpetion when execute bill RepeatBillReportTask", e);
		}
		if (!billReportList.isEmpty()) {
			BillReportHandler.getInstance().handle(billReportList, true);
			nums = billReportList.size();
		}
		logger.debug("end execute RepeatBillReportTask");
		return nums;
	}

	
}
