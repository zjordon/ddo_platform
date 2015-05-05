/**
 * 
 */
package com.jason.ddoMsg.event.billBusiness;

import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.event.AbstractEvent;
import com.jason.ddoMsg.event.EventException;

/**
 * 更新计费业务状态
 * @author jasonzhang
 *
 */
public class UpdateBBusinessStateEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(UpdateBBusinessStateEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id","state");
		String id = paramMap.get("id");
		String state = paramMap.get("state");
		super.validDigital(state);
		int stateI = Integer.parseInt(state);
		super.validState(stateI);
		//TODO 暂时不需要该事件

	}

}
