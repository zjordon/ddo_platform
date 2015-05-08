package com.jason.ddoTimingTask.task.handler.billResultRecord;

import com.jason.ddoTimingTask.bean.BillResultRecord;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.bean.SendResultRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.BaseTaskTest;
import com.jason.ddoTimingTask.task.handler.HandlerException;

public class BRRChannelStatisMonthHandlerTest extends BaseTaskTest {

	private BRRChannelStatisMonthHandler handler;
	protected void setUp() throws Exception {
		super.setUp();
		this.handler = new BRRChannelStatisMonthHandler();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testHandle() throws HandlerException {
//		BillResultRecord resultRecord = new BillResultRecord();
//		resultRecord.setId("402848814c726de4014c726de4e30000");
//		resultRecord.setDdoMsgId("402848814c726de4014c726de4e30000");
//		resultRecord.setBillResult(new Integer(1));
//		SendRecord sendRecord = this.getSendRecord(resultRecord.getDdoMsgId());
//		if (sendRecord != null && sendRecord.getState() == 1) {
//			SendResultRecord sendResultRecord = this.getSendResultRecord(resultRecord.getDdoMsgId());
//			if (sendResultRecord != null && sendResultRecord.getState() == 1) {
//				if (this.handler.handle(sendRecord, resultRecord)) {
//					this.handler.commit();
//				}
//			}
//		}
		
		BillResultRecord resultRecord = new BillResultRecord();
		resultRecord.setId("402848814c726de4014c726de4e30000");
		resultRecord.setDdoMsgId("402848814c726de4014c726de4e30000");
		resultRecord.setBillResult(new Integer(0));
		SendRecord sendRecord = this.getSendRecord(resultRecord.getDdoMsgId());
		if (sendRecord != null && sendRecord.getState() == 1) {
			SendResultRecord sendResultRecord = this.getSendResultRecord(resultRecord.getDdoMsgId());
			if (sendResultRecord != null && sendResultRecord.getState() == 1) {
				if (this.handler.handle(sendRecord, resultRecord)) {
					this.handler.commit();
				}
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
	
	private SendResultRecord getSendResultRecord(String ddoMsgId) throws HandlerException {
		SendResultRecord record = null;
		try {
			record = DaoManager.getInstance().getSendResultRecordDao().getSendResultRecord(ddoMsgId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return record;
	}

}
