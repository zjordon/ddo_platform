/**
 * 
 */
package com.jason.ddoMsg.cache;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.BillReport;
import com.jason.ddoMsg.dao.BillReportDao;
import com.jason.ddoMsg.dao.DaoException;

/**
 * @author jasonzhang
 *
 */
public class BillReportCache {
	private static final Logger logger = Logger.getLogger(BillReportCache.class);
	private BillReportDao billReportDao;

	public void setBillReportDao(BillReportDao billReportDao) {
		this.billReportDao = billReportDao;
	}

	/**
	 * 保存计费状态报告列表
	 * 
	 * @param reportList
	 *            报告列表
	 * @throws CacheException
	 */
	public void saveBillReportList(List<BillReport> reportList) throws CacheException {
		try {
			this.billReportDao.saveBillReportList(reportList);
		} catch (DaoException e) {
			logger.error("exception when saveBillReportList", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 更新下发结果
	 * 
	 * @param id
	 * @param state
	 * @param processResult
	 * @param resultCode
	 * @throws CacheException
	 */
	public void updateResultCode(String id, int state, int processResult,
			String resultCode, boolean repeat, Date sendTime, Date responseTime) throws CacheException {
		try {
			this.billReportDao.updateResultCode(id, state, processResult, resultCode, repeat, sendTime, responseTime);
		} catch (DaoException e) {
			logger.error("exception when updateResultCode", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 更新状态
	 * @param id
	 * @param state
	 * @param processResult
	 * @param resultCode
	 */
	public void updateState(String id, int state, int processResult, boolean needRepeat) throws CacheException {
		try {
			this.billReportDao.updateState(id, state, processResult, needRepeat);
		} catch (DaoException e) {
			logger.error("exception when updateResultCode", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 获取未找到消息id的记录
	 * @param limitNum
	 * @return
	 */
	public List<BillReport> getMissTransIdRecord(int limitNum) throws CacheException {
		try {
			return this.billReportDao.getMissTransIdRecord(limitNum);
		} catch (DaoException e) {
			logger.error("exception when getMissTransIdRecord", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 获取异常中断的记录
	 * @param limitNum
	 * @return
	 */
	public List<BillReport> getUnexpectedEndReport(int limitNum) throws CacheException {
		try {
			return this.billReportDao.getUnexpectedEndReport(limitNum);
		} catch (DaoException e) {
			logger.error("exception when getUnexpectedEndReport", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 获取需要重新下发的状态报告
	 * @param limitNums
	 * @return
	 * @throws CacheException
	 */
	public List<BillReport> getRepeatReportList(int limitNums) throws CacheException {
		try {
			return this.billReportDao.getRepeatReportList(limitNums);
		} catch (DaoException e) {
			logger.error("exception when getUnexpectedEndReport", e);
			throw new CacheException(e.getMessage());
		}
	}
}
