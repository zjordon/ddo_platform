package com.jason.ddoMsg.cache;

import junit.framework.TestCase;

public class ConfigCacheTest extends TestCase {
	
	private ConfigCache configCache;

	protected void setUp() throws Exception {
		super.setUp();
		configCache = new ConfigCache();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLoadConfigParam() {
		this.configCache.loadConfigParam();
		super.assertEquals("http://218.207.208.30:8080/pae/soap/ExtendedServices", this.configCache.getDdoUrl());
		super.assertFalse(this.configCache.isStopAll());
	}

//	public void testSetStopAll() throws CacheException {
//		this.configCache.setStopAll(true);
//		super.assertTrue(this.configCache.isStopAll());
//		this.configCache.setStopAll(false);
//		super.assertFalse(this.configCache.isStopAll());
//	}

}
