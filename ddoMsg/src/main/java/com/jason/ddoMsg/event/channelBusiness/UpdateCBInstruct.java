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
 * @author jasonzhang
 *
 */
public class UpdateCBInstruct extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(UpdateCBInstruct.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id","instruct");
		String id = paramMap.get("id");
		String instruct = paramMap.get("instruct");
		
		try {
			CacheManager.getInstance().getChannelBusinessCache().updateChannelBusinessMsg(id, instruct);
		} catch (ElementNotFoundException e) {
			logger.error("exception when process UpdateCBInstruct", e);
			throw new EventException(e.getMessage());
		}

	}

}
