package com.jason.ddoMsg.task;

import com.jason.ddoMsg.bean.msg.DdoMsg;
import com.jason.ddoMsg.task.handler.UpChannelHandler;

public class UpChannelHandlerTest extends BaseTaskTest {
	
	private UpChannelHandler upChannelHandler;

	protected void setUp() throws Exception {
		super.setUp();
		upChannelHandler = UpChannelHandler.getInstance();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testHandle() {
		DdoMsg ddoMsg = new DdoMsg();
		ddoMsg.setId("402848814c726de4014c726de4e30005");
		ddoMsg.setMsisdn(new Long(13950079348L));
		ddoMsg.setRequestId("1");
		ddoMsg.setReturnMsgCode("00000000");
		this.upChannelHandler.handle(ddoMsg, "test", "http://112.5.196.74:8080/", null);
	}

}
