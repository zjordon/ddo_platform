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
 * 更新渠道日限额
 * @author jasonzhang
 *
 */
public class UpdateDayLimitEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(UpdateDayLimitEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "channelId", "oldValue", "newValue", "updateDate");
		String channelId = paramMap.get("channelId");
		String oldValue = paramMap.get("oldValue");
		String newValue = paramMap.get("newValue");
		String updateDate = paramMap.get("updateDate");
		super.validDigital(oldValue);
		super.validDigital(newValue);
		super.validDigital(updateDate);
		long oldValueL = Long.parseLong(oldValue);
		long newValueL = Long.parseLong(newValue);
		long differenceValue = newValueL - oldValueL;
		try {
			CacheManager.getInstance().getChannelCache().updateChannelDayLimit(channelId, newValueL);
			if (differenceValue != 0) {
				CacheManager.getInstance().getChannelLimitCache().changeDayLimit(channelId, Integer.parseInt(updateDate), differenceValue);
			}
		} catch (ElementNotFoundException e) {
			logger.error("exception when process UpdateDayLimitEvent", e);
			throw new EventException(e.getMessage());
		} catch (CacheException e) {
			logger.error("exception when process UpdateDayLimitEvent", e);
			throw new EventException(e.getMessage());
		}
		

	}

}
