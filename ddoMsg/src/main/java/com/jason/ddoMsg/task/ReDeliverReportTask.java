/**
 * 
 */
package com.jason.ddoMsg.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.BillReport;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.task.handler.BillReportHandler;

/**
 * 重新下发状态报告任务
 * 从数据库中获取需要重新下发的状态进行处理
 * @author jasonzhang
 *
 */
public class ReDeliverReportTask extends AbstractTask {
	private static final Logger logger = Logger.getLogger(ReDeliverReportTask.class);

	protected int executeTask() {
		int nums = 0;
		logger.debug("start execute ReDeliverReportTask");
		List<BillReport> records = null;
		try {
			records = CacheManager.getInstance().getBillReportCache().getRepeatReportList(100);
		} catch (CacheException e) {
			logger.error("excpetion when execute bill ReDeliverReportTask", e);
		}
		if (records != null && !records.isEmpty()) {
			BillReportHandler.getInstance().handle(records, false);
			nums = records.size();
		}
		logger.debug("end execute ReDeliverReportTask");
		return nums;
	}

}
