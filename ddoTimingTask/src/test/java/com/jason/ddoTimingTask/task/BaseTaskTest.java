package com.jason.ddoTimingTask.task;

import com.jason.ddoTimingTask.dao.DaoManager;

import junit.framework.TestCase;

public class BaseTaskTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		DaoManager.getInstance().init();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
