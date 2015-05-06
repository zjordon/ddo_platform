/**
 * 
 */
package com.jason.ddoMsg.event.channel;

import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ElementNotFoundException;
import com.jason.ddoMsg.event.AbstractEvent;
import com.jason.ddoMsg.event.EventException;

/**
 * 更新渠道信息
 * 更新渠道的信息（除上行，下行地址），状态，关停状态
 * @author jasonzhang
 *
 */
public class UpdateChannelInfoEvent extends AbstractEvent {
	private static final Logger logger = Logger.getLogger(UpdateChannelInfoEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.Event#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id", "state", "closeState");
		String stateStr = paramMap.get("state");
		String closeStateStr = paramMap.get("closeState");
		super.validDigital(stateStr);
		super.validDigital(closeStateStr);
		int state = Integer.parseInt(stateStr);
		int closeState = Integer.parseInt(closeStateStr);
		super.validState(state);
		super.validState(closeState);
		String id = paramMap.get("id");
		try {
			CacheManager.getInstance().getChannelCache().updateChannelState(id, state);
			CacheManager.getInstance().getChannelCache().updateChannelCloseState(id, closeState);
		} catch (ElementNotFoundException e) {
			logger.error("exception when process UpdateChannelInfoEvent", e);
			throw new EventException("exception when process UpdateChannelInfoEvent");
		}
		

	}

}
