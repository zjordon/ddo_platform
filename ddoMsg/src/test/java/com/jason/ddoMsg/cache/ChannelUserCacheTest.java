package com.jason.ddoMsg.cache;

import java.util.Map;

import com.jason.ddoMsg.bean.channel.ChannelUser;
import com.jason.ddoMsg.dao.ChannelUserDao;

public class ChannelUserCacheTest extends BaseCacheTest {
	private ChannelUserCache channelUserCache;

	protected void setUp() throws Exception {
		super.setUp();
		ChannelUserDao channelUserDao = new ChannelUserDao();
		channelUserDao.setDataSource(super.dataSource);
		this.channelUserCache = new ChannelUserCache();
		this.channelUserCache.setChannelUserDao(channelUserDao);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLoadChannelUserList() throws CacheException {
		this.channelUserCache.loadChannelUserList();
		 Map<String, ChannelUser> channelUserMap = this.channelUserCache.getChannelUserMap();
		 Map<String, String> usernameMap = this.channelUserCache.getUsernameMap();
		 super.assertEquals("test", channelUserMap.get("1").getUsername());
		 super.assertEquals("1", usernameMap.get("test"));
	}

//	public void testAddChannelUser() {
//		fail("Not yet implemented");
//	}
//
//	public void testUpdateChanneUserPass() {
//		fail("Not yet implemented");
//	}
//
//	public void testUpdateChannelUserState() {
//		fail("Not yet implemented");
//	}
//
//	public void testDeleteChannelUser() {
//		fail("Not yet implemented");
//	}
//
//	public void testGetChanneUser() {
//		fail("Not yet implemented");
//	}
//
//	public void testGetChannelUserByUsername() {
//		fail("Not yet implemented");
//	}

}
