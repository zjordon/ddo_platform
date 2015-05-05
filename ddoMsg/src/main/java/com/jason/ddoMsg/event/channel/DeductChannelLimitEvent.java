/**
 * 
 */
package com.jason.ddoMsg.event.channel;

import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.event.AbstractEvent;
import com.jason.ddoMsg.event.EventException;

/**
 * 扣除渠道限额 (日限额，月限额)
 * @author jasonzhang
 *
 */
public class DeductChannelLimitEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(DeductChannelLimitEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "channelId", "day", "month", "amount", "requestId");
		String channelId = paramMap.get("channelId");
		String day = paramMap.get("day");
		String month = paramMap.get("month");
		String amount = paramMap.get("amount");
		String requestId = paramMap.get("requestId");
		super.validDigital(day);
		super.validDigital(month);
		super.validDigital(amount);
		try {
			CacheManager.getInstance().getChannelLimitCache().deductChannelLimit(channelId, Integer.parseInt(day), Integer.parseInt(month), Long.parseLong(amount), requestId, false, true);
		} catch (CacheException e) {
			logger.error("exception when process DeleteChannelEvent", e);
			throw new EventException(e.getMessage());
		}

	}

}
