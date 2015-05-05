package com.jason.ddoTimingTask.dao;

import java.util.Date;

import com.jason.ddoTimingTask.bean.SmRequest;
import com.jason.ddoTimingTask.util.UUIDGenerator;

public class SmRequestDaoTest extends BaseDaoTest {
	
	private SmRequestDao smRequestDao;

	protected void setUp() throws Exception {
		super.setUp();
		this.smRequestDao = new SmRequestDao();
		this.smRequestDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSaveSmRequest() throws Exception {
		SmRequest smRequest = new SmRequest();
		smRequest.setId((new UUIDGenerator()).generate());
		smRequest.setResponseNo("1");
		smRequest.setResponseState(new Integer(1));
		smRequest.setResponseTime(new Date());
		smRequest.setSendTime(new Date());
		smRequest.setSmTaskId("402848814cd5bef0014cd5c4082b0002");
		this.smRequestDao.saveSmRequest(smRequest);
	}

}
