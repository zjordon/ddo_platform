package com.jason.ddoMsg.externalInterface;

import com.jason.ddoMsg.bean.msg.DeliverResponse;

public class ChannelBillRepInterfaceTest extends BaseInterfaceTest {
	
	private ChannelBillRepInterface channelBillRepInterface;

	protected void setUp() throws Exception {
		super.setUp();
		channelBillRepInterface = ChannelBillRepInterface.getInstnace();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testDeliverReport() {
		DeliverResponse response = this.channelBillRepInterface.deliverReport("1", 13950079348L, "0000000", "http://112.5.196.74:8080/");
		super.assertNotNull(response);
		super.assertEquals(200, response.getStatusCode());
		super.assertFalse("ok".equals(response.getMsg()));
		System.out.println(response.getMsg());
	}

}
