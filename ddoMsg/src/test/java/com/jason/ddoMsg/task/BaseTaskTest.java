package com.jason.ddoMsg.task;

import com.jason.ddoMsg.cache.CacheManager;

import junit.framework.TestCase;

public class BaseTaskTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		CacheManager.getInstance().init();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
