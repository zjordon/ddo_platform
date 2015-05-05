package com.jason.ddoMsg.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jason.ddoMsg.bean.msg.ChannelRequest;
import com.jason.ddoMsg.bean.msg.RequestMsisdn;
import com.jason.ddoMsg.util.UUIDGenerator;

public class ChannelRequestDaoTest extends BaseDaoTest {
	private ChannelRequestDao channelRequestDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		this.channelRequestDao = new ChannelRequestDao();
		this.channelRequestDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSaveChannelRequest() throws DaoException {
		List<ChannelRequest> requestList = new ArrayList<ChannelRequest>();
		for (int i=0; i<10; i++) {
			ChannelRequest request = new ChannelRequest();
			request.setId((new UUIDGenerator()).generate());
			request.setUsername("test");
			request.setPassword("test");
			request.setContent("test");
			request.setProductId("1234");
			request.setRequestTime(new Date());
			request.setState(1);
			request.setChannelId("1");
			request.setSourceType(1);
			request.setReturnState(1);
			List<RequestMsisdn> requestMsisdns = new ArrayList<RequestMsisdn>();
			for (int j=0; j<20; j++) {
				RequestMsisdn msisdn = new RequestMsisdn();
				msisdn.setId((new UUIDGenerator()).generate());
				msisdn.setMsisdn(13950079348L);
				msisdn.setRequestId(request.getId());
				requestMsisdns.add(msisdn);
			}
			request.setRequestMsisdns(requestMsisdns);
			requestList.add(request);
		}
		
		this.channelRequestDao.saveChannelRequest(requestList);
	}

	public void testUpdateProcessResult() throws DaoException {
		Date current = new Date();
		this.channelRequestDao.updateProcessResult("402848814c6f0f3a014c6f0f3a7d0000", 2, current, current, 1);
	}

	public void testUpdateState() throws DaoException {
		this.channelRequestDao.updateState("402848814c6f0f3a014c6f0f3a7e0015", 0);
	}

	public void testGetPendingTimingRequests() throws DaoException {
		Date current = new Date();
		List<ChannelRequest> channelRequestList = this.channelRequestDao.getPendingTimingRequests(current, 100);
		super.assertNotNull(channelRequestList);
		super.assertEquals(1, channelRequestList.size());
	}

	public void testGetUnexpectedEndRequests() throws DaoException {
		List<ChannelRequest> channelRequestList = this.channelRequestDao.getUnexpectedEndRequests(100);
		super.assertNotNull(channelRequestList);
		super.assertEquals(0, channelRequestList.size());
	}

}
