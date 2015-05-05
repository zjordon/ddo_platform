package com.jason.ddoTimingTask.dao;

import com.jason.ddoTimingTask.bean.ChannelStatisticsDay;
import com.jason.ddoTimingTask.util.UUIDGenerator;

public class ChannelStatisticsDayDaoTest extends BaseDaoTest {

	private ChannelStatisticsDayDao channelStatisticsDayDao;
	protected void setUp() throws Exception {
		super.setUp();
		this.channelStatisticsDayDao = new ChannelStatisticsDayDao();
		this.channelStatisticsDayDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

//	public void testGetChannelStatisticsDay() throws Exception {
//		ChannelStatisticsDay record = this.channelStatisticsDayDao.getChannelStatisticsDay(20150429, "1");
//		super.assertNull(record);
//		record = this.channelStatisticsDayDao.getChannelStatisticsDay(20150409, "1");
//		super.assertNotNull(record);
//	}

//	public void testSaveChannelStatisticsDay() throws Exception {
//		ChannelStatisticsDay record = new ChannelStatisticsDay();
//		record.setId((new UUIDGenerator()).generate());
//		record.setMsisdnNum(new Integer(1));
//		record.setMsgNum(new Integer(1));
//		record.setSendSuccessNum(new Integer(0));
//		record.setSendFailNum(new Integer(0));
//		record.setBillSuccessNum(new Integer(0));
//		record.setBillFailNum(new Integer(0));
//		record.setSumDate(new Integer(20150428));
//		record.setSumAmount(new Double(0.0));
//		record.setChannelId("1");
//		this.channelStatisticsDayDao.saveChannelStatisticsDay(record);
//	}

	public void testAddMsgNum() throws Exception {
		this.channelStatisticsDayDao.addMsgNum("e79655ccec9e11e4a8a6cde2067539bd", 1);
	}

	public void testAddMsgAndMsisdnNum() throws Exception {
		this.channelStatisticsDayDao.addMsgAndMsisdnNum("e79655ccec9e11e4a8a6cde2067539bd", 1, 1);
	}

	public void testAddSendSuccessNum() throws Exception {
		this.channelStatisticsDayDao.addSendSuccessNum("e79655ccec9e11e4a8a6cde2067539bd", 1);
	}

	public void testAddBillFailNum() throws Exception {
		this.channelStatisticsDayDao.addBillFailNum("e79655ccec9e11e4a8a6cde2067539bd", 1);
	}

	public void testAddSendFailNum() throws Exception {
		this.channelStatisticsDayDao.addSendFailNum("e79655ccec9e11e4a8a6cde2067539bd", 1);
	}

	public void testAddBillSuccessNum() throws Exception {
		this.channelStatisticsDayDao.addBillSuccessNum("e79655ccec9e11e4a8a6cde2067539bd", 1, 0.1);
	}

//	public void testIsMsisdnExist() throws Exception {
//		boolean b = this.channelStatisticsDayDao.isMsisdnExist(20150409, 13950079348L, "1");
//		super.assertFalse(b);
//		b = this.channelStatisticsDayDao.isMsisdnExist(20150409, 13950079348L, "1");
//		super.assertTrue(b);
//	}

}
