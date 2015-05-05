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
 * 更新渠道上下行地址
 * @author jasonzhang
 *
 */
public class UpdateChannelUrlEvent extends AbstractEvent {
	private static final Logger logger = Logger.getLogger(UpdateChannelUrlEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id", "upUrl", "downUrl");
		String id = paramMap.get("id");
		String upUrl =  paramMap.get("upUrl");
		String downUrl =  paramMap.get("downUrl");
		//TODO 验证url格式
		try {
			CacheManager.getInstance().getChannelCache().updateChannelUrl(id, upUrl, downUrl);
		} catch (ElementNotFoundException e) {
			logger.error("exception when process UpdateChannelUrlEvent", e);
			throw new EventException("exception when process UpdateChannelUrlEvent");
		}

	}

}
