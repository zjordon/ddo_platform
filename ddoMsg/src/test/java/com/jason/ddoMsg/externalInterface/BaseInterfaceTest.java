package com.jason.ddoMsg.externalInterface;

import com.jason.ddoMsg.cache.CacheManager;

import junit.framework.TestCase;

public class BaseInterfaceTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		CacheManager.getInstance().init();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

}
