/**
 * 
 */
package com.jason.ddoMsg.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.channel.ChannelUser;
import com.jason.ddoMsg.dao.ChannelUserDao;
import com.jason.ddoMsg.dao.DaoException;

/**
 * 渠道用户Cache
 * @author jasonzhang
 *
 */
public class ChannelUserCache {
	private static final Logger logger = Logger.getLogger(ChannelUserCache.class);

	private ChannelUserDao channelUserDao;
	private Map<String, ChannelUser> channelUserMap;
	private Map<String, String> usernameMap;

	public void setChannelUserDao(ChannelUserDao channelUserDao) {
		this.channelUserDao = channelUserDao;
	}
	/**
	 * 加载渠道用户列表
	 * @throws CacheException
	 */
	public void loadChannelUserList() throws CacheException {
		List<ChannelUser> list = null;
		try {
			list = this.channelUserDao.getChannelUsers();
		} catch (DaoException e) {
			logger.error("exception when loadChannelUserList", e);
			throw new CacheException(e.getMessage());
		}
		if (list != null) {
			this.channelUserMap = new HashMap<String, ChannelUser>();
			this.usernameMap = new HashMap<String, String>();
			for (ChannelUser user : list) {
				this.channelUserMap.put(user.getId(), user);
				this.usernameMap.put(user.getUsername(), user.getId());
			}
		}
	}
	/**
	 * 新增渠道用户
	 * @param channelUser 渠道用户
	 * @throws ElementExistsException
	 */
	public void addChannelUser(ChannelUser channelUser) throws ElementExistsException {
		if (this.channelUserMap.get(channelUser.getId()) != null) {
			throw new ElementExistsException("channelUser is already exists with id " + channelUser.getId());
		}
		this.channelUserMap.put(channelUser.getId(), channelUser);
		this.usernameMap.put(channelUser.getUsername(), channelUser.getId());
	}
	/**
	 * 更新渠道用户密码
	 * @param id 唯一标识
	 * @param pass 密码
	 * @throws ElementNotFoundException
	 */
	public void updateChanneUserPass(String id, String pass) throws ElementNotFoundException {
		ChannelUser channelUser = this.getChanneUser(id);
		channelUser.setPassword(pass);
	}
	/**
	 * 更新渠道用户状态
	 * @param id 唯一标识
	 * @param state 状态
	 * @throws ElementNotFoundException
	 */
	public void updateChannelUserState(String id, int state) throws ElementNotFoundException {
		ChannelUser channelUser = this.getChanneUser(id);
		channelUser.setState(new Integer(state));
	}
	/**
	 * 删除渠道用户
	 * @param id 唯一标识
	 * @throws ElementNotFoundException
	 */
	public void deleteChannelUser(String id) throws ElementNotFoundException {
		ChannelUser channelUser = this.getChanneUser(id);
		this.channelUserMap.remove(id);
		this.usernameMap.remove(channelUser.getUsername());
	}
	/**
	 * 获取渠道用户
	 * @param id 唯一标识
	 * @return
	 * @throws ElementNotFoundException
	 */
	public ChannelUser getChanneUser(String id) throws ElementNotFoundException {
		ChannelUser channelUser = this.channelUserMap.get(id);
		if (channelUser == null) {
			throw new ElementNotFoundException("channelUser element not found with id " + id);
		}
		return channelUser;
	}
	
	public ChannelUser getChannelUserByUsername(String username) throws ElementNotFoundException {
		String id = this.usernameMap.get(username);
		if (id == null) {
			throw new ElementNotFoundException("channelUser element not found with username " + username);
		}
		return this.getChanneUser(id);
	}
	public Map<String, ChannelUser> getChannelUserMap() {
		return channelUserMap;
	}
	public Map<String, String> getUsernameMap() {
		return usernameMap;
	}
}
