package com.jason.ddoMsg.dao;

import java.util.Date;
import java.util.List;

import com.jason.ddoMsg.bean.msg.UpChannelRecord;
import com.jason.ddoMsg.util.UUIDGenerator;

public class UpChannelRecordDaoTest extends BaseDaoTest {
	
	private UpChannelRecordDao upChannelRecordDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		this.upChannelRecordDao = new UpChannelRecordDao();
		this.upChannelRecordDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSaveUpChannelRecord() throws DaoException {
		UpChannelRecord record = new UpChannelRecord();
		record.setId((new UUIDGenerator()).generate());
		record.setCreateDate(new Date());
		record.setResultCode("ok");
		record.setProcessResult(1);
		record.setDdoMsgId("402848814c726de4014c726de4e30001");
		this.upChannelRecordDao.saveUpChannelRecord(record, false);
	}
	
	public void testSaveUpChannelRecordNR() throws DaoException {
		UpChannelRecord record = new UpChannelRecord();
		record.setId((new UUIDGenerator()).generate());
		record.setCreateDate(new Date());
		record.setResultCode("500");
		record.setProcessResult(1);
		record.setDdoMsgId("402848814c726de4014c726de4e30002");
		this.upChannelRecordDao.saveUpChannelRecord(record, true);
	}
	
	public void testUpdateResultCode() throws DaoException {
		Date current = new Date();
		this.upChannelRecordDao.updateResultCode("402848814c979f19014c979f19a20000", "ok", false, current, current);
	}
	
	public void testGetNeedUpChannel() throws DaoException {
		List<UpChannelRecord> records = this.upChannelRecordDao.getNeedUpChannel(100);
		super.assertNotNull(records);
	}

}
