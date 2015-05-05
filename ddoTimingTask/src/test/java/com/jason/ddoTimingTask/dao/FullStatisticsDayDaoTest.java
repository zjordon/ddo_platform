package com.jason.ddoTimingTask.dao;

import com.jason.ddoTimingTask.bean.FullStatisticsDay;
import com.jason.ddoTimingTask.util.UUIDGenerator;

public class FullStatisticsDayDaoTest extends BaseDaoTest {

	private FullStatisticsDayDao fullStatisticsDayDao;
	protected void setUp() throws Exception {
		super.setUp();
		this.fullStatisticsDayDao = new FullStatisticsDayDao();
		this.fullStatisticsDayDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetFullStatisticsDay() throws Exception {
		FullStatisticsDay record = this.fullStatisticsDayDao.getFullStatisticsDay(20150429);
		super.assertNull(record);
		record = this.fullStatisticsDayDao.getFullStatisticsDay(20150409);
		super.assertNotNull(record);
	}

	public void testSaveFullStatisticsDay() throws Exception {
		FullStatisticsDay record = new FullStatisticsDay();
		record.setId((new UUIDGenerator()).generate());
		record.setMsisdnNum(new Integer(1));
		record.setMsgNum(new Integer(1));
		record.setSendSuccessNum(new Integer(0));
		record.setSendFailNum(new Integer(0));
		record.setBillSuccessNum(new Integer(0));
		record.setBillFailNum(new Integer(0));
		record.setSumDate(new Integer(20150428));
		record.setSumAmount(new Double(0.0));
		this.fullStatisticsDayDao.saveFullStatisticsDay(record);
	}

	public void testAddMsgNum() throws Exception {
		this.fullStatisticsDayDao.addMsgNum("e796908aec9e11e4a8a6cde2067539bd", 1);
	}

	public void testAddMsgAndMsisdnNum() throws Exception {
		this.fullStatisticsDayDao.addMsgAndMsisdnNum("e796908aec9e11e4a8a6cde2067539bd", 1, 1);
	}

	public void testAddSendSuccessNum() throws Exception {
		this.fullStatisticsDayDao.addSendSuccessNum("e796908aec9e11e4a8a6cde2067539bd", 1);
	}

	public void testAddBillFailNum() throws Exception {
		this.fullStatisticsDayDao.addBillFailNum("e796908aec9e11e4a8a6cde2067539bd", 1);
	}

	public void testAddSendFailNum() throws Exception {
		this.fullStatisticsDayDao.addSendFailNum("e796908aec9e11e4a8a6cde2067539bd", 1);
	}

	public void testAddBillSuccessNum() throws Exception {
		this.fullStatisticsDayDao.addBillSuccessNum("e796908aec9e11e4a8a6cde2067539bd", 1, 0.1);
	}

	public void testIsMsisdnExist() throws Exception {
//		boolean b = this.fullStatisticsDayDao.isMsisdnExist(20150409, 13950079348L);
//		super.assertFalse(b);
		boolean b = this.fullStatisticsDayDao.isMsisdnExist(20150409, 13950079348L);
		super.assertTrue(b);
	}

}
