package com.jason.ddoMsg.dao;

import java.util.List;

import com.jason.ddoMsg.bean.channel.ProviceCloseState;

public class ProviceCloseStateDaoTest extends BaseDaoTest {
	
	private ProviceCloseStateDao proviceCloseStateDao;

	protected void setUp() throws Exception {
		super.setUp();
		this.proviceCloseStateDao = new ProviceCloseStateDao();
		this.proviceCloseStateDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetProviceCloseStates() throws DaoException {
		List<ProviceCloseState> list = this.proviceCloseStateDao.getProviceCloseStates();
		super.assertNotNull(list);
	}

}
