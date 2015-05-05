package com.jason.ddoTimingTask.dao;

import java.util.List;

import com.jason.ddoTimingTask.bean.SendResultRecord;


public class SendResultRecordDaoTest extends BaseDaoTest {

	private SendResultRecordDao sendResultRecordDao;
	protected void setUp() throws Exception {
		super.setUp();
		this.sendResultRecordDao = new SendResultRecordDao();
		this.sendResultRecordDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetSendResultRecordList() throws Exception {
		List<SendResultRecord> list = this.sendResultRecordDao.getSendResultRecordList(500);
		super.assertNotNull(list);
	}

	public void testGetSendResultRecord() throws Exception {
		SendResultRecord record = this.sendResultRecordDao.getSendResultRecord("402848814c726de4014c726de4e30000");
		super.assertNotNull(record);
	}

	public void testUpdateStateProcessed() throws Exception {
		this.sendResultRecordDao.updateStateProcessed("402848814c726de4014c726de4e30001");
	}

}
