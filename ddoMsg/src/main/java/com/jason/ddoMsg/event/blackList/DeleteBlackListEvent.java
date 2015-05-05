/**
 * 
 */
package com.jason.ddoMsg.event.blackList;

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
public class DeleteBlackListEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(DeleteBlackListEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "msisdn");
		String msisdn = paramMap.get("msisdn");
		super.validDigital(msisdn);
		try {
			CacheManager.getInstance().getBlackListCache().deleteBlackList(Long.parseLong(msisdn));
		} catch (ElementNotFoundException e) {
			logger.error("exception when process DeleteBlackListEvent", e);
			throw new EventException(e.getMessage());
		}

	}

}
