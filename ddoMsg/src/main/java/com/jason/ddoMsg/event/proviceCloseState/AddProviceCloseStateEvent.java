/**
 * 
 */
package com.jason.ddoMsg.event.proviceCloseState;

import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.channel.ProviceCloseState;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ElementExistsException;
import com.jason.ddoMsg.event.AbstractEvent;
import com.jason.ddoMsg.event.EventException;

/**
 * @author jasonzhang
 *
 */
public class AddProviceCloseStateEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(AddProviceCloseStateEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id", "closeState", "proviceCode");
		String id = paramMap.get("id");
		String proviceCode = paramMap.get("proviceCode");
		String closeState = paramMap.get("closeState");
		super.validDigital(closeState);
		int closeStateI = Integer.parseInt(closeState);
		super.validState(closeStateI);
		ProviceCloseState proviceCloseState = new ProviceCloseState();
		proviceCloseState.setId(id);
		proviceCloseState.setProviceCode(proviceCode);
		proviceCloseState.setCloseState(new Integer(closeStateI));
		try {
			CacheManager.getInstance().getProviceCloseStateCache().addProviceCloseState(proviceCloseState);
		} catch (ElementExistsException e) {
			logger.error("exception when process AddProviceCloseStateEvent", e);
			throw new EventException(e.getMessage());
		}
	}

}
