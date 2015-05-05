/**
 * 
 */
package com.jason.ddoMsg.externalInterface;

import java.util.Date;

import com.jason.ddoMsg.bean.event.Event;
import com.jason.ddoMsg.util.UUIDGenerator;

/**
 * 接收事件接口
 * @author jasonzhang
 *
 */
public class EventInterface {
	
	private final static EventInterface instance = new EventInterface();
	
	private EventInterface(){}
	
	public final static EventInterface getInstance() {
		return instance;
	}

	/**
	 * 接收事件
	 * @param eventId 事件id
	 * @param param 参数
	 */
	public void receiveEvent(String eventId, String param) {
		//TODO 验证事件id和参数格式的合法性
		Event event = new Event();
		event.setId((new UUIDGenerator()).generate());
		event.setEventId(eventId);
		event.setParam(param);
		event.setCreateDate(new Date());
		//TODO 调用事件模块方法处理事件
	}
}
