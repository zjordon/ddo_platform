/**
 * 
 */
package com.jason.ddoTimingTask.task;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.SmTask;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;
import com.jason.ddoTimingTask.task.handler.smTask.SmTaskHandler;

/**
 * @author jasonzhang
 *
 */
public class SendMessageTask extends AbstractTask {
	private static final Logger logger = Logger
			.getLogger(SendMessageTask.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.AbstractTask#executeTask()
	 */
	@Override
	protected int executeTask() {
		List<SmTask> todoList = null;
		try {
			todoList = DaoManager.getInstance().getSmTaskDao().getSmTaskList(500);
		} catch (DaoException e) {
			logger.error("exception when executeTask SendMessageTask", e);
		}
		if (todoList != null && !todoList.isEmpty()) {
			HandlerException expectedException = null;
			for (SmTask task : todoList) {
				try {
					SmTaskHandler.getInstacne().handle(task);
				} catch (HandlerException e) {
					expectedException = e;
				}
				if (expectedException != null) {
					task.setState(new Integer(3));
					String failMsg = expectedException.getMessage();
					if (failMsg.length() > 512) {
						failMsg = failMsg.substring(0, 511);
					}
					task.setFailMsg(failMsg);
					expectedException = null;
					try {
						DaoManager.getInstance().getSmTaskDao().updateStateFail(task.getId(), failMsg, new Date());
					} catch (DaoException e) {
						logger.error("exception when executeTask SendMessageTask", e);
					}
				}
				try {
					DaoManager.getInstance().getSmTaskDao().updateStateSuccess(task.getId(), task.getExecuteTime());
				} catch (DaoException e) {
					logger.error("exception when executeTask SendMessageTask", e);
				}
			}
		}
		return todoList != null ? todoList.size() : 0;
	}

}
