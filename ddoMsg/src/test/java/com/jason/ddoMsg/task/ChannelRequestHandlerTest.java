package com.jason.ddoMsg.task;

import java.util.ArrayList;
import java.util.List;

import com.jason.ddoMsg.bean.msg.ChannelRequest;
import com.jason.ddoMsg.bean.msg.RequestMsisdn;
import com.jason.ddoMsg.task.handler.ChannelRequestHandler;
import com.jason.ddoMsg.util.DateUtil;

public class ChannelRequestHandlerTest extends BaseTaskTest {

	private ChannelRequestHandler channelRequestHandler;
	protected void setUp() throws Exception {
		super.setUp();
		channelRequestHandler = ChannelRequestHandler.getInstance();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

//	public void testHandleChannelRequestBoolean() {
//		ChannelRequest channelRequest = new ChannelRequest();
//		channelRequest.setId("402848814c6f0f3a014c6f0f3a800054");
////		channelRequest.setUsername(username);
//		channelRequest.setChannelId("1");
//		channelRequest.setContent("10000000100000000001");
//		channelRequest.setProductId("100000000001");
//		channelRequest.setSourceType(new Integer(1));
//		channelRequest.setState(new Integer(1));
//		channelRequest.setReturnState(new Integer(1));
//		List<RequestMsisdn> requestMsisdns = new ArrayList<RequestMsisdn>(1);
//		RequestMsisdn requestMsisdn = new RequestMsisdn();
//		requestMsisdn.setId("402848814c6f0f3a014c6f0f3a7f002b");
//		requestMsisdn.setMsisdn(new Long(13950079348L));
//		requestMsisdn.setRequestId("402848814c6f0f3a014c6f0f3a7f002a");
//		requestMsisdns.add(requestMsisdn);
//		channelRequest.setRequestMsisdns(requestMsisdns);
//		this.channelRequestHandler.handle(channelRequest, false);
//	}
	
	public void testHandleChannelRequestDsttime() {
		ChannelRequest channelRequest = new ChannelRequest();
		channelRequest.setId("402848814c6f354f014c6f354f030015");
//		channelRequest.setUsername(username);
		channelRequest.setChannelId("1");
		channelRequest.setContent("10000000100000000001");
		channelRequest.setProductId("100000000001");
		channelRequest.setSourceType(new Integer(1));
		channelRequest.setState(new Integer(1));
		channelRequest.setReturnState(new Integer(1));
		channelRequest.setDstime(DateUtil.parseDate("2015-04-09 17:15:00"));
		List<RequestMsisdn> requestMsisdns = new ArrayList<RequestMsisdn>(1);
		RequestMsisdn requestMsisdn = new RequestMsisdn();
		requestMsisdn.setId("402848814c6f0f3a014c6f0f3a7f002b");
		requestMsisdn.setMsisdn(new Long(13950079348L));
		requestMsisdn.setRequestId("402848814c6f0f3a014c6f0f3a7f002a");
		requestMsisdns.add(requestMsisdn);
		channelRequest.setRequestMsisdns(requestMsisdns);
		this.channelRequestHandler.handle(channelRequest, false);
	}

}
