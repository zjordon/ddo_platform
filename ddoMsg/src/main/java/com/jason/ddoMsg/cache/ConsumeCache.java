/**
 * 
 */
package com.jason.ddoMsg.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.consumption.ConsumeRecord;
import com.jason.ddoMsg.bean.consumption.ConsumeTurnover;
import com.jason.ddoMsg.dao.ConsumeDao;
import com.jason.ddoMsg.dao.DaoException;
import com.jason.ddoMsg.util.DateUtil;

/**
 * @author jasonzhang
 *
 */
public class ConsumeCache {
	
	private static final Logger logger = Logger.getLogger(ConsumeCache.class);

	private ConsumeDao consumeDao;
	private int currentMonth;
	private Map<Long, ConsumeRecord> consumeMap;

	public void setConsumeDao(ConsumeDao consumeDao) {
		this.consumeDao = consumeDao;
	}
	/**
	 * 初始化
	 * @throws CacheException
	 */
	public void init() throws CacheException {
		Date current = new Date();
		int day = DateUtil.getDateIntNum(current);
		int month = day/100;
		if (month != currentMonth) {
			//从数据库取当前月份的消费记录
			List<ConsumeRecord> list = null;
			try {
				list = this.consumeDao.getConsumeRecordList(month);
			} catch (DaoException e) {
				logger.error("exception when init", e);
				throw new CacheException(e.getMessage());
			}
			if (list != null && !list.isEmpty()) {
				if (this.consumeMap == null) {
					this.consumeMap = new HashMap<Long, ConsumeRecord>();
				} else {
					this.consumeMap.clear();
				}
				for (ConsumeRecord record : list) {
					this.consumeMap.put(new Long(record.getMsisdn()), record);
				}
			}
			this.currentMonth = month;
		}
	}
	/**
	 * 保存手机消费记录流水
	 * @param list
	 * @throws CacheException
	 */
	public void saveConsumeTurnoverList(List<ConsumeTurnover> list) throws CacheException {
		try {
			this.consumeDao.saveConsumeTurnoverList(list);
		} catch (DaoException e) {
			logger.error("exception when saveConsumeTurnoverList", e);
			throw new CacheException(e.getMessage());
		}
	}
	/**
	 * 保存手机消费记录
	 * @param msisdn 手机号
	 * @param amount 消费金额
	 * @throws ElementExistsException
	 */
	public void saveConsume(long msisdn, int amount) throws ElementExistsException {
		Long key = new Long(msisdn);
		if (this.consumeMap.containsKey(key)) {
			throw new ElementExistsException("the consume record is exist with key:" + msisdn);
		}
		ConsumeRecord record = new ConsumeRecord();
		record.setAmount(amount);
		record.setMsisdn(msisdn);
		record.setNum(1);
		this.consumeMap.put(key, record);
	}
	
	/**
	 * 获取手机消费记录
	 * @param msisdn 手机号码
	 * @return
	 */
	public ConsumeRecord getConsumeRecord(long msisdn) {
		return this.consumeMap.get(new Long(msisdn));
	}
	/**
	 * 增加手机消费金额
	 * @param msisdn
	 * @param amount
	 * @throws ElementNotFoundException
	 */
	public void increaseConsume(long msisdn, int amount) throws ElementNotFoundException {
		ConsumeRecord record = this.consumeMap.get(new Long(msisdn));
		if (record == null) {
			throw new ElementNotFoundException("not found the consume with key " + msisdn);
		}
		record.increaseAmount(amount);
	}
	
}
