/**
 * 
 */
package com.jason.ddoMsg.event.channelBusiness;

import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ElementNotFoundException;
import com.jason.ddoMsg.event.AbstractEvent;
import com.jason.ddoMsg.event.EventException;

/**
 * 删除渠道业务
 * @author jasonzhang
 *
 */
public class DeleteChannelBusinessEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(DeleteChannelBusinessEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id");
		String id = paramMap.get("id");
		try {
			CacheManager.getInstance().getChannelBusinessCache().deleteChannelBusiness(id);
		} catch (ElementNotFoundException e) {
			logger.error("exception when process DeleteChannelBusinessEvent", e);
			throw new EventException(e.getMessage());
		}

	}

}
