package com.jason.ddoTimingTask.dao;

import java.util.Date;
import java.util.List;

import com.jason.ddoTimingTask.bean.SmTask;

public class SmTaskDaoTest extends BaseDaoTest {
	
	private SmTaskDao smTaskDao;

	protected void setUp() throws Exception {
		super.setUp();
		this.smTaskDao = new SmTaskDao();
		this.smTaskDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetSmTaskList() throws Exception {
		List<SmTask> list = this.smTaskDao.getSmTaskList(500);
		super.assertNotNull(list);
	}

	public void testUpdateStateSuccess() throws Exception {
		this.smTaskDao.updateStateSuccess("402848814cd5bef0014cd5c4082b0002", new Date());
	}

	public void testUpdateStateFail() throws Exception {
		this.smTaskDao.updateStateFail("402848814cd5bef0014cd5c91d640004", "test", new Date());
	}

}
