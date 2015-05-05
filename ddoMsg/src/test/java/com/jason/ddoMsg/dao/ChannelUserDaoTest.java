package com.jason.ddoMsg.dao;

import java.util.List;

import com.jason.ddoMsg.bean.channel.ChannelUser;

public class ChannelUserDaoTest extends BaseDaoTest {
	
	private ChannelUserDao channelUseDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		this.channelUseDao = new ChannelUserDao();
		this.channelUseDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetChannelUsers() throws DaoException {
		List<ChannelUser> list = this.channelUseDao.getChannelUsers();
		super.assertNotNull(list);
	}

}
