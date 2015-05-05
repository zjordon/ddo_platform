/**
 * 
 */
package com.jason.ddoMsg.cache;

import com.jason.ddoMsg.dao.BlackListDao;

/**
 * @author jasonzhang
 *
 */
public class BlackListCacheTest extends BaseCacheTest {
	private BlackListCache blackListCache;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		BlackListDao blackListDao = new BlackListDao();
		blackListDao.setDataSource(super.dataSource);
		this.blackListCache = new BlackListCache();
		this.blackListCache.setBlackListDao(blackListDao);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	public void testLoadBlackListList() throws CacheException {
		this.blackListCache.loadBlackListList();
		long[] blackListList = this.blackListCache.getBlackListList();
		super.assertNotNull(blackListList);
		this.printBlackListList(blackListList);
	}

	public void testAddBlackList() throws ElementExistsException {
		this.setupBlackListList();
		this.blackListCache.addBlackList(13950079346L);
		long[] blackListList = this.blackListCache.getBlackListList();
		super.assertNotNull(blackListList);
		this.printBlackListList(blackListList);
		super.assertEquals(13950079346L, blackListList[2]);
		super.assertEquals(13950079349L, blackListList[4]);
		
	}
	
	public void testAddBlackListTE() {
		this.setupBlackListList();
		ElementExistsException excepedEx = null;
		try {
			this.blackListCache.addBlackList(13394001001L);
		} catch (ElementExistsException e) {
			excepedEx = e;
		}
		super.assertNotNull(excepedEx);
		long[] blackListList = this.blackListCache.getBlackListList();
		super.assertNotNull(blackListList);
		this.printBlackListList(blackListList);
		super.assertEquals(13950079348L, blackListList[2]);
		
	}

	
	public void testDeleteBlackList() {
		this.setupBlackListList();
		ElementNotFoundException excepedEx = null;
		try {
			this.blackListCache.deleteBlackList(13394001002L);
		} catch (ElementNotFoundException e) {
			excepedEx = e;
		}
		super.assertNotNull(excepedEx);
		long[] blackListList = this.blackListCache.getBlackListList();
		super.assertNotNull(blackListList);
		this.printBlackListList(blackListList);
		super.assertEquals(13950079348L, blackListList[2]);
	}
	
	public void testDeleteBlackListTE() throws ElementNotFoundException {
		this.setupBlackListList();
		this.blackListCache.deleteBlackList(13394001001L);
		long[] blackListList = this.blackListCache.getBlackListList();
		super.assertNotNull(blackListList);
		this.printBlackListList(blackListList);
		super.assertEquals(13950079348L, blackListList[2]);
		super.assertEquals(13950079349L, blackListList[2]);
	} 

	
	public void testIsExists() throws ElementExistsException {
		this.setupBlackListList();
		boolean flag = this.blackListCache.isExists(13950079349L);
		super.assertTrue(flag);
		flag = this.blackListCache.isExists(13950079340L);
		super.assertFalse(flag);
		this.blackListCache.addBlackList(13950079340L);
		flag = this.blackListCache.isExists(13950079340L);
		super.assertTrue(flag);
	}
	
	private void printBlackListList(long[] msisdns) {
		for (int i=0; i<msisdns.length; i++) {
			System.out.println(msisdns[i]);
		}
	}
	
	private void setupBlackListList() {
		long[] msisdns = new long[]{13055201008L, 13950079349L, 13394001001L, 13950079348L};
		this.blackListCache.setBlackListList(msisdns);
	}

}
