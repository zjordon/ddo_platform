package com.jason.ddoMsg.externalInterface;

import com.jason.ddoMsg.bean.msg.DdoMsg;
import com.jason.ddoMsg.bean.msg.DdoMsgResult;
import com.jason.ddoMsg.cache.CacheManager;

import junit.framework.TestCase;

public class DdoMsgInterfaceTest extends TestCase {

	private  DdoMsgInterface  ddoMsgInterface;
	protected void setUp() throws Exception {
		super.setUp();
		ddoMsgInterface = DdoMsgInterface.getInstance();
		CacheManager.getInstance().init();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSubmitDdoMsg() {
		DdoMsg ddoMsg = new DdoMsg();
		ddoMsg.setId("402848814ca2d866014ca2d9e923000f");
		ddoMsg.setMsisdn(15060750676L);
		ddoMsg.setBillingBusinessId("1");
		ddoMsg.setChannelId("1");
		ddoMsg.setRequestId("402848814ca2d866014ca2d885190004");
		DdoMsgResult result = this.ddoMsgInterface.submitDdoMsg(ddoMsg);
		super.assertNotNull(result);
		System.out.println(result.getSendResult() + "," + result.getReturnMsgCode() + "," + result.getTransationId());
	}

}
