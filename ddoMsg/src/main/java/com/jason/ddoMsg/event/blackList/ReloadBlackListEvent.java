/**
 * 
 */
package com.jason.ddoMsg.event.blackList;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.event.AbstractEvent;
import com.jason.ddoMsg.event.EventException;

/**
 * @author jasonzhang
 *
 */
public class ReloadBlackListEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(ReloadBlackListEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		try {
			CacheManager.getInstance().getBlackListCache().loadBlackListList();
		} catch (CacheException e) {
			logger.error("exception when process ReloadBlackListEvent", e);
			throw new EventException(e.getMessage());
		}

	}

}
