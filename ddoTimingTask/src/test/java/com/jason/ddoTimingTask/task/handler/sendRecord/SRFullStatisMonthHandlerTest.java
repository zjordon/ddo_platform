package com.jason.ddoTimingTask.task.handler.sendRecord;

import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.task.BaseTaskTest;

public class SRFullStatisMonthHandlerTest extends BaseTaskTest {

	private SRFullStatisMonthHandler handler;
	protected void setUp() throws Exception {
		super.setUp();
		this.handler = new SRFullStatisMonthHandler();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testHandler() throws Exception {
//		SendRecord sendRecord = new SendRecord();
//		sendRecord.setId("402848814c726de4014c726de4e30004");
//		sendRecord.setDdoMsgId("402848814c726de4014c726de4e30004");
//		sendRecord.setMsisdn(new Long(13950079348L));
//		sendRecord.setChannelId("1");
//		sendRecord.setBillingBusinessId("1");
//		sendRecord.setSendDate(new Integer(20150408));
//		sendRecord.calcuateSendMonth();
//		this.handler.handler(sendRecord);
//		this.handler.commit();
		SendRecord sendRecord = new SendRecord();
		sendRecord.setId("402848814c726de4014c726de4e30005");
		sendRecord.setDdoMsgId("402848814c726de4014c726de4e30004");
		sendRecord.setMsisdn(new Long(13950079348L));
		sendRecord.setChannelId("1");
		sendRecord.setBillingBusinessId("1");
		sendRecord.setSendDate(new Integer(20150408));
		sendRecord.calcuateSendMonth();
		this.handler.handler(sendRecord);
		this.handler.commit();
	}

}
