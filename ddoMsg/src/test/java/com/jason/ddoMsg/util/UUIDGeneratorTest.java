package com.jason.ddoMsg.util;

import junit.framework.TestCase;

public class UUIDGeneratorTest extends TestCase {

	public void testGenerate() {
		UUIDGenerator generator = new UUIDGenerator();
		String id = generator.generate();
		super.assertEquals(32,id.length());
	}

}
