/**
 * 
 */
package com.jason.ddoMsg.event.channel;

import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.channel.Channel;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.ElementExistsException;
import com.jason.ddoMsg.event.AbstractEvent;
import com.jason.ddoMsg.event.EventException;

/**
 * 新增渠道
 * 
 * @author jasonzhang
 *
 */
public class AddChannelEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(AddChannelEvent.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void processEvent(String param)
			throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "id",
				"state", "closeState", "name", "no",
				"dayLimit", "monthLimit");
		String id = paramMap.get("id");
		String upUrl = paramMap.get("upUrl");
		String downUrl = paramMap.get("downUrl");
		String stateStr = paramMap.get("state");
		String closeStateStr = paramMap.get("closeState");
		String name = paramMap.get("name");
		String noStr = paramMap.get("no");
		String dayLimitStr = paramMap.get("dayLimit");
		String monthLimitStr = paramMap.get("monthLimit");
		super.validDigital(stateStr);
		super.validDigital(closeStateStr);
		int state = Integer.parseInt(stateStr);
		int closeState = Integer.parseInt(closeStateStr);
		super.validState(state);
		super.validState(closeState);
		super.validDigital(dayLimitStr);
		super.validDigital(monthLimitStr);
		super.validDigital(noStr);
		long dayLimit = Long.parseLong(dayLimitStr);
		long monthLimit = Long.parseLong(monthLimitStr);
		long no = Long.parseLong(noStr);
		Channel channel = new Channel();
		channel.setId(id);
		channel.setDayLimit(dayLimit);
		channel.setMonthLimit(monthLimit);
		channel.setCloseState(closeState);
		channel.setState(state);
		channel.setDownUrl(downUrl);
		channel.setUpUrl(upUrl);
		channel.setName(name);
		channel.setNo(no);
		try {
			CacheManager.getInstance().getChannelCache().addChannel(channel);
			//渠道新增完后需要往日限额和月限额的缓存中增加数据,数据库中的数据也由些处新加，web平台无需维护
			CacheManager.getInstance().getChannelLimitCache().addDayLimit(id, dayLimit);
			CacheManager.getInstance().getChannelLimitCache().addMonthLimit(id, monthLimit);
		} catch (ElementExistsException e) {
			logger.error("exception when process AddChannelEvent", e);
			throw new EventException(e.getMessage());
		} catch (CacheException e) {
			logger.error("exception when process AddChannelEvent", e);
			throw new EventException(e.getMessage());
		}
		
		

	}

}
