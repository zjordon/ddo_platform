/**
 * 
 */
package com.jason.ddoMsg.cache;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.UpChannelRecord;
import com.jason.ddoMsg.dao.DaoException;
import com.jason.ddoMsg.dao.UpChannelRecordDao;

/**
 * 上行渠道记录Cache
 * @author jasonzhang
 *
 */
public class UpChannelRecordCache {
	private static final Logger logger = Logger.getLogger(UpChannelRecordCache.class);

	private UpChannelRecordDao upChannelRecordDao;

	public void setUpChannelRecordDao(UpChannelRecordDao upChannelRecordDao) {
		this.upChannelRecordDao = upChannelRecordDao;
	}
	
	/**
	 * 保存上行渠道记录
	 * @param record 上行渠道记录
	 * @param needRepeat 是否需要重新上行
	 * @throws DaoException
	 */
	public void saveUpChannelRecord(UpChannelRecord record, boolean needRepeat) throws CacheException {
		try {
			this.upChannelRecordDao.saveUpChannelRecord(record, needRepeat);
		} catch (DaoException e) {
			logger.error("exception when saveUpChannelRecord", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 更新消息状态为不重发
	 * @param id
	 * @throws DaoException
	 */
	public void updateNoRepeat(String id) throws CacheException {
		try {
			this.upChannelRecordDao.updateNoRepeat(id);
		} catch (DaoException e) {
			logger.error("exception when updateNoRepeat", e);
			throw new CacheException(e.getMessage());
		}
	}
	

	/**
	 * 更新返回消息编码
	 * 
	 * @param id
	 *            唯一标识
	 * @param resultCode
	 *            消息编码
	 * @param needRepeat
	 *            是否需要重新上行
	 * @throws CacheException
	 */
	public void updateResultCode(String id, String resultCode,
			boolean needRepeat, Date sendTime, Date responseTime) throws CacheException {
		try {
			this.upChannelRecordDao.updateResultCode(id, resultCode, needRepeat, sendTime, responseTime);
		} catch (DaoException e) {
			logger.error("exception when updateResultCode", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 获取需要重新上行的记录
	 * 
	 * @param limitNums
	 *            获取数目
	 * @return
	 * @throws CacheException
	 */
	public List<UpChannelRecord> getNeedUpChannel(int limitNums) throws CacheException {
		try {
			return this.upChannelRecordDao.getNeedUpChannel(limitNums);
		} catch (DaoException e) {
			logger.error("exception when getNeedUpChannel", e);
			throw new CacheException(e.getMessage());
		}
	}
}
