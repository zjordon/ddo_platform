/**
 * 
 */
package com.jason.ddoMsg.cache;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.dao.BlackListDao;
import com.jason.ddoMsg.dao.DaoException;

/**
 * 黑名单Cache
 * @author jasonzhang
 *
 */
public class BlackListCache {
	private static final Logger logger = Logger.getLogger(BlackListCache.class);

	private BlackListDao blackListDao;
	private long[] msisdns;

	public void setBlackListDao(BlackListDao blackListDao) {
		this.blackListDao = blackListDao;
	}
	/**
	 * 加载有效的黑名单
	 * @throws CacheException
	 */
	public void loadBlackListList() throws CacheException {
		List<Long> blackListList = null;
		try {
			blackListList = this.blackListDao.getBlackLists();
		} catch (DaoException e) {
			logger.error("exception when loadBlackListList", e);
			throw new CacheException(e.getMessage());
		}
		if (blackListList != null) {
			this.msisdns = new long[blackListList.size()];
			for (int i=0; i<this.msisdns.length; i++) {
				this.msisdns[i] = blackListList.get(i).longValue();
			}
			Arrays.sort(this.msisdns);
		}
	}
	/**
	 * 新增黑名单
	 * @param blackList 黑名单
	 * @throws ElementExistsException
	 */
	public void addBlackList(long msisdn) throws ElementExistsException {
		int idx = Arrays.binarySearch(this.msisdns, msisdn);
		if (idx > -1) {
			throw new ElementExistsException("blackList is already exists with msisdn " + msisdn);
		}
		long[] tempArray = new long[this.msisdns.length + 1];
		int putIdx = -1;
		for (int i=0; i<this.msisdns.length; i++) {
			if (this.msisdns[i] < msisdn) {
				tempArray[i] = this.msisdns[i];
			} else {
				if (putIdx == -1) {
					tempArray[i] = msisdn;
					tempArray[i + 1] = this.msisdns[i];
					putIdx = i;
				} else {
					tempArray[i + 1] = this.msisdns[i];
				}
			}
		}
		this.msisdns = tempArray;
	}
	/**
	 * 删除黑名单
	 * @param msisdn 手机号码
	 * @throws ElementNotFoundException
	 */
	public void deleteBlackList(long msisdn) throws ElementNotFoundException {
		int idx = Arrays.binarySearch(this.msisdns, msisdn);
		if (idx > -1) {
			long[] tempArray = new long[this.msisdns.length - 1];
			for (int i=0; i<this.msisdns.length; i++) {
				if (i < idx) {
					tempArray[i] = this.msisdns[i];
				} else if (i > idx) {
					tempArray[i - 1] = this.msisdns[i];
				}
			}
			this.msisdns = tempArray;
		} else {
			throw new ElementNotFoundException("blackList is not found with msisdn " + msisdn);
		}
	}
	/**
	 * 是否存在于黑名单列表
	 * @param msisdn 手机号码
	 */
	public boolean isExists(long msisdn) {
		return (Arrays.binarySearch(this.msisdns, msisdn) > -1);
	}
	
	public long[] getBlackListList() {
		return this.msisdns;
	}
	
	public void setBlackListList(long[] msisdns) {
		this.msisdns = msisdns;
		Arrays.sort(this.msisdns);
	}
}
