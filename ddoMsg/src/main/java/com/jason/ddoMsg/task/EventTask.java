/**
 * 
 */
package com.jason.ddoMsg.task;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.event.Event;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.EventCache;
import com.jason.ddoMsg.event.EventException;
import com.jason.ddoMsg.event.EventManager;

/**
 * 处理事件
 * @author jasonzhang
 *
 */
public class EventTask extends AbstractTask {
	private static final Logger logger = Logger.getLogger(EventTask.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.task.AbstractTask#executeTask()
	 */
	@Override
	protected int executeTask() {
		int nums = 0;
		logger.debug("start execute EventTask");
		List<Event> eventList = null;
		EventCache eventCache = CacheManager.getInstance().getEventCache();
		
		try {
			eventList = eventCache.getPendingEvents(100);
		} catch (CacheException e) {
			logger.error("exception when execute EventTask", e);
		}
		if (eventList != null && !eventList.isEmpty()) {
			EventManager eventManager = EventManager.getInstance();
			Date beginTime = new Date();
			Date endTime = new Date();
			int processResult = 1;
			String failMsg = null;
			for (Event event : eventList) {
				beginTime.setTime(System.currentTimeMillis());
				 processResult = 1;
				 failMsg = null;
				try {
					eventManager.executeEvent(event.getEventId(), event.getParam());
				} catch (EventException e) {
					logger.error("exception happen when execute event", e);
					processResult = 0;
					failMsg = e.getMessage();
				}
				endTime.setTime(System.currentTimeMillis());
				try {
					eventCache.updateProcessTime(event.getId(), beginTime, endTime, processResult, failMsg);
				} catch (CacheException e) {
					logger.error("exception happen when updateProcessTime", e);
				}
			}
			nums = eventList.size();
		}
		logger.debug("end execute EventTask");
		return nums;
	}

}
