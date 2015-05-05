/**
 * 
 */
package com.jason.ddoMsg.cache;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.ChannelRequest;
import com.jason.ddoMsg.dao.ChannelRequestDao;
import com.jason.ddoMsg.dao.DaoException;

/**
 * 外部请求Cache
 * @author jasonzhang
 *
 */
public class ChannelRequestCache {
	private static final Logger logger = Logger.getLogger(ChannelRequestCache.class);

	private ChannelRequestDao channelRequestDao;

	public void setChannelRequestDao(ChannelRequestDao channelRequestDao) {
		this.channelRequestDao = channelRequestDao;
	}

	/**
	 * 保存外部请求列表
	 * @param requestList
	 * @throws DaoException
	 */
	public void saveChannelRequest(List<ChannelRequest> requestList) throws CacheException {
		try {
			this.channelRequestDao.saveChannelRequest(requestList);
		} catch (DaoException e) {
			logger.error("exception when saveChannelRequest", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 更新处理结果
	 * @param channelResult
	 * @throws DaoException
	 */
	public void updateProcessResult(String id, int state, Date beginTime, Date endTime, int processResult) throws CacheException {
		try {
			this.channelRequestDao.updateProcessResult(id, state, beginTime, endTime, processResult);
		} catch (DaoException e) {
			logger.error("exception when updateProcessResult", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 更新状态
	 * @param id 唯一标识
	 * @param state 状态
	 * @throws DaoException
	 */
	public void updateState(String id, int state) throws CacheException {
		try {
			this.channelRequestDao.updateState(id, state);
		} catch (DaoException e) {
			logger.error("exception when updateState", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 获取待发送的定时请求
	 * @param limitDate 待发送请求的时间限制，小于等于该时间的请求需要被返回
	 * @param limitNums 获取条数
	 * @return
	 * @throws SQLException
	 */
	public List<ChannelRequest> getPendingTimingRequests(Date limitDate, int limitNums) throws CacheException {
		try {
			return this.channelRequestDao.getPendingTimingRequests(limitDate, limitNums);
		} catch (DaoException e) {
			logger.error("exception when getPendingTimingRequests", e);
			throw new CacheException(e.getMessage());
		}
	}
	

	/**
	 * 获取异常中断的请求
	 * @param limitNums 获取条数
	 * @return
	 * @throws DaoException
	 */
	public List<ChannelRequest> getUnexpectedEndRequests(int limitNums) throws CacheException {
		try {
			return this.channelRequestDao.getUnexpectedEndRequests(limitNums);
		} catch (DaoException e) {
			logger.error("exception when getUnexpectedEndRequests", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	public ChannelRequest getChannelRequest(String id) throws CacheException {
		try {
			return this.channelRequestDao.getChannelRequest(id);
		} catch (DaoException e) {
			logger.error("exception when getChannelRequest", e);
			throw new CacheException(e.getMessage());
		}
	}
}
