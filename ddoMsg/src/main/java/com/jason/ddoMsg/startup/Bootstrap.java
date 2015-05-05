/**
 * 
 */
package com.jason.ddoMsg.startup;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.event.EventManager;
import com.jason.ddoMsg.scheduler.TaskScheduler;

/**
 * 启动类，负责初始化引擎需要用到的资源，主要包括缓存，调度，事件三个模块的初始化
 * @author jasonzhang
 *
 */
public class Bootstrap {
	private static final Logger logger = Logger
			.getLogger(Bootstrap.class);

	public void startup() {
		logger.info("start init cacheManager");
		try {
			CacheManager.getInstance().init();
		} catch (CacheException e) {
			logger.error("init chache manager error!!!!");
		}
		logger.info("start init EventManager");
		EventManager.getInstance().init();
		logger.info("start init TaskScheduler");
		TaskScheduler.getInstance().init();
	}
	
	public void stop() {
		logger.info("stop the system");
		CacheManager.getInstance().destory();
		EventManager.getInstance().destory();
		TaskScheduler.getInstance().destory();
		
	}
}
