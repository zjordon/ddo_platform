package com.jason.ddoTimingTask.task.handler;

import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.task.BaseTaskTest;

public class SendRecordStatisticsHandlerTest extends BaseTaskTest {

	private SendRecordStatisticsHandler sendRecordStatisticsHandler;
	protected void setUp() throws Exception {
		super.setUp();
		this.sendRecordStatisticsHandler = SendRecordStatisticsHandler.getInstacne();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		this.sendRecordStatisticsHandler = SendRecordStatisticsHandler.getInstacne();
	}

	public void testHandle() throws HandlerException {
//		SendRecord sendRecord = new SendRecord();
//		sendRecord.setId("402848814c726de4014c726de4e30000");
//		sendRecord.setDdoMsgId("402848814c726de4014c726de4e30000");
//		sendRecord.setMsisdn(new Long(13950079348L));
//		sendRecord.setChannelId("1");
//		sendRecord.setBillingBusinessId("1");
//		sendRecord.setSendDate(new Integer(20150409));
//		this.sendRecordStatisticsHandler.handle(sendRecord);
		
		SendRecord sendRecord = new SendRecord();
		sendRecord.setId("402848814c726de4014c726de4e30004");
		sendRecord.setDdoMsgId("402848814c726de4014c726de4e30004");
		sendRecord.setMsisdn(new Long(13950079348L));
		sendRecord.setChannelId("1");
		sendRecord.setBillingBusinessId("1");
		sendRecord.setSendDate(new Integer(20150408));
		this.sendRecordStatisticsHandler.handle(sendRecord);
		sendRecord = new SendRecord();
		sendRecord.setId("402848814c726de4014c726de4e30005");
		sendRecord.setDdoMsgId("402848814c726de4014c726de4e30004");
		sendRecord.setMsisdn(new Long(13950079348L));
		sendRecord.setChannelId("1");
		sendRecord.setBillingBusinessId("1");
		sendRecord.setSendDate(new Integer(20150408));
		this.sendRecordStatisticsHandler.handle(sendRecord);
	}

}
