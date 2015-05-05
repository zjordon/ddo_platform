/**
 * 
 */
package com.jason.ddoMsg.event.billBusiness;

import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.channel.BillBusiness;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ElementExistsException;
import com.jason.ddoMsg.event.AbstractEvent;
import com.jason.ddoMsg.event.EventException;

/**
 * @author jasonzhang
 *
 */
public class AddBBusinessEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(AddBBusinessEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id","state","price","code", "channelBillCode", "name");
		String id = paramMap.get("id");
		String state = paramMap.get("state");
		String price = paramMap.get("price");
		String code = paramMap.get("code");
		String channelBillCode = paramMap.get("channelBillCode");
		String name = paramMap.get("name");
		super.validDigital(price);
		int stateI = Integer.parseInt(state);
		super.validState(stateI);
		BillBusiness business = new BillBusiness();
		business.setId(id);
		business.setState(new Integer(stateI));
		business.setPrice(new Long(Long.parseLong(price)));
		business.setCode(code);
		business.setChannelBillCode(channelBillCode);
		business.setName(name);
		try {
			CacheManager.getInstance().getBillBusinessCache().addBillBusiness(business);
		} catch (ElementExistsException e) {
			logger.error("exception when process AddBBusinessEvent", e);
			throw new EventException(e.getMessage());
		}

	}

}
