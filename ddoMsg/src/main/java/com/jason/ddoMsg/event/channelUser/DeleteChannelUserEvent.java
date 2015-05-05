/**
 * 
 */
package com.jason.ddoMsg.event.channelUser;

import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ElementNotFoundException;
import com.jason.ddoMsg.event.AbstractEvent;
import com.jason.ddoMsg.event.EventException;

/**
 * 删除渠道用户
 * @author jasonzhang
 *
 */
public class DeleteChannelUserEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(DeleteChannelUserEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id");
		String id = paramMap.get("id");
		try {
			CacheManager.getInstance().getChannelUserCache().deleteChannelUser(id);
		} catch (ElementNotFoundException e) {
			logger.error("exception when process DeleteUserEvent", e);
			throw new EventException(e.getMessage());
		}
	}

}
