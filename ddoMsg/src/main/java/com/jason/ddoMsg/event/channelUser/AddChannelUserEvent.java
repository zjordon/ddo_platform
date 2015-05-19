/**
 * 
 */
package com.jason.ddoMsg.event.channelUser;

import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.channel.ChannelUser;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ElementExistsException;
import com.jason.ddoMsg.event.AbstractEvent;
import com.jason.ddoMsg.event.EventException;

/**
 * 新增渠道用户事件
 * @author jasonzhang
 *
 */
public class AddChannelUserEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(AddChannelUserEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id", "username", "password", "state", "channelId");
		String id = paramMap.get("id");
		String username = paramMap.get("username");
		String password = paramMap.get("password");
		String msisdn = paramMap.get("msisdn");
		String state = paramMap.get("state");
		String channelId = paramMap.get("channelId");
		super.validDigital(state);
		int stateI = Integer.parseInt(state);
		super.validState(stateI);
		ChannelUser channelUser = new ChannelUser();
		channelUser.setId(id);
		channelUser.setUsername(username);
		channelUser.setPassword(password);
		if (super.isDigital(msisdn)) {
			channelUser.setMsisdn(new Long(Long.parseLong(msisdn)));
		}
		channelUser.setState(new Integer(stateI));
		channelUser.setChannelId(channelId);
		try {
			CacheManager.getInstance().getChannelUserCache().addChannelUser(channelUser);
		} catch (ElementExistsException e) {
			logger.error("exception when process AddUserEvent", e);
			throw new EventException(e.getMessage());
		}

	}

}
