/**
 * 
 */
package com.jason.ddoMsg.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.consumption.ConsumeTurnover;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.queue.ConsumeTurnoverQueue;

/**
 * 处理浪费记录流水
 * @author jasonzhang
 *
 */
public class ConsumeTurnoverTask extends AbstractTask {
	private static final Logger logger = Logger.getLogger(ConsumeTurnoverTask.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.task.AbstractTask#executeTask()
	 */
	@Override
	protected int executeTask() {
		List<ConsumeTurnover> list = ConsumeTurnoverQueue.getInstnace().getConsumeTurnovers(500);
		if (!list.isEmpty()) {
			try {
				CacheManager.getInstance().getConsumeCache().saveConsumeTurnoverList(list);
			} catch (CacheException e) {
				logger.error("exception when executeTask", e);
			}
		}
		return list.size();
	}

}
