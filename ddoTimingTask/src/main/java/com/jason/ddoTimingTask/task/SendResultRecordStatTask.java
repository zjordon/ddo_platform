/**
 * 
 */
package com.jason.ddoTimingTask.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.SendResultRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;
import com.jason.ddoTimingTask.task.handler.SRRSHandlerChain;

/**
 * 发送结果统计任务
 * @author jasonzhang
 *
 */
public class SendResultRecordStatTask extends AbstractTask {
	private static final Logger logger = Logger
			.getLogger(SendResultRecordStatTask.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.AbstractTask#executeTask()
	 */
	@Override
	protected int executeTask() {
		List<SendResultRecord> todoList = null;
		try {
			todoList = DaoManager.getInstance().getSendResultRecordDao().getSendResultRecordList(500);
		} catch (DaoException e) {
			logger.error("exception when executeTask SendRecordStatisticsTask", e);
		}
		if (todoList != null && !todoList.isEmpty()) {
			for (SendResultRecord sendResultRecord : todoList) {
				try {
					//SendResultRecordStatHandler.getInstacne().handle(sendResultRecord);
					SRRSHandlerChain.getInstance().doHandler(sendResultRecord);
				} catch (HandlerException e) {
					logger.error("exception when executeTask SendResultRecordStatTask", e);
				}
			}
			
		}
		return todoList != null ? todoList.size() : 0;
	}

}
