/**
 * 
 */
package com.jason.ddoMsg.event.proviceCloseState;

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
public class UpdateProviceCloseStateEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(UpdateProviceCloseStateEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id", "closeState");
		String id = paramMap.get("id");
		String closeState = paramMap.get("closeState");
		super.validDigital(closeState);
		int closeStateI = Integer.parseInt(closeState);
		super.validState(closeStateI);
		try {
			CacheManager.getInstance().getProviceCloseStateCache().updateProviceCloseState(id, closeStateI);
		} catch (ElementNotFoundException e) {
			logger.error("exception when process UpdateProviceCloseStateEvent", e);
			throw new EventException(e.getMessage());
		}

	}

}
