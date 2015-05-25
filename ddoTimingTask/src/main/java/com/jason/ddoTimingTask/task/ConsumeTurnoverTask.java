/**
 * 
 */
package com.jason.ddoTimingTask.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.ConsumeTurnover;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;
import com.jason.ddoTimingTask.task.handler.consume.ConsumeTurnoverHandler;

/**
 * @author jasonzhang
 *
 */
public class ConsumeTurnoverTask extends AbstractTask {
	
	private static final Logger logger = Logger
			.getLogger(ConsumeTurnoverTask.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.AbstractTask#executeTask()
	 */
	@Override
	protected int executeTask() {
		List<ConsumeTurnover> list = null;
		try {
			list = DaoManager.getInstance().getConsumeDao().getUntreatedConsumeTurnover(500);
		} catch (DaoException e) {
			logger.error("exception when executeTask ConsumeTurnoverTask", e);
		}
		if (list != null && !list.isEmpty()) {
			for (ConsumeTurnover record : list) {
				try {
					ConsumeTurnoverHandler.getInstacne().handle(record);
				} catch (HandlerException e) {
					logger.error("exception when executeTask ConsumeTurnoverTask", e);
				}
			}
		}
		return list == null ? 0 : list.size();
	}

}
