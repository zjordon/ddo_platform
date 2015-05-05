/**
 * 
 */
package com.jason.ddoMsg.cache;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.event.Event;
import com.jason.ddoMsg.dao.DaoException;
import com.jason.ddoMsg.dao.EventDao;

/**
 * 事件Cache
 * @author jasonzhang
 *
 */
public class EventCache {
	private static final Logger logger = Logger.getLogger(EventCache.class);

	private EventDao eventDao;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	/**
	 * 保存事件
	 * @param event 事件
	 * @throws DaoException
	 */
	public void saveEvent(Event event) throws CacheException {
		try {
			this.eventDao.saveEvent(event);
		} catch (DaoException e) {
			logger.error("exception when saveEvent", e);
			throw new CacheException(e.getMessage());
		}
	}
	/**
	 * 更新处理时间
	 * @param id 唯一标识
	 * @param beginTime 开始处理时间
	 * @param endTime 处理结束时间
	 * @param processResult 处理结果
	 * @param failMsg 失败原因
	 * @throws CacheException
	 */
	public void updateProcessTime(String id, Date beginTime, Date endTime, int processResult, String failMsg) throws CacheException {
		try {
			this.eventDao.updateProcessTime(id, beginTime, endTime, processResult, failMsg);
		} catch (DaoException e) {
			logger.error("exception when updateProcessTime", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	public List<Event> getPendingEvents(int limitNums) throws CacheException {
		try {
			return this.eventDao.getPendingEvents(limitNums);
		} catch (DaoException e) {
			logger.error("exception when getPendingEvents", e);
			throw new CacheException(e.getMessage());
		}
	}
}
