/**
 * 
 */
package com.jason.ddoMsg.event;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.event.Event;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.event.billBusiness.AddBBusinessEvent;
import com.jason.ddoMsg.event.billBusiness.DeleteBBusinessEvent;
import com.jason.ddoMsg.event.billBusiness.UpdateBBusinessInfoEvent;
import com.jason.ddoMsg.event.billBusiness.UpdateBBusinessStateEvent;
import com.jason.ddoMsg.event.blackList.AddBlackListEvent;
import com.jason.ddoMsg.event.blackList.DeleteBlackListEvent;
import com.jason.ddoMsg.event.blackList.ReloadBlackListEvent;
import com.jason.ddoMsg.event.channel.AddChannelEvent;
import com.jason.ddoMsg.event.channel.DeductChannelLimitEvent;
import com.jason.ddoMsg.event.channel.DeleteChannelEvent;
import com.jason.ddoMsg.event.channel.UpdateChannelInfoEvent;
import com.jason.ddoMsg.event.channel.UpdateChannelUrlEvent;
import com.jason.ddoMsg.event.channel.UpdateDayLimitEvent;
import com.jason.ddoMsg.event.channel.UpdateMonthLimitEvent;
import com.jason.ddoMsg.event.channelBusiness.AddChannelBusinessEvent;
import com.jason.ddoMsg.event.channelBusiness.DeleteChannelBusinessEvent;
import com.jason.ddoMsg.event.channelBusiness.UpdateCBCloseStateEvent;
import com.jason.ddoMsg.event.channelBusiness.UpdateCBInstruct;
import com.jason.ddoMsg.event.channelBusiness.UpdateCBStateEvent;
import com.jason.ddoMsg.event.channelUser.AddChannelUserEvent;
import com.jason.ddoMsg.event.channelUser.DeleteChannelUserEvent;
import com.jason.ddoMsg.event.channelUser.UpdateCUPasswordEvent;
import com.jason.ddoMsg.event.channelUser.UpdateChannelUserStateEvent;
import com.jason.ddoMsg.event.consume.UpdateConsumeEvent;
import com.jason.ddoMsg.event.proviceCloseState.AddProviceCloseStateEvent;
import com.jason.ddoMsg.event.proviceCloseState.UpdateProviceCloseStateEvent;

/**
 * 事件管理器
 * @author jasonzhang
 *
 */
public class EventManager {

	private static final Logger logger = Logger
			.getLogger(EventManager.class);
	
	private final static EventManager instance = new EventManager();
	
	private Map<String, IEvent> eventMap;
	
	private EventManager(){}
	
	public final static EventManager getInstance() {
		return instance;
	}
	/**
	 * 初始化
	 */
	public void init() {
		eventMap = new HashMap<String, IEvent>();
		eventMap.put("UpdateChannelInfoEvent", new UpdateChannelInfoEvent());
		eventMap.put("UpdateChannelUrlEvent", new UpdateChannelUrlEvent());
		eventMap.put("AddChannelEvent", new AddChannelEvent());
		eventMap.put("DeleteChannelEvent", new DeleteChannelEvent());
		eventMap.put("DeductChannelLimitEvent", new DeductChannelLimitEvent());
		eventMap.put("UpdateMonthLimitEvent", new UpdateMonthLimitEvent());
		eventMap.put("UpdateDayLimitEvent", new UpdateDayLimitEvent());
		
		eventMap.put("AddChannelUserEvent", new AddChannelUserEvent());
		eventMap.put("DeleteChannelUserEvent", new DeleteChannelUserEvent());
		eventMap.put("UpdateCUPasswordEvent", new UpdateCUPasswordEvent());
		eventMap.put("UpdateChannelUserStateEvent", new UpdateChannelUserStateEvent());
		
		eventMap.put("UpdateBBusinessInfoEvent", new UpdateBBusinessInfoEvent());
		eventMap.put("UpdateBBusinessStateEvent", new UpdateBBusinessStateEvent());
		eventMap.put("DeleteBBusinessEvent", new DeleteBBusinessEvent());
		eventMap.put("AddBBusinessEvent", new AddBBusinessEvent());
		
		eventMap.put("UpdateCBCloseStateEvent", new UpdateCBCloseStateEvent());
		eventMap.put("UpdateCBStateEvent", new UpdateCBStateEvent());
		eventMap.put("UpdateCBInstruct", new UpdateCBInstruct());
		eventMap.put("AddChannelBusinessEvent", new AddChannelBusinessEvent());
		eventMap.put("DeleteChannelBusinessEvent", new DeleteChannelBusinessEvent());
		
		eventMap.put("UpdateProviceCloseStateEvent", new UpdateProviceCloseStateEvent());
		eventMap.put("AddProviceCloseStateEvent", new AddProviceCloseStateEvent());
		
		eventMap.put("DeleteBlackListEvent", new DeleteBlackListEvent());
		eventMap.put("AddBlackListEvent", new AddBlackListEvent());
		eventMap.put("ReloadBlackListEvent", new ReloadBlackListEvent());
		
		eventMap.put("UpdateConsumeEvent", new UpdateConsumeEvent());
		
	}
	
	/**
	 * 新增事件
	 * @param event
	 * @throws EventException
	 */
	public void addEvent(Event event) throws EventException {
		try {
			CacheManager.getInstance().getEventCache().saveEvent(event);
		} catch (CacheException e) {
			logger.error("exception when process ReloadBlackListEvent", e);
			throw new EventException(e.getMessage());
		}
	}
	/**
	 * 执行事件
	 * @param eventId
	 * @param param
	 * @throws EventException
	 */
	public void executeEvent(String eventId, String param) throws EventException {
		IEvent event = this.eventMap.get(eventId);
		if (event != null) {
			event.processEvent(param);
		} else {
			throw new EventException("the event is not exists with id " + eventId);
		}
	}
	
	public void destory() {
		this.eventMap.clear();
	}
}
