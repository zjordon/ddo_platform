package com.jason.ddoMsg.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jason.ddoMsg.bean.msg.BillReport;
import com.jason.ddoMsg.task.handler.BillReportHandler;
import com.jason.ddoMsg.util.UUIDGenerator;

public class BillReportHandlerTest extends BaseTaskTest {

	private BillReportHandler billReportHandler;
	protected void setUp() throws Exception {
		super.setUp();
		billReportHandler = BillReportHandler.getInstance();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
/*
	public void testHandleMissTransId() {
		List<BillReport> reportList = new ArrayList<BillReport>(100);
		Date current = new Date();
		BillReport billReport = new BillReport();
		billReport.setId("402848814c9680e2014c9680e2e80005");
		billReport.setCreateDate(current);
		billReport.setBillStateCode("00000000");
		billReport.setTransationId("5");
		billReport.setState(new Integer(1));
		reportList.add(billReport);
//		for (int i=5;i <100; i++) {
//			BillReport billReport = new BillReport();
//			billReport.setId((new UUIDGenerator()).generate());
//			billReport.setCreateDate(current);
//			billReport.setBillStateCode("00000000");
//			billReport.setTransationId(Integer.toString(i));
//			billReport.setState(new Integer(1));
//			reportList.add(billReport);
//		}
		
		this.billReportHandler.handle(reportList);
	}
	*/
	public void testHandle() {
		List<BillReport> reportList = new ArrayList<BillReport>(100);
		Date current = new Date();
		BillReport billReport = new BillReport();
		billReport.setId("402848814c9680e2014c9680e2e80006");
		billReport.setCreateDate(current);
		billReport.setBillStateCode("00000000");
		billReport.setTransationId("124");
		billReport.setState(new Integer(1));
		reportList.add(billReport);
		this.billReportHandler.handle(reportList);
	}

}
