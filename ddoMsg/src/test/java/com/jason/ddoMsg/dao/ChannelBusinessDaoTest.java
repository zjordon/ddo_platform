package com.jason.ddoMsg.dao;

import java.util.List;

import com.jason.ddoMsg.bean.channel.ChannelBusiness;

public class ChannelBusinessDaoTest extends BaseDaoTest {
	private ChannelBusinessDao channelBusinessDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		channelBusinessDao = new ChannelBusinessDao();
		channelBusinessDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetChannelBusinesses() {
		List<ChannelBusiness> channelBusinessList = null;
		try {
			channelBusinessList = channelBusinessDao.getChannelBusinesses();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		super.assertNotNull(channelBusinessList);
	}

}
