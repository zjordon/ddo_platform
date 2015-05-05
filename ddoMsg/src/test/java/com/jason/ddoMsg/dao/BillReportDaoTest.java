package com.jason.ddoMsg.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jason.ddoMsg.bean.msg.BillReport;
import com.jason.ddoMsg.util.UUIDGenerator;

public class BillReportDaoTest extends BaseDaoTest {

	private BillReportDao billReportDao;
	protected void setUp() throws Exception {
		super.setUp();
		this.billReportDao = new BillReportDao();
		this.billReportDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

//	public void testSaveBillReportList() throws DaoException {
//		List<BillReport> reportList = new ArrayList<BillReport>(100);
//		Date current = new Date();
//		for (int i=0;i <100; i++) {
//			BillReport billReport = new BillReport();
//			billReport.setId((new UUIDGenerator()).generate());
//			billReport.setCreateDate(current);
//			billReport.setBillStateCode("00000000");
//			billReport.setTransationId(Integer.toString(i));
//			billReport.setState(new Integer(1));
//			reportList.add(billReport);
//		}
//		this.billReportDao.saveBillReportList(reportList);
//	}
//
//	public void testUpdateResultCode() throws DaoException {
//		this.billReportDao.updateResultCode("402848814c9680e2014c9680e2e70000", 2, 1, "ok", false);
//		this.billReportDao.updateResultCode("402848814c9680e2014c9680e2e70001", 2, 1, "1", true);
//	}
//
//	public void testUpdateState() throws DaoException {
//		this.billReportDao.updateState("402848814c9680e2014c9680e2e70002", 2, 2, true);
//	}

	public void testGetMissTransIdRecord() throws DaoException {
		List<BillReport> reportList = this.billReportDao.getMissTransIdRecord(100);
		super.assertNotNull(reportList);
		System.out.println("MissTransIdRecord size is " + reportList.size());
	}

	public void testGetUnexpectedEndReport() throws DaoException {
		List<BillReport> reportList = this.billReportDao.getUnexpectedEndReport(100);
		super.assertNotNull(reportList);
		System.out.println("UnexpectedEndReport size is " + reportList.size());
		
	}
	
//	public void testGetRepeatReportList() throws DaoException {
//		List<BillReport> reportList = this.billReportDao.getRepeatReportList(100);
//		super.assertNotNull(reportList);
//		super.assertEquals(2, reportList.size());
////		super.assertEquals("402848814c9680e2014c9680e2e80003", reportList.get(0).getId());
//		
//	}

}
