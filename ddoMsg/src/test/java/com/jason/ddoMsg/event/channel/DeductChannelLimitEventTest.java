package com.jason.ddoMsg.event.channel;

import com.jason.ddoMsg.event.BaseEventTest;
import com.jason.ddoMsg.event.EventException;

public class DeductChannelLimitEventTest extends BaseEventTest {

	DeductChannelLimitEvent event;
	protected void setUp() throws Exception {
		super.setUp();
		event = new DeductChannelLimitEvent();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testProcessEvent() throws EventException {
		String param = "channelId:1,day:20150413,month:201504,amount:10,requestId:402848814cb03bd2014cb03e354d0008";
		event.processEvent(param);
	}

}
