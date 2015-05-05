package com.jason.ddoTimingTask.dao;

import java.util.List;

import com.jason.ddoTimingTask.bean.SendRecord;


public class SendRecordDaoTest extends BaseDaoTest {

	private SendRecordDao sendRecordDao;
	protected void setUp() throws Exception {
		super.setUp();
		this.sendRecordDao = new SendRecordDao();
		this.sendRecordDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetSendRecordList() throws Exception {
		List<SendRecord> list = this.sendRecordDao.getSendRecordList(500);
		super.assertNotNull(list);
	}

	public void testGetSendRecord() throws Exception {
		SendRecord record = this.sendRecordDao.getSendRecord("402848814c726de4014c726de4e30000");
		super.assertNotNull(record);
	}

	public void testUpdateStateProcessed() throws Exception {
		this.sendRecordDao.updateStateProcessed("402848814c726de4014c726de4e30001");
	}

}
