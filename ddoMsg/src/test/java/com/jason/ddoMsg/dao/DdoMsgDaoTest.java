package com.jason.ddoMsg.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jason.ddoMsg.bean.msg.DdoMsg;
import com.jason.ddoMsg.util.UUIDGenerator;

public class DdoMsgDaoTest extends BaseDaoTest {
	
	private DdoMsgDao ddoMsgDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		this.ddoMsgDao = new DdoMsgDao();
		this.ddoMsgDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

//	public void testSaveMsgList() throws DaoException {
//		List<DdoMsg> msgList = new ArrayList<DdoMsg>();
//		for (int i=0; i<100; i++) {
//			DdoMsg ddoMsg = new DdoMsg();
//			ddoMsg.setId((new UUIDGenerator()).generate());
//			ddoMsg.setMsisdn(new Long(13950079348L));
//			ddoMsg.setBillingBusinessId("1");
//			ddoMsg.setSendResult(0);
//			ddoMsg.setMsisdnProvinceCode("059");
//			ddoMsg.setMsisdnCityCode("0592");
//			ddoMsg.setChannelId("1");
//			ddoMsg.setRequestId("402848814c6f0f3a014c6f0f3a7f002a");
//			msgList.add(ddoMsg);
//		}
//		
//		this.ddoMsgDao.saveMsgList(msgList);
//	}

//	public void testUpdateMsgTransationId() throws DaoException {
//		this.ddoMsgDao.updateMsgTransationId("402848814c726de4014c726de4e30000", "123", "00000000", 1, new Date());
//	}

//	public void testUpdateMsgRetMsgCode() throws DaoException {
//		this.ddoMsgDao.updateMsgRetMsgCode("402848814c726de4014c726de4e30003", "00539999", 1, new Date(), true);
//	}

//	public void testUpdateMsgBillingCode() throws DaoException {
//		this.ddoMsgDao.updateMsgBillingCode("402848814c726de4014c726de4e30000", "00000000", new Date());
//	}

//	public void testGetDdoMsgs() throws DaoException {
//		List<String> transationIds = new ArrayList<String>();
//		transationIds.add("123");
//		List<DdoMsg> list = this.ddoMsgDao.getDdoMsgs(transationIds);
//		super.assertNotNull(list);
//		super.assertEquals(1, list.size());
//		super.assertEquals("123", list.get(0).getTransationId());
//	}

//	public void testGetDdoMsgByReqId() throws DaoException {
//		List<DdoMsg> list = this.ddoMsgDao.getDdoMsgByReqId("402848814c6f0f3a014c6f0f3a8300bd");
//		super.assertNotNull(list);
//		super.assertEquals(100, list.size());
//	}
	
	public void testGetNeedRepeatMsg() throws DaoException {
		List<DdoMsg> records = this.ddoMsgDao.getNeedRepeatMsg(100);
		super.assertNotNull(records);
		System.out.println(records.size());
	}

}
