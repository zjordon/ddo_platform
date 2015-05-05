package com.jason.ddoMsg.cache;

import com.jason.ddoMsg.util.DmMobile;

import junit.framework.TestCase;

public class DmMobileCacheTest extends TestCase {

	DmMobileCache cache = null;

	protected void setUp() throws Exception {
		super.setUp();
		cache = new DmMobileCache();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	 public void testInit() {
		 this.cache.init();
		 DmMobile object = this.cache.getDmMobile(13399999898L);
			super.assertNull(object);
			object = this.cache.getDmMobile(13055201008L);
			super.assertNull(object);
			object = this.cache.getDmMobile(13950079348L);
			super.assertNotNull(object);
			super.assertEquals(350000, object.provinceCode);
			super.assertEquals(350200, object.cityCode);
			object = this.cache.getDmMobile(13707889348L);
			super.assertNotNull(object);
			super.assertEquals(450000, object.provinceCode);
			super.assertEquals(450100, object.cityCode);
	 }

//	public void testGetDmMobile() {
//		DmMobile[] array = new DmMobile[] {
//				new DmMobile(1339999, 650000, 654000),
//				new DmMobile(1370000, 210000, 210100),
//				new DmMobile(1370001, 210000, 210100),
//				new DmMobile(1370002, 210000, 210100),
//				new DmMobile(1370771, 450000, 450100)};
//		this.cache.setMobileArray(array);
//		DmMobile object = this.cache.getDmMobile(13399999898L);
//		super.assertNull(object);
//		object = this.cache.getDmMobile(13055201008L);
//		super.assertNull(object);
//		object = this.cache.getDmMobile(13950079348L);
//		super.assertNull(object);
//		object = this.cache.getDmMobile(13707719348L);
//		super.assertNotNull(object);
//		super.assertEquals(450000, object.provinceCode);
//		super.assertEquals(450100, object.cityCode);
//	}
}
