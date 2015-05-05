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
 * 更新渠道用户密码
 * @author jasonzhang
 *
 */
public class UpdateCUPasswordEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(UpdateCUPasswordEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id",  "password");
		String id = paramMap.get("id");
		String password = paramMap.get("password");
		try {
			CacheManager.getInstance().getChannelUserCache().updateChanneUserPass(id, password);
		} catch (ElementNotFoundException e) {
			logger.error("exception when process UpdatePasswordEvent", e);
			throw new EventException(e.getMessage());
		}

	}

}
