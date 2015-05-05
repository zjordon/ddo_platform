/**
 * 
 */
package com.jason.ddoTimingTask.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;
import com.jason.ddoTimingTask.task.handler.SendRecordStatisticsHandler;

/**
 * 发送量统计任务
 * @author jasonzhang
 *
 */
public class SendRecordStatisticsTask extends AbstractTask {
	private static final Logger logger = Logger
			.getLogger(SendRecordStatisticsTask.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.AbstractTask#executeTask()
	 */
	@Override
	protected int executeTask() {
		List<SendRecord> todoList = null;
		try {
			todoList = DaoManager.getInstance().getSendRecordDao().getSendRecordList(500);
		} catch (DaoException e) {
			logger.error("exception when executeTask SendRecordStatisticsTask", e);
		}
		if (todoList != null && !todoList.isEmpty()) {
			for (SendRecord sendRecord : todoList) {
				try {
					SendRecordStatisticsHandler.getInstacne().handle(sendRecord);
				} catch (HandlerException e) {
					logger.error("exception when executeTask SendRecordStatisticsTask", e);
				}
			}
			
		}
		return todoList != null ? todoList.size() : 0;
	}

}
