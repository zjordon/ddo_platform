/**
 * 
 */
package com.jason.ddoTimingTask.task.handler.sendRecord;

import org.apache.log4j.Logger;




import com.jason.ddoTimingTask.bean.FullStatisticsMonth;
import com.jason.ddoTimingTask.bean.FSMsisdnMonth;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;
import com.jason.ddoTimingTask.util.UUIDGenerator;

/**
 *  按月统计全量发送量流程
 * @author jasonzhang
 *
 */
public class SRFullStatisMonthHandler extends AbstractSRStatisHandler {
	private static final Logger logger = Logger
			.getLogger(SRFullStatisMonthHandler.class);
	
	private FullStatisticsMonth fsmRecord;
	private FSMsisdnMonth fsMsisdn;

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#commit()
	 */
	@Override
	public void commit() throws HandlerException {
		try {
			if (this.fsMsisdn != null) {

				DaoManager
						.getInstance()
						.getFullStatisticsMonthDao()
						.saveMsisdn(this.fsMsisdn.getMsisdn(),
								this.fsMsisdn.getSumMonth());

			} else {
				DaoManager.getInstance().getFullStatisticsMonthDao()
						.addMsgNum(fsmRecord.getId(), 1);
			}
			if (this.fsmRecord.getPersistenceState() == 0) {
				DaoManager.getInstance().getFullStatisticsMonthDao().saveFullStatisticsMonth(this.fsmRecord);
			}
		} catch (DaoException e) {
			logger.error("exception when commit", e);
			throw new HandlerException(e.getMessage());
		}
		this.fsmRecord = null;
		this.fsMsisdn = null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#
	 * isExistStatisRecord(com.jason.ddoTimingTask.bean.SendRecord)
	 */
	@Override
	protected boolean isExistStatisRecord(SendRecord sendRecord)
			throws HandlerException {
		FullStatisticsMonth record = this.getFullStatisticsMonth(sendRecord
				.getSendMonth());
		if (record != null) {
			this.fsmRecord = record;
			// 设置持久化状态为已存在
			this.fsmRecord.setPersistenceState((short) 1);
		}
		return (record != null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#addStatisRecord
	 * (com.jason.ddoTimingTask.bean.SendRecord)
	 */
	@Override
	protected void addStatisRecord(SendRecord sendRecord)
			throws HandlerException {
		this.initFsdRecord(sendRecord.getSendMonth());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#isExistMsisdn
	 * (com.jason.ddoTimingTask.bean.SendRecord)
	 */
	@Override
	protected boolean isExistMsisdn(SendRecord sendRecord)
			throws HandlerException {
		boolean exist = false;
		try {
			exist = DaoManager
					.getInstance()
					.getFullStatisticsMonthDao()
					.isMsisdnExist(sendRecord.getSendMonth(),
							sendRecord.getMsisdn());
		} catch (DaoException e) {
			logger.error("exception when isExistFSMsisdn", e);
			throw new HandlerException(e.getMessage());
		}
		return exist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#addMsisdnRecord
	 * (com.jason.ddoTimingTask.bean.SendRecord)
	 */
	@Override
	protected void addMsisdnRecord(SendRecord sendRecord)
			throws HandlerException {
		this.fsMsisdn = new FSMsisdnMonth();
		this.fsMsisdn.setMsisdn(sendRecord.getMsisdn().longValue());
		this.fsMsisdn.setSumMonth(sendRecord.getSendMonth().intValue());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#
	 * increaseMsisdnNum()
	 */
	@Override
	protected void increaseMsisdnNum() throws HandlerException {
		this.fsmRecord.increaseMsisdnNum();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#increaseMsgNum
	 * ()
	 */
	@Override
	protected void increaseMsgNum() throws HandlerException {
		this.fsmRecord.increaseMsgNum();

	}

	private FullStatisticsMonth getFullStatisticsMonth(int date)
			throws HandlerException {
		FullStatisticsMonth record = null;
		try {
			record = DaoManager.getInstance().getFullStatisticsMonthDao()
					.getFullStatisticsMonth(date);
		} catch (DaoException e) {
			logger.error("exception when getFullStatisticsMonth", e);
			throw new HandlerException(e.getMessage());
		}

		return record;
	}

	private void initFsdRecord(int sumDate) {
		this.fsmRecord = new FullStatisticsMonth();
		fsmRecord.setId((new UUIDGenerator()).generate());
		fsmRecord.setMsgNum(new Integer(0));
		fsmRecord.setMsisdnNum(new Integer(0));
		fsmRecord.setSendSuccessNum(new Integer(0));
		fsmRecord.setSendFailNum(new Integer(0));
		fsmRecord.setBillSuccessNum(new Integer(0));
		fsmRecord.setBillFailNum(new Integer(0));
		fsmRecord.setSumAmount(new Double(0));
		fsmRecord.setSumMonth(sumDate);
		// 新增记录，设置持久化状态为新增
		fsmRecord.setPersistenceState((short) 0);
	}

}
