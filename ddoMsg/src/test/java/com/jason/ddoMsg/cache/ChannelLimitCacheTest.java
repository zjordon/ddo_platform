package com.jason.ddoMsg.cache;

import java.util.Date;
import java.util.Map;

import com.jason.ddoMsg.bean.channel.ChannelDayLimit;
import com.jason.ddoMsg.bean.channel.ChannelMonthLimit;
import com.jason.ddoMsg.dao.ChannelDao;
import com.jason.ddoMsg.dao.ChannelLimitDao;
import com.jason.ddoMsg.util.DateUtil;

public class ChannelLimitCacheTest extends BaseCacheTest {
	private ChannelLimitCache channelLimitCache;

	protected void setUp() throws Exception {
		super.setUp();
		ChannelLimitDao channelLimitDao = new ChannelLimitDao();
		ChannelDao channelDao = new ChannelDao();
		channelLimitDao.setDataSource(super.dataSource);
		channelDao.setDataSource(super.dataSource);
		this.channelLimitCache = new ChannelLimitCache();
		this.channelLimitCache.setChannelDao(channelDao);
		this.channelLimitCache.setChannelLimitDao(channelLimitDao);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLoadChannelLimitList() throws CacheException {
		this.channelLimitCache.loadChannelLimitList();
		Date current = new Date();
		int day = DateUtil.getDateIntNum(current);
		int month = day/100;
		super.assertEquals(day, this.channelLimitCache.getCurrentDate());
		super.assertEquals(month, this.channelLimitCache.getCurrentMonth());
		Map<String, ChannelDayLimit> dayLimitMap = this.channelLimitCache.getDayLimitMap();
		super.assertNotNull(dayLimitMap);
		super.assertEquals(100000L, dayLimitMap.get("1").getLimitAmount().longValue());
		Map<String, ChannelMonthLimit> monthLimitMap = this.channelLimitCache.getMonthLimitMap();
		super.assertNotNull(monthLimitMap);
		super.assertEquals(3000000L, monthLimitMap.get("1").getLimitAmount().longValue());
		
	}

	public void testDeductChannelLimit() throws CacheException {
		this.channelLimitCache.loadChannelLimitList();
		Map<String, ChannelDayLimit> dayLimitMap = this.channelLimitCache.getDayLimitMap();
		Map<String, ChannelMonthLimit> monthLimitMap = this.channelLimitCache.getMonthLimitMap();
		long initDayLimit = dayLimitMap.get("1").getLimitAmount().longValue();
		long initMonthLimit = monthLimitMap.get("1").getLimitAmount().longValue();
		this.channelLimitCache.deductChannelLimit("1", 20150410, 201504, 10, "1", true, false);
		
		super.assertEquals((initDayLimit - 10L), dayLimitMap.get("1").getLimitAmount().longValue());
		
		super.assertEquals((initMonthLimit - 10L), monthLimitMap.get("1").getLimitAmount().longValue());
		this.channelLimitCache.deductChannelLimit("1", 20150410, 201504, 100, "1", true, false);
		super.assertEquals((initDayLimit - 10L - 100L), dayLimitMap.get("1").getLimitAmount().longValue());
		super.assertEquals((initMonthLimit - 10L - 100L), monthLimitMap.get("1").getLimitAmount().longValue());
		this.channelLimitCache.deductChannelLimit("1", 20150410, 201504, 10, "1", false, true);
		this.channelLimitCache.deductChannelLimit("1", 20150410, 201504, 100, "1", false, true);
		super.assertEquals((initDayLimit - 10L - 100L), dayLimitMap.get("1").getLimitAmount().longValue());
		super.assertEquals((initMonthLimit - 10L - 100L), monthLimitMap.get("1").getLimitAmount().longValue());
	}

	public void testGetChannelDayLimit() throws CacheException {
		this.channelLimitCache.loadChannelLimitList();
		ChannelDayLimit dayLimit = this.channelLimitCache.getChannelDayLimit("1");
		super.assertNotNull(dayLimit);
	}

	public void testGetChannelMonthLimit() throws CacheException {
		this.channelLimitCache.loadChannelLimitList();
		ChannelMonthLimit monthLimit = this.channelLimitCache.getChannelMonthLimit("4");
		super.assertNotNull(monthLimit);
	}

}
