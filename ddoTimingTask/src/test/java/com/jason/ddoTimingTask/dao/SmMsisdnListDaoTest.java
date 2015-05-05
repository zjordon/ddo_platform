package com.jason.ddoTimingTask.dao;

import java.util.ArrayList;
import java.util.List;

import com.jason.ddoTimingTask.bean.SmMsisdnList;

public class SmMsisdnListDaoTest extends BaseDaoTest {

	private SmMsisdnListDao smMsisdnListDao;
	protected void setUp() throws Exception {
		super.setUp();
		this.smMsisdnListDao = new SmMsisdnListDao();
		this.smMsisdnListDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetSmMsisdnListList() throws Exception {
		List<SmMsisdnList> list = this.smMsisdnListDao.getSmMsisdnListList("402848814cd5cda0014cd5d0c32b0003");
		super.assertNotNull(list);
		super.assertEquals(2, list.size());
	}

	public void testUpdateSmMsisdnList() throws Exception {
		List<SmMsisdnList> list = new ArrayList<SmMsisdnList>();
		SmMsisdnList recordOne = new SmMsisdnList();
		recordOne.setId("402848814cd5cda0014cd5d0c32e0004");
		recordOne.setSmRequestId("402848814d03b543014d03b543270000");
		list.add(recordOne);
		this.smMsisdnListDao.updateSmMsisdnList(list);
	}

}
