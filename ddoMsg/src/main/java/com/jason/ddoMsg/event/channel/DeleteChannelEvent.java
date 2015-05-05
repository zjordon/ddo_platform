/**
 * 
 */
package com.jason.ddoMsg.event.channel;

import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ElementNotFoundException;
import com.jason.ddoMsg.event.AbstractEvent;
import com.jason.ddoMsg.event.EventException;

/**
 * @author jasonzhang
 *
 */
public class DeleteChannelEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(DeleteChannelEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id");
		String id = paramMap.get("id");
		try {
			CacheManager.getInstance().getChannelCache().deleteChannel(id);
			//清除该渠道的日限额和月限额
			CacheManager.getInstance().getChannelLimitCache().deleteDayLimit(id);
			CacheManager.getInstance().getChannelLimitCache().deleteMonthLimit(id);
		} catch (ElementNotFoundException e) {
			logger.error("exception when process DeleteChannelEvent", e);
			throw new EventException(e.getMessage());
		} catch (CacheException e) {
			logger.error("exception when process DeleteChannelEvent", e);
			throw new EventException(e.getMessage());
		}
		

	}

}
