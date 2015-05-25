/**
 * 
 */
package com.jason.ddoMsg.task;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;

/**
 * 更新限额任务
 * 每天凌晨0点左右更新各渠道的每日限额的缓存，保存缓存中只有当天的日限额
 * 每日1号凌晨0点左右更新月限额缓存，保证缓存中只保留一份最新的各渠道的月限额缓存
 * 同时只有一个线程在处理
 * @author jasonzhang
 *
 */
public class UpdateChannelLimitTask extends AbstractTask {
	private static final Logger logger = Logger.getLogger(UpdateChannelLimitTask.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.task.Task#execute()
	 */
	@Override
	protected int executeTask() {
		//logger.debug("start execute UpdateChannelLimitTask");
		try {
			CacheManager.getInstance().getChannelLimitCache().loadChannelLimitList();
		} catch (CacheException e) {
			logger.error("exception when execute UpdateChannelLimitTask", e);
		}
		//logger.debug("end execute UpdateChannelLimitTask");
		return 0;
	}

}
