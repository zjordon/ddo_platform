/**
 * 
 */
package com.jason.ddoMsg.event.billBusiness;

import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ElementNotFoundException;
import com.jason.ddoMsg.event.AbstractEvent;
import com.jason.ddoMsg.event.EventException;

/**
 * 删除计费业务
 * @author jasonzhang
 *
 */
public class DeleteBBusinessEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(DeleteBBusinessEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id");
		String id = paramMap.get("id");
		try {
			CacheManager.getInstance().getBillBusinessCache().deleteBillBusiness(id);
		} catch (ElementNotFoundException e) {
			logger.error("exception when process DeleteBBusinessEvent", e);
			throw new EventException(e.getMessage());
		}

	}

}
