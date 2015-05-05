package com.jason.ddoTimingTask.dao;

import javax.sql.DataSource;

import com.jason.ddoTimingTask.util.DataSourceUtil;

import junit.framework.TestCase;

public class BaseDaoTest extends TestCase {
	
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
