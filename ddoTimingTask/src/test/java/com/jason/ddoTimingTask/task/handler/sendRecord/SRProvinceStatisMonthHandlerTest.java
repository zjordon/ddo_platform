package com.jason.ddoTimingTask.task.handler.sendRecord;

import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.task.BaseTaskTest;
import com.jason.ddoTimingTask.task.handler.HandlerException;

public class SRProvinceStatisMonthHandlerTest extends BaseTaskTest {

	private SRProvinceStatisMonthHandler handler;
	protected void setUp() throws Exception {
		super.setUp();
		this.handler = new SRProvinceStatisMonthHandler();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testHandler() throws HandlerException {
		SendRecord sendRecord = new SendRecord();
		sendRecord.setId("402848814c726de4014c726de4e30004");
		sendRecord.setDdoMsgId("402848814c726de4014c726de4e30004");
		sendRecord.setMsisdn(new Long(13950079348L));
		sendRecord.setChannelId("1");
		sendRecord.setBillingBusinessId("1");
		sendRecord.setSendDate(new Integer(20150408));
		sendRecord.calcuateSendMonth();
		sendRecord.setProvinceCode("350000");
		this.handler.handler(sendRecord);
		this.handler.commit();
		sendRecord = new SendRecord();
		sendRecord.setId("402848814c726de4014c726de4e30005");
		sendRecord.setDdoMsgId("402848814c726de4014c726de4e30005");
		sendRecord.setMsisdn(new Long(13950079348L));
		sendRecord.setChannelId("1");
		sendRecord.setBillingBusinessId("1");
		sendRecord.setSendDate(new Integer(20150408));
		sendRecord.calcuateSendMonth();
		sendRecord.setProvinceCode("350000");
		this.handler.handler(sendRecord);
		this.handler.commit();
	}

}
