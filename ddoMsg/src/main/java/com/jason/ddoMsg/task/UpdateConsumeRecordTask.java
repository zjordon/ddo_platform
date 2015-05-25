/**
 * 
 */
package com.jason.ddoMsg.task;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;

/**
 * 更新手机消费记录任务
 * @author jasonzhang
 *
 */
public class UpdateConsumeRecordTask extends AbstractTask {
	private static final Logger logger = Logger.getLogger(UpdateConsumeRecordTask.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.task.AbstractTask#executeTask()
	 */
	@Override
	protected int executeTask() {
		try {
			CacheManager.getInstance().getConsumeCache().init();
		} catch (CacheException e) {
			logger.error("exception when executeTask", e);
		}
		return 0;
	}

}
