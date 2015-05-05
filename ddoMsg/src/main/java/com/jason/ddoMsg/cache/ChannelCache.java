/**
 * 
 */
package com.jason.ddoMsg.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.dao.ChannelDao;
import com.jason.ddoMsg.dao.DaoException;
import com.jason.ddoMsg.bean.channel.Channel;

/**
 * 渠道Cache
 * @author jasonzhang
 *
 */
public class ChannelCache {
	private static final Logger logger = Logger.getLogger(ChannelDao.class);

	private ChannelDao channelDao;
	private Map<String, Channel> channelMap;
	
	public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}
	/**
	 * 加载渠道列表
	 * @throws CacheException
	 */
	public void loadChannelList() throws CacheException {
		logger.info("begin load channel list into cache");
		List<Channel> channelList = null;
		try {
			channelList = this.channelDao.getChannels();
		} catch (DaoException e) {
			logger.error("exception when loadChannelList", e);
			throw new CacheException(e.getMessage());
		}
		if (channelList != null) {
			this.channelMap = new HashMap<String, Channel>();
			for (Channel channel : channelList) {
				this.channelMap.put(channel.getId(), channel);
			}
		}
		logger.info("end load channel list into cache, channel nums is " + channelList.size());
	}
	/**
	 * 新增渠道
	 * @param channel
	 */
	public void addChannel(Channel channel) throws ElementExistsException {
		if (this.channelMap.get(channel.getId()) != null) {
			throw new ElementExistsException("channel is already exists with id " + channel.getId());
		}
		this.channelMap.put(channel.getId(), channel);
	}
	/**
	 * 更新渠道状态
	 * @param id
	 * @param state
	 */
	public void updateChannelState(String id, int state) throws ElementNotFoundException {
		Channel channel = this.getChannel(id);
		channel.setState(new Integer(state));
	}
	/**
	 * 更新渠道关停状态
	 * @param id
	 * @param closeState
	 */
	public void updateChannelCloseState(String id, int closeState) throws ElementNotFoundException {
		Channel channel = this.getChannel(id);
		channel.setCloseState(new Integer(closeState));
	}
	/**
	 * 更新渠道日限额
	 * @param id
	 * @param amount
	 */
	public void updateChannelDayLimit(String id, long amount) throws ElementNotFoundException {
		Channel channel = this.getChannel(id);
		channel.setDayLimit(new Long(amount));
	}
	/**
	 * 更新渠道月限额
	 * @param id
	 * @param amount
	 */
	public void updateChannelMonthLimit(String id, long amount) throws ElementNotFoundException {
		Channel channel = this.getChannel(id);
		channel.setMonthLimit(new Long(amount));
	}
	/**
	 * 更新渠道上下行地址
	 * @param id
	 * @param upUrl
	 * @param downUrl
	 */
	public void updateChannelUrl(String id, String upUrl, String downUrl) throws ElementNotFoundException {
		Channel channel = this.getChannel(id);
		channel.setUpUrl(upUrl);
		channel.setDownUrl(downUrl);
	}
	/**
	 * 删除渠道
	 * @param id
	 */
	public void deleteChannel(String id) throws ElementNotFoundException {
		if (this.channelMap.get(id) != null) {
			throw new ElementNotFoundException("channel element not found with id " + id);
		}
		this.channelMap.remove(id);
	}
	/**
	 * 获取渠道
	 * @param id
	 * @return
	 */
	public Channel getChannel(String id) throws ElementNotFoundException {
		Channel channel = this.channelMap.get(id);
		if (channel == null) {
			throw new ElementNotFoundException("channel element not found with id " + id);
		}
		return channel;
	}
	public Map<String, Channel> getChannelMap() {
		return channelMap;
	}
	public void setChannelMap(Map<String, Channel> channelMap) {
		this.channelMap = channelMap;
	}
}
