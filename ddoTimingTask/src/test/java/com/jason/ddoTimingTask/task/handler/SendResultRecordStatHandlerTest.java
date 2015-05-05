package com.jason.ddoTimingTask.task.handler;

import com.jason.ddoTimingTask.bean.SendResultRecord;
import com.jason.ddoTimingTask.task.BaseTaskTest;

public class SendResultRecordStatHandlerTest extends BaseTaskTest {

	private SendResultRecordStatHandler sendResultRecordStatHandler;
	protected void setUp() throws Exception {
		super.setUp();
		this.sendResultRecordStatHandler = SendResultRecordStatHandler.getInstacne();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testHandle() throws HandlerException {
		SendResultRecord sendResultRecord = new SendResultRecord();
		sendResultRecord.setId("402848814c726de4014c726de4e30000");
		sendResultRecord.setDdoMsgId("402848814c726de4014c726de4e30000");
		sendResultRecord.setSendResult(new Integer(1));
		this.sendResultRecordStatHandler.handle(sendResultRecord);
		sendResultRecord = new SendResultRecord();
		sendResultRecord.setId("402848814c726de4014c726de4e30005");
		sendResultRecord.setDdoMsgId("402848814c726de4014c726de4e30005");
		sendResultRecord.setSendResult(new Integer(0));
		this.sendResultRecordStatHandler.handle(sendResultRecord);
	}

}
