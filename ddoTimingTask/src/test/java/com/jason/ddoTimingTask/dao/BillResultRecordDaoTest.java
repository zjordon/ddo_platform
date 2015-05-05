package com.jason.ddoTimingTask.dao;

import java.util.List;

import com.jason.ddoTimingTask.bean.BillResultRecord;

public class BillResultRecordDaoTest extends BaseDaoTest {

	private BillResultRecordDao billResultRecordDao;
	protected void setUp() throws Exception {
		super.setUp();
		this.billResultRecordDao = new BillResultRecordDao();
		this.billResultRecordDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetBillResultRecordList() throws Exception {
		List<BillResultRecord> list = this.billResultRecordDao.getBillResultRecordList(500);
		super.assertNotNull(list);
	}

	public void testUpdateStateProcessed() throws Exception {
		this.billResultRecordDao.updateStateProcessed("402848814c726de4014c726de4e30000");
	}

}
