/**
 * 
 */
package com.jason.ddoMsg.event.channelBusiness;

import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.channel.ChannelBusiness;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ElementExistsException;
import com.jason.ddoMsg.event.AbstractEvent;
import com.jason.ddoMsg.event.EventException;

/**
 * 新增渠道业务
 * @author jasonzhang
 *
 */
public class AddChannelBusinessEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(AddChannelBusinessEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id","instruct", "state", "closeState", "billBusinessId","channelId");
		String id = paramMap.get("id");
		String instruct = paramMap.get("instruct");
		String state = paramMap.get("state");
		String closeState = paramMap.get("closeState");
		String billBusinessId = paramMap.get("billBusinessId");
		String channelId = paramMap.get("channelId");
		super.validDigital(state);
		super.validDigital(closeState);
		int stateI = Integer.parseInt(state);
		int closeStateI = Integer.parseInt(closeState);
		super.validState(stateI);
		super.validState(closeStateI);
		ChannelBusiness channelBusiness = new ChannelBusiness();
		channelBusiness.setId(id);
		channelBusiness.setInstruct(instruct);
		channelBusiness.setState(new Integer(stateI));
		channelBusiness.setCloseState(new Integer(closeStateI));
		channelBusiness.setBillBusinessId(billBusinessId);
		channelBusiness.setChannelId(channelId);
		
		try {
			CacheManager.getInstance().getChannelBusinessCache().addChannelBusiness(channelBusiness);
		} catch (ElementExistsException e) {
			logger.error("exception when process AddChannelBusinessEvent", e);
			throw new EventException(e.getMessage());
		}

	}

}
