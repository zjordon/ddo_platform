package com.jason.ddoTimingTask.task.handler;

import com.jason.ddoTimingTask.task.BaseTaskTest;
import com.jason.ddoTimingTask.bean.BillResultRecord;

public class BillResultRecordStatHandlerTest extends BaseTaskTest {

	private  BillResultRecordStatHandler  billResultRecordStatHandler;
	protected void setUp() throws Exception {
		super.setUp();
		this.billResultRecordStatHandler =  BillResultRecordStatHandler.getInstacne();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testHandle() throws HandlerException {
		BillResultRecord record = new BillResultRecord();
		record.setId("402848814c726de4014c726de4e30000");
		record.setDdoMsgId("402848814c726de4014c726de4e30000");
		record.setBillResult(new Integer(1));
		this.billResultRecordStatHandler.handle(record);
//		BillResultRecord record = new BillResultRecord();
//		record.setId("402848814c726de4014c726de4e30004");
//		record.setDdoMsgId("402848814c726de4014c726de4e30004");
//		record.setBillResult(new Integer(0));
//		this.billResultRecordStatHandler.handle(record);
	}

}
