package com.jason.ddoMsg.dao;

import java.util.List;

import com.jason.ddoMsg.bean.channel.Channel;

public class ChannelDaoTest extends BaseDaoTest {

	private ChannelDao channelDao = null;
	protected void setUp() throws Exception {
		super.setUp();
		channelDao = new ChannelDao();
		channelDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetChannels() {
		List<Channel> channelList = null;
		try {
			channelList = this.channelDao.getChannels();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		super.assertNotNull(channelList);
	}

}
