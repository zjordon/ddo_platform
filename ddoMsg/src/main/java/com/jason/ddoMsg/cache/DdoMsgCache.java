/**
 * 
 */
package com.jason.ddoMsg.cache;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.DdoMsg;
import com.jason.ddoMsg.dao.DaoException;
import com.jason.ddoMsg.dao.DdoMsgDao;

/**
 * @author jasonzhang
 *
 */
public class DdoMsgCache {
	private static final Logger logger = Logger.getLogger(DdoMsgCache.class);

	private DdoMsgDao ddoMsgDao;

	public void setDdoMsgDao(DdoMsgDao ddoMsgDao) {
		this.ddoMsgDao = ddoMsgDao;
	}
	
	/**
	 * 保存消息列表
	 * 
	 * @param msgList
	 * @throws DaoException
	 */
	public void saveMsgList(List<DdoMsg> msgList) throws CacheException {
		
		try {
			this.ddoMsgDao.saveMsgList(msgList);
		} catch (DaoException e) {
			logger.error("exception when saveMsgList", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 更新消息事务ID和返回编码
	 * 
	 * @param id
	 *            唯一标识
	 * @param transationId
	 *            事务ID
	 * @param returnMsgCode
	 *            返回编码
	 * @param sendResult
	 *            发送结果
	 * @param sendTime
	 *            发送时间
	 * @throws DaoException
	 */
	public void updateMsgTransationId(String id, String transationId,
			String returnMsgCode, int sendResult, Date sendTime, Date responseTime)
			throws CacheException {
		try {
			this.ddoMsgDao.updateMsgTransationId(id, transationId, returnMsgCode, sendResult, sendTime, responseTime);
		} catch (DaoException e) {
			logger.error("exception when updateMsgTransationId", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 更新消息返回编码
	 * 
	 * @param id
	 *            唯一标识
	 * @param returnMsgCode
	 *            返回编码
	 * @param sendResult
	 *            发送结果
	 * @param sendTime
	 *            发送时间
	 * @throws DaoException
	 */
	public void updateMsgRetMsgCode(String id, String returnMsgCode,
			int sendResult, Date sendTime, boolean repeat, Date responseTime) throws CacheException {
		try {
			this.ddoMsgDao.updateMsgRetMsgCode(id, returnMsgCode, sendResult, sendTime, repeat, responseTime);
		} catch (DaoException e) {
			logger.error("exception when updateMsgRetMsgCode", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 更新消息计费编码
	 * 
	 * @param id
	 *            唯一标识
	 * @param billingCode
	 *            计费编码
	 * @param billingStateTime
	 *            计费编码返回时间
	 * @throws DaoException
	 */
	public void updateMsgBillingCode(String id, String billingCode,
			Date billingStateTime) throws CacheException {
		try {
			this.ddoMsgDao.updateMsgBillingCode(id, billingCode, billingStateTime);
		} catch (DaoException e) {
			logger.error("exception when updateMsgBillingCode", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	public void updateMsgSendResult(String id, int sendResult, boolean repeat) throws CacheException {
		try {
			this.ddoMsgDao.updateMsgSendResult(id, sendResult, repeat);
		} catch (DaoException e) {
			logger.error("exception when updateMsgSendResult", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 获取消息列表
	 * 
	 * @param transationIds
	 *            事务id列表
	 * @return
	 * @throws DaoException
	 */
	public List<DdoMsg> getDdoMsgs(List<String> transationIds)
			throws CacheException {
		try {
			return this.ddoMsgDao.getDdoMsgs(transationIds);
		} catch (DaoException e) {
			logger.error("exception when getDdoMsgs", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 获取某批请求的消息列表
	 * 
	 * @param reqId
	 *            请求id
	 * @return
	 * @throws DaoException
	 */
	public List<DdoMsg> getDdoMsgByReqId(String reqId) throws CacheException {
		try {
			return this.ddoMsgDao.getDdoMsgByReqId(reqId);
		} catch (DaoException e) {
			logger.error("exception when getDdoMsgByReqId", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 获取需要重发的消息列表
	 * 
	 * @param limitNums
	 *            获取条数
	 * @return
	 * @throws CacheException
	 */
	public List<DdoMsg> getNeedRepeatMsg(int limitNums) throws CacheException {
		try {
			return this.ddoMsgDao.getNeedRepeatMsg(limitNums);
		} catch (DaoException e) {
			logger.error("exception when getNeedRepeatMsg", e);
			throw new CacheException(e.getMessage());
		}
	}
	/**
	 * 获取单条消息
	 * @param id
	 * @return
	 * @throws CacheException
	 */
	public DdoMsg getDdoMsg(String id) throws CacheException {
		try {
			return this.ddoMsgDao.getDdoMsg(id);
		} catch (DaoException e) {
			logger.error("exception when getDdoMsg", e);
			throw new CacheException(e.getMessage());
		}
	}
}
