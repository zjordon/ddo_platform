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
 * 更新渠道月限额
 * @author jasonzhang
 *
 */
public class UpdateMonthLimitEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(UpdateMonthLimitEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "channelId", "oldValue", "newValue", "updateMonth");
		String channelId = paramMap.get("channelId");
		String oldValue = paramMap.get("oldValue");
		String newValue = paramMap.get("newValue");
		String updateMonth = paramMap.get("updateMonth");
		super.validDigital(oldValue);
		super.validDigital(newValue);
		super.validDigital(updateMonth);
		long oldValueL = Long.parseLong(oldValue);
		long newValueL = Long.parseLong(newValue);
		long differenceValue = newValueL - oldValueL;
		try {
			CacheManager.getInstance().getChannelCache().updateChannelMonthLimit(channelId, newValueL);
			if (differenceValue != 0) {
				CacheManager.getInstance().getChannelLimitCache().changeMonthLimit(channelId, Integer.parseInt(updateMonth), differenceValue);
			}
		} catch (ElementNotFoundException e) {
			logger.error("exception when process UpdateMonthLimitEvent", e);
			throw new EventException(e.getMessage());
		} catch (CacheException e) {
			logger.error("exception when process UpdateMonthLimitEvent", e);
			throw new EventException(e.getMessage());
		}

	}

}
