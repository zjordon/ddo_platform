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
 * 更新渠道业务状态
 * @author jasonzhang
 *
 */
public class UpdateCBStateEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(UpdateCBStateEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id","state");
		String id = paramMap.get("id");
		String state = paramMap.get("state");
		super.validDigital(state);
		int stateI = Integer.parseInt(state);
		super.validState(stateI);
		try {
			CacheManager.getInstance().getChannelBusinessCache().updateState(id, stateI);
		} catch (ElementNotFoundException e) {
			logger.error("exception when process UpdateCBStateEvent", e);
			throw new EventException(e.getMessage());
		}

	}

}
