package com.jason.ddoMsg.task;

import com.jason.ddoMsg.bean.channel.Channel;
import com.jason.ddoMsg.bean.msg.DdoMsg;
import com.jason.ddoMsg.task.handler.DdoMsgHandler;

public class DdoMsgHandlerTest extends BaseTaskTest {

	private DdoMsgHandler ddoMsgHandler;
	protected void setUp() throws Exception {
		super.setUp();
		ddoMsgHandler = DdoMsgHandler.getInstance();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testHandle() {
		DdoMsg ddoMsg = new DdoMsg();
		ddoMsg.setId("402848814c726de4014c726de4e30006");
		ddoMsg.setMsisdn(new Long(13950079348L));
		ddoMsg.setRequestId("1");
		ddoMsg.setReturnMsgCode("00000000");
		ddoMsg.setMsisdnProvinceCode("059");
		ddoMsg.setBillingBusinessId("1");
		Channel channel = new Channel();
		channel.setId("1");
		channel.setUpUrl("http://112.5.196.74:8080/");
		
		ddoMsgHandler.handle(ddoMsg, channel, "test", 2);
	}

}
