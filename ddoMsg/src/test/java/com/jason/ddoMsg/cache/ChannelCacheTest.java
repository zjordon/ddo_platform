package com.jason.ddoMsg.cache;

import java.util.Map;

import com.jason.ddoMsg.bean.channel.Channel;
import com.jason.ddoMsg.dao.ChannelDao;

public class ChannelCacheTest extends BaseCacheTest {
	private ChannelCache channelCache;

	protected void setUp() throws Exception {
		super.setUp();
		ChannelDao channelDao = new ChannelDao();
		channelDao.setDataSource(super.dataSource);
		this.channelCache = new ChannelCache();
		this.channelCache.setChannelDao(channelDao);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLoadChannelList() throws CacheException {
		this.channelCache.loadChannelList();
		Map<String, Channel> channelMap = this.channelCache.getChannelMap();
		super.assertNotNull(channelMap);
		super.assertEquals(10000000L, channelMap.get("1").getNo().longValue());
	}

}
