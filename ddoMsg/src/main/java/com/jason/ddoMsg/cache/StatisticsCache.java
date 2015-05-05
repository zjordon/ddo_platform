/**
 * 
 */
package com.jason.ddoMsg.cache;

import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.statistics.BillResultRecord;
import com.jason.ddoMsg.bean.statistics.SendRecord;
import com.jason.ddoMsg.bean.statistics.SendResultRecord;
import com.jason.ddoMsg.dao.DaoException;
import com.jason.ddoMsg.dao.StatisticsDao;

/**
 * @author jasonzhang
 *
 */
public class StatisticsCache {

	private static final Logger logger = Logger.getLogger(StatisticsCache.class);
	
	private StatisticsDao statisticsDao;

	public void setStatisticsDao(StatisticsDao statisticsDao) {
		this.statisticsDao = statisticsDao;
	}
	
	/**
	 * 保存发送列表
	 * @param list
	 * @throws CacheException
	 */
	public void saveSendRecordList(List<SendRecord> list) throws CacheException {
		try {
			this.statisticsDao.saveSendRecordList(list);
		} catch (DaoException e) {
			logger.error("exception when saveSendRecordList", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 保存发送结果列表
	 * @param list
	 * @throws CacheException
	 */
	public void saveSendResultRecordList(List<SendResultRecord> list) throws CacheException {
		try {
			this.statisticsDao.saveSendResultRecordList(list);
		} catch (DaoException e) {
			logger.error("exception when saveSendResultRecordList", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 保存计费结果列表
	 * @param list
	 * @throws DaoException
	 */
	public void saveBillResultRecordList(List<BillResultRecord> list)  throws CacheException {
		try {
			this.statisticsDao.saveBillResultRecordList(list);
		} catch (DaoException e) {
			logger.error("exception when saveBillResultRecordList", e);
			throw new CacheException(e.getMessage());
		}
	}
}
