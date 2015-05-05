/**
 * 
 */
package com.jason.ddoMsg.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.BillReport;
import com.jason.ddoMsg.queue.BillReportQueue;
import com.jason.ddoMsg.task.handler.BillReportHandler;

/**
 * 处理状态报告任务
 * 从状态报告获取待处理的状态报告进行处理，有可能要启多个线程同时处理
 * @author jasonzhang
 *
 */
public class NormalBillReportTask extends AbstractTask {
	private static final Logger logger = Logger.getLogger(NormalBillReportTask.class);

	protected int executeTask() {
		int nums = 0;
		logger.debug("start execute NormalBillReportTask");
		List<BillReport> billReportList = BillReportQueue.getInstnace().getBillReports(100);
		if (!billReportList.isEmpty()) {
			BillReportHandler.getInstance().handle(billReportList);
			nums = billReportList.size();
			billReportList.clear();
		}
		logger.debug("end execute NormalBillReportTask");
		return nums;
	}
}
