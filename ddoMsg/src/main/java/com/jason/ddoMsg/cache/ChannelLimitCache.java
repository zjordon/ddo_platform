/**
 * 
 */
package com.jason.ddoMsg.cache;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.dao.ChannelDao;
import com.jason.ddoMsg.dao.ChannelLimitDao;
import com.jason.ddoMsg.dao.DaoException;
import com.jason.ddoMsg.util.DateUtil;
import com.jason.ddoMsg.util.UUIDGenerator;
import com.jason.ddoMsg.bean.channel.Channel;
import com.jason.ddoMsg.bean.channel.ChannelDayLimit;
import com.jason.ddoMsg.bean.channel.ChannelMonthLimit;

/**
 * 渠道限额Cache
 * 
 * @author jasonzhang
 *
 */
public class ChannelLimitCache {
	private static final Logger logger = Logger
			.getLogger(ChannelLimitCache.class);

	private ChannelLimitDao channelLimitDao;
	private ChannelDao channelDao;
	private Map<String, ChannelDayLimit> dayLimitMap;
	private Map<String, ChannelMonthLimit> monthLimitMap;
	private int currentDate = 0;
	private int currentMonth = 0;

	public void setChannelLimitDao(ChannelLimitDao channelLimitDao) {
		this.channelLimitDao = channelLimitDao;
	}

	public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}



	/**
	 * 加载渠道限额列表
	 * 
	 * @throws CacheException
	 */
	public void loadChannelLimitList() throws CacheException {
		//获取系统当前时间
		Date current = new Date();
		int day = DateUtil.getDateIntNum(current);
		int month = day/100;
		//当前时间与缓存中的当前日期是否相同
		if (day != currentDate) {
			//从数据库中加载当前日期对应的日限额列表
			List<ChannelDayLimit> dayLimitList = null;
			try {
				dayLimitList = this.channelLimitDao.getChannelDayLimits(day);
			} catch (DaoException e) {
				logger.error("exception when loadChannelLimitList", e);
				throw new CacheException(e.getMessage());
			}
			//数据库中是否存在对应的日限额列表
			if (dayLimitList != null && dayLimitList.isEmpty()) {
				//创建当前日期对应的日限额列表
				dayLimitList = this.generateDayLimitList(day);
			}
			//把日限额数据载入缓存
			this.loadDayLimitsIntoCache(dayLimitList);
			currentDate = day;
			//当前时间与缓存中的当前月份是否相同
			if (month != currentMonth) {
				//从数据库中加载当前日期对应的月限额列表
				List<ChannelMonthLimit> monthLimitList = null;
				try {
					monthLimitList = this.channelLimitDao.getChannelMonthLimits(month);
				} catch (DaoException e) {
					logger.error("exception when loadChannelLimitList", e);
					throw new CacheException(e.getMessage());
				}
				//数据库中是否存在对应的月限额列表
				if (monthLimitList != null && monthLimitList.isEmpty()) {
					//创建当前日期对应的月限额列表
					monthLimitList = this.generateMonthLimitList(month);
				}
				//把月限额数据加载到缓存
				this.loadMonthLimitsIntoCache(monthLimitList);
				this.currentMonth = month;
			}
		}
	}
	
	private List<ChannelDayLimit> generateDayLimitList(int day) throws CacheException {
		List<ChannelDayLimit> dayLimitList = new ArrayList<ChannelDayLimit>();
		List<Channel> channelList = null;
		try {
			channelList = this.channelDao.getChannels();
		} catch (DaoException e) {
			logger.error("exception when generateDayLimitList", e);
			throw new CacheException(e.getMessage());
		}
		if (channelList != null) {
			if (!channelList.isEmpty()) {
				for (Channel channel : channelList) {
					ChannelDayLimit dayLimit = new ChannelDayLimit();
					dayLimit.setId((new UUIDGenerator()).generate());
					dayLimit.setDay(day);
					dayLimit.setLimitAmount(channel.getDayLimit());
					dayLimit.setChannelId(channel.getId());
					dayLimitList.add(dayLimit);
				}
				try {
					this.channelLimitDao.saveChannelDayLimits(dayLimitList, day);
				} catch (DaoException e) {
					logger.error("exception when generateDayLimitList", e);
					throw new CacheException(e.getMessage());
				}
			} else {
				logger.warn("generate day limit list but the channel is empty in db!");
			}
		}
		return dayLimitList;
	}
	
	private void loadDayLimitsIntoCache(List<ChannelDayLimit> dayLimitList) {
		if (this.dayLimitMap == null) {
			this.dayLimitMap = new HashMap<String, ChannelDayLimit>();
		} else {
			this.dayLimitMap.clear();
		}
		for (ChannelDayLimit dayLimit : dayLimitList) {
			this.dayLimitMap.put(dayLimit.getChannelId(), dayLimit);
		}
	}
	
	private void loadMonthLimitsIntoCache(List<ChannelMonthLimit> monthLimitList) {
		if (this.monthLimitMap == null) {
			this.monthLimitMap = new HashMap<String, ChannelMonthLimit>();
		} else {
			this.monthLimitMap.clear();
		}
		for (ChannelMonthLimit monthLimit : monthLimitList) {
			this.monthLimitMap.put(monthLimit.getChannelId(), monthLimit);
		}
	}
	
	private List<ChannelMonthLimit> generateMonthLimitList(int month) throws CacheException {
		List<ChannelMonthLimit> monthLimitList = new ArrayList<ChannelMonthLimit>();
		List<Channel> channelList = null;
		try {
			channelList = this.channelDao.getChannels();
		} catch (DaoException e) {
			logger.error("exception when generateMonthLimitList", e);
			throw new CacheException(e.getMessage());
		}
		if (channelList != null) {
			if (!channelList.isEmpty()) {
				for (Channel channel : channelList) {
					ChannelMonthLimit monthLimit = new ChannelMonthLimit();
					monthLimit.setId((new UUIDGenerator()).generate());
					monthLimit.setMonth(month);
					monthLimit.setLimitAmount(channel.getMonthLimit());
					monthLimit.setChannelId(channel.getId());
					monthLimitList.add(monthLimit);
				}
				try {
					this.channelLimitDao.saveChannelMonthLimits(monthLimitList, month);
				} catch (DaoException e) {
					logger.error("exception when generateMonthLimitList", e);
					throw new CacheException(e.getMessage());
				}
			} else {
				logger.warn("generate month limit list but the channel is empty in db!");
			}
		}
		return monthLimitList;
	}
	
	/**
	 * 
	 * @param channelId
	 * @param dayLimit
	 * @throws CacheException
	 */
	public void addDayLimit(String channelId, long dayLimit) throws CacheException {
		if (this.dayLimitMap.get(channelId) != null) {
			throw new ElementExistsException("the day limit is exists with channelId " + channelId + " and dayLimit " + dayLimit);
		}
		
		ChannelDayLimit channelDayLimit = new ChannelDayLimit();
		channelDayLimit.setId((new UUIDGenerator()).generate());
		channelDayLimit.setDay(this.currentDate);
		channelDayLimit.setChannelId(channelId);
		channelDayLimit.setLimitAmount(dayLimit);
		this.dayLimitMap.put(channelId, channelDayLimit);
		try {
			this.channelLimitDao.addChannelDayLimit(channelDayLimit);
		} catch (DaoException e) {
			logger.error("exception when deductChannelLimit", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param channelId
	 * @param dayLimit
	 * @throws CacheException
	 */
	public void addMonthLimit(String channelId, long monthLimit) throws CacheException {
		if (this.monthLimitMap.get(channelId) != null) {
			throw new ElementExistsException("the month limit is exists with channelId " + channelId + " and monthLimit " + monthLimit);
		}
		ChannelMonthLimit channelMonthLimit = new ChannelMonthLimit();
		channelMonthLimit.setId((new UUIDGenerator()).generate());
		channelMonthLimit.setMonth(this.currentMonth);
		channelMonthLimit.setChannelId(channelId);
		channelMonthLimit.setLimitAmount(monthLimit);
		this.monthLimitMap.put(channelId, channelMonthLimit);
		try {
			this.channelLimitDao.addChannelMonthLimit(channelMonthLimit);
		} catch (DaoException e) {
			logger.error("exception when deductChannelLimit", e);
			throw new CacheException(e.getMessage());
		}
	}
	/**
	 * 删除渠道日限额
	 * @param channelId
	 * @throws CacheException
	 */
	public void deleteDayLimit(String channelId) throws CacheException {
		try {
			this.channelLimitDao.deleteChannelDayLimit(channelId, currentDate);
		} catch (DaoException e) {
			logger.error("exception when deleteDayLimit", e);
			throw new CacheException(e.getMessage());
		}
	}
	/**
	 * 删除渠道月限额
	 * @param channelId
	 * @throws CacheException
	 */
	public void deleteMonthLimit(String channelId) throws CacheException {
		try {
			this.channelLimitDao.deleteChannelMonthLimit(channelId, currentMonth);
		} catch (DaoException e) {
			logger.error("exception when deleteDayLimit", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 改变渠道的日限额
	 * @param channelId
	 * @param day
	 * @param amount 正数表示加，负数表示减
	 * @throws CacheException
	 */
	public void changeDayLimit(String channelId, int day, long amount) throws CacheException {
		if (day == this.currentDate) {
			ChannelDayLimit dayLimit = this.getChannelDayLimit(channelId);
			long limitAmount = dayLimit.getLimitAmount().longValue() + amount;
			dayLimit.setLimitAmount(new Long(limitAmount));
		}
		try {
			this.channelLimitDao.changeDayLimit(channelId, day, amount);
		} catch (DaoException e) {
			logger.error("exception when changeDayLimit", e);
			throw new CacheException(e.getMessage());
		}
	}
	
	/**
	 * 改变渠道的月限额
	 * @param channelId
	 * @param month
	 * @param amount 正数表示加，负数表示减
	 * @throws CacheException
	 */
	public void changeMonthLimit(String channelId, int month, long amount) throws CacheException {
		if (month == this.currentMonth) {
			ChannelMonthLimit monthLimit = this.getChannelMonthLimit(channelId);
			long limitAmount = monthLimit.getLimitAmount().longValue() - amount;
			monthLimit.setLimitAmount(new Long(limitAmount));
		}
		try {
			this.channelLimitDao.changeMonthLimit(channelId, month, amount);
		} catch (DaoException e) {
			logger.error("exception when changeMonthLimit", e);
			throw new CacheException(e.getMessage());
		}
	}

	/**
	 * 
	 * 扣除某个渠道的限额 扣除某个渠道的限额，需要扣除对应月份，对应天的限额，并往渠道限额使用流水插入一条流水记录
	 * 
	 * @param channelId
	 *            渠道id
	 * @param deductDate
	 *            日期
	 * @param amount
	 *            扣减限额(单元：分)
	 * @param requestId
	 *            请求id
	 * @param syncCache
	 *            是否更新缓存
	 * @param syncDb
	 *            是否更新数据库
	 * @throws CacheException
	 */
	public void deductChannelLimit(String channelId, int day, int month,
			long amount, String requestId, boolean syncCache, boolean syncDb)
			throws CacheException {
		if (syncCache) {
			if (day == this.currentDate) {
				ChannelDayLimit dayLimit = this.getChannelDayLimit(channelId);
				long limitAmount = dayLimit.getLimitAmount().longValue() - amount;
				dayLimit.setLimitAmount(new Long(limitAmount));
			} else {
				throw new CacheException("the day is not euqals currentDate could not deduct the dayLimit");
			}
			if (month == this.currentMonth) {
				ChannelMonthLimit monthLimit = this.getChannelMonthLimit(channelId);
				long limitAmount = monthLimit.getLimitAmount().longValue() - amount;
				monthLimit.setLimitAmount(new Long(limitAmount));
			} else {
				throw new CacheException("the month is not euqals currentMonth could not deduct the monthLimit");
			}
			
		}
		if (syncDb) {
			try {
				this.channelLimitDao.deductChannelLimit(channelId, day, month, amount, requestId);
			} catch (DaoException e) {
				logger.error("exception when deductChannelLimit", e);
				throw new CacheException(e.getMessage());
			}
		}
	}

	/**
	 * 获取某渠道的日限额
	 * @param channelId
	 * @return
	 * @throws ElementNotFoundException
	 */
	public ChannelDayLimit getChannelDayLimit(String channelId)
			throws ElementNotFoundException {
		ChannelDayLimit dayLimit = this.dayLimitMap.get(channelId);
		if (dayLimit == null) {
			throw new ElementNotFoundException(
					"dayLimit element not found with channelId " + channelId);
		}
		return dayLimit;
	}

	/**
	 * 获取某渠道的月限额
	 * @param channelId
	 * @return
	 * @throws ElementNotFoundException
	 */
	public ChannelMonthLimit getChannelMonthLimit(String channelId)
			throws ElementNotFoundException {
		ChannelMonthLimit monthLimit = this.monthLimitMap.get(channelId);
		if (monthLimit == null) {
			throw new ElementNotFoundException("monthLimit element not found with channelId " + channelId);
		}
		return monthLimit;
	}

	public Map<String, ChannelDayLimit> getDayLimitMap() {
		return dayLimitMap;
	}

	public Map<String, ChannelMonthLimit> getMonthLimitMap() {
		return monthLimitMap;
	}

	public int getCurrentDate() {
		return currentDate;
	}

	public int getCurrentMonth() {
		return currentMonth;
	}
}
