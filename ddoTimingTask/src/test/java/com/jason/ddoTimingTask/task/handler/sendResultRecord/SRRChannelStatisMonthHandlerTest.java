package com.jason.ddoTimingTask.task.handler.sendResultRecord;

import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.bean.SendResultRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.BaseTaskTest;
import com.jason.ddoTimingTask.task.handler.HandlerException;

public class SRRChannelStatisMonthHandlerTest extends BaseTaskTest {

	private SRRChannelStatisMonthHandler handler;
	protected void setUp() throws Exception {
		super.setUp();
		this.handler = new SRRChannelStatisMonthHandler();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testHandle() throws HandlerException {
//		SendResultRecord sendResultRecord = new SendResultRecord();
//		sendResultRecord.setId("402848814c726de4014c726de4e30000");
//		sendResultRecord.setDdoMsgId("402848814c726de4014c726de4e30000");
//		sendResultRecord.setSendResult(new Integer(1));
//		SendRecord sendRecord = this.getSendRecord(sendResultRecord.getDdoMsgId());
//		if (sendRecord != null && sendRecord.getState() == 1) {
//			if (this.handler.handle(sendRecord, sendResultRecord)) {
//				this.handler.commit();
//			}
//		}
		SendResultRecord sendResultRecord = new SendResultRecord();
		sendResultRecord.setId("402848814c726de4014c726de4e30005");
		sendResultRecord.setDdoMsgId("402848814c726de4014c726de4e30005");
		sendResultRecord.setSendResult(new Integer(0));
		SendRecord sendRecord = this.getSendRecord(sendResultRecord.getDdoMsgId());
		if (sendRecord != null && sendRecord.getState() == 1) {
			if (this.handler.handle(sendRecord, sendResultRecord)) {
				this.handler.commit();
			}
		}
		
	}
	
	private SendRecord getSendRecord(String ddoMsgId) throws HandlerException {
		SendRecord record = null;
		try {
			record = DaoManager.getInstance().getSendRecordDao().getSendRecord(ddoMsgId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return record;
	}

}
