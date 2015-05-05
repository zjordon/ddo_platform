package com.jason.ddoMsg.dao;

import java.util.List;

import javax.sql.DataSource;

import com.jason.ddoMsg.bean.channel.BillBusiness;
import com.jason.ddoMsg.util.DataSourceUtil;

import junit.framework.TestCase;

public class BillBusinessDaoTest extends TestCase {
	private DataSource dataSource;
	private BillBusinessDao billBusinessDao;

	protected void setUp() throws Exception {
		super.setUp();
		this.dataSource = DataSourceUtil.getDataSource();
		billBusinessDao = new BillBusinessDao();
		billBusinessDao.setDataSource(this.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		if (this.dataSource != null) {
			DataSourceUtil.destoryDataSource(this.dataSource);
		}
	}

	public void testGetBillBusinesses() {
		List<BillBusiness> billBusinessList = null;
		try {
			billBusinessList = this.billBusinessDao.getBillBusinesses();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		super.assertNotNull(billBusinessList);
	}

}
