/**
 * 
 */
package com.jason.ddoMsg.cache;

import javax.sql.DataSource;

import com.jason.ddoMsg.util.DataSourceUtil;

import junit.framework.TestCase;

/**
 * @author jasonzhang
 *
 */
public class BaseCacheTest extends TestCase {

	protected DataSource dataSource;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.dataSource = DataSourceUtil.getDataSource();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		if (this.dataSource != null) {
			DataSourceUtil.destoryDataSource(this.dataSource);
		}
	}

}
