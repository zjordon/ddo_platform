/**
 * 
 */
package com.jason.ddoMsg.event;

import com.jason.ddoMsg.cache.CacheManager;

import junit.framework.TestCase;

/**
 * @author jasonzhang
 *
 */
public class BaseEventTest extends TestCase {

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
