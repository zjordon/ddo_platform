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
 * 更新计费业务信息
 * @author jasonzhang
 *
 */
public class UpdateBBusinessInfoEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(UpdateBBusinessInfoEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id","price","code","channelBillCode");
		String id = paramMap.get("id");
		String price = paramMap.get("price");
		String code = paramMap.get("code");
		String channelBillCode = paramMap.get("channelBillCode");
		super.validDigital(price);
		try {
			CacheManager.getInstance().getBillBusinessCache().updateBillBusiness(id, Long.parseLong(price), code, channelBillCode);
		} catch (ElementNotFoundException e) {
			logger.error("exception when process UpdateBBusinessInfoEvent", e);
			throw new EventException(e.getMessage());
		}

	}

}
