package com.jason.ddoMsg.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jason.ddoMsg.bean.channel.Channel;
import com.jason.ddoMsg.bean.channel.ChannelDayLimit;
import com.jason.ddoMsg.bean.channel.ChannelMonthLimit;
import com.jason.ddoMsg.util.DateUtil;
import com.jason.ddoMsg.util.UUIDGenerator;

public class ChannelLimitDaoTest extends BaseDaoTest {
	private ChannelLimitDao channelLimitDao = null;
	private ChannelDao channelDao;

	protected void setUp() throws Exception {
		super.setUp();
		channelLimitDao = new ChannelLimitDao();
		this.channelLimitDao.setDataSource(super.dataSource);
		this.channelDao = new ChannelDao();
		this.channelDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

//	public void testDeductChannelLimit() {
//		try {
//			this.channelLimitDao.deductChannelLimit("1", new Date(), 10, "1");
//		} catch (DaoException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void testGetChannelDayLimits() throws DaoException {
//		List<ChannelDayLimit> list = null;
//		list = this.channelLimitDao.getChannelDayLimits(20150331);
//		super.assertNotNull(list);
//		super.assertEquals(1, list.size());
//	}
//
//	public void testGetChannelMonthLimits() throws DaoException {
//		List<ChannelMonthLimit> list = null;
//		list = this.channelLimitDao.getChannelMonthLimits(201503);
//		super.assertNotNull(list);
//		super.assertEquals(1, list.size());
//	}

	public void testSaveChannelDayLimits() throws DaoException {
		Date current = new Date();
		int day = DateUtil.getDateIntNum(current);
		List<Channel> channelList = this.channelDao.getChannels();
		List<ChannelDayLimit> dayLimitList = new ArrayList<ChannelDayLimit>();
		for (Channel channel : channelList) {
			ChannelDayLimit dayLimit = new ChannelDayLimit();
			dayLimit.setId((new UUIDGenerator()).generate());
			dayLimit.setDay(day);
			dayLimit.setLimitAmount(channel.getDayLimit());
			dayLimit.setChannelId(channel.getId());
			dayLimitList.add(dayLimit);
		}
		this.channelLimitDao.saveChannelDayLimits(dayLimitList, day);
	}

	public void testSaveChannelMonthLimits() throws DaoException {
		Date current = new Date();
		int day = DateUtil.getDateIntNum(current);
		int month = day/100;
		List<Channel> channelList = this.channelDao.getChannels();
		List<ChannelMonthLimit> monthLimitList = new ArrayList<ChannelMonthLimit>();
		for (Channel channel : channelList) {
			ChannelMonthLimit monthLimit = new ChannelMonthLimit();
			monthLimit.setId((new UUIDGenerator()).generate());
			monthLimit.setMonth(month);
			monthLimit.setLimitAmount(channel.getDayLimit());
			monthLimit.setChannelId(channel.getId());
			monthLimitList.add(monthLimit);
		}
		this.channelLimitDao.saveChannelMonthLimits(monthLimitList, month);
	}

}
