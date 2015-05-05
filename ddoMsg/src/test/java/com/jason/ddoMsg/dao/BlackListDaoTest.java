package com.jason.ddoMsg.dao;

import java.util.List;

import com.jason.ddoMsg.bean.blacklist.BlackList;

public class BlackListDaoTest extends BaseDaoTest {

	private BlackListDao blackListDao = null;
	protected void setUp() throws Exception {
		super.setUp();
		blackListDao = new BlackListDao();
		blackListDao.setDataSource(super.dataSource);
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
	}

	public void testGetBlackLists() {
		List<Long> blackListList = null;
		try {
			blackListList = blackListDao.getBlackLists();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		super.assertNotNull(blackListList);
	}

}
