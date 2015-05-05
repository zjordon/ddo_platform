package com.jason.ddoMsg.event.channelBusiness;

import com.jason.ddoMsg.event.BaseEventTest;
import com.jason.ddoMsg.event.EventException;

public class DeleteChannelBusinessEventTest extends BaseEventTest {

	private DeleteChannelBusinessEvent event;
	protected void setUp() throws Exception {
		super.setUp();
		event = new DeleteChannelBusinessEvent();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testProcessEvent() throws EventException {
		String param = "id:1";
		event.processEvent(param);
	}

}
