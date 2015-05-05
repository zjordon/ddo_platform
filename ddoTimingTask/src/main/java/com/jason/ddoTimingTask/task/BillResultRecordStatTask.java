/**
 * 
 */
package com.jason.ddoTimingTask.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.BillResultRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.BillResultRecordStatHandler;
import com.jason.ddoTimingTask.task.handler.HandlerException;

/**
 * 发送结果统计任务
 * @author jasonzhang
 *
 */
public class BillResultRecordStatTask extends AbstractTask {
	private static final Logger logger = Logger
			.getLogger(BillResultRecordStatTask.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.AbstractTask#executeTask()
	 */
	@Override
	protected int executeTask() {
		List<BillResultRecord> todoList = null;
		try {
			todoList = DaoManager.getInstance().getBillResultRecordDao().getBillResultRecordList(500);
		} catch (DaoException e) {
			logger.error("exception when executeTask SendRecordStatisticsTask", e);
		}
		if (todoList != null && !todoList.isEmpty()) {
			for (BillResultRecord billResultRecord : todoList) {
				try {
					BillResultRecordStatHandler.getInstacne().handle(billResultRecord);
				} catch (HandlerException e) {
					logger.error("exception when executeTask BillResultRecordStatTask", e);
				}
			}
			
		}
		return todoList != null ? todoList.size() : 0;
	}

}
