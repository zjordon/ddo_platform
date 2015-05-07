/**
 * 
 */
package com.jason.ddoTimingTask.task.handler;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.FullStatisticsDay;
import com.jason.ddoTimingTask.bean.FullStatisticsMsisdn;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.util.UUIDGenerator;

/**
 * 按日统计全量发送量流程
 * 
 * @author jasonzhang
 *
 */
public class SRFullStatisDayHandler extends AbstractSRStatisHandler {
	private static final Logger logger = Logger
			.getLogger(SRFullStatisDayHandler.class);

	private FullStatisticsDay fsdRecord;
	private FullStatisticsMsisdn fsMsisdn;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#commit()
	 */
	@Override
	public void commit() throws HandlerException {
		try {
			if (this.fsMsisdn != null) {

				DaoManager
						.getInstance()
						.getFullStatisticsDayDao()
						.saveMsisdn(this.fsMsisdn.getMsisdn(),
								this.fsMsisdn.getSumDate());

			} else {
				DaoManager.getInstance().getFullStatisticsDayDao()
						.addMsgNum(fsdRecord.getId(), 1);
			}
		} catch (DaoException e) {
			logger.error("exception when commit", e);
			throw new HandlerException(e.getMessage());
		}
		this.fsdRecord = null;
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
		FullStatisticsDay record = this.getFullStatisticsDay(sendRecord
				.getSendDate());
		if (record != null) {
			this.fsdRecord = record;
			// 设置持久化状态为已存在
			this.fsdRecord.setPersistenceState((short) 1);
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
		this.initFsdRecord(sendRecord.getSendDate());

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
					.getFullStatisticsDayDao()
					.isMsisdnExist(sendRecord.getSendDate(),
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
		this.fsMsisdn = new FullStatisticsMsisdn();
		this.fsMsisdn.setMsisdn(sendRecord.getMsisdn().longValue());
		this.fsMsisdn.setSumDate(sendRecord.getSendDate().intValue());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#
	 * increaseMsisdnNum()
	 */
	@Override
	protected void increaseMsisdnNum() throws HandlerException {
		this.fsdRecord.increaseMsisdnNum();

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
		this.fsdRecord.increaseMsgNum();

	}

	private FullStatisticsDay getFullStatisticsDay(int date)
			throws HandlerException {
		FullStatisticsDay record = null;
		try {
			record = DaoManager.getInstance().getFullStatisticsDayDao()
					.getFullStatisticsDay(date);
		} catch (DaoException e) {
			logger.error("exception when getFullStatisticsDay", e);
			throw new HandlerException(e.getMessage());
		}

		return record;
	}

	private void initFsdRecord(int sumDate) {
		this.fsdRecord = new FullStatisticsDay();
		fsdRecord.setId((new UUIDGenerator()).generate());
		fsdRecord.setMsgNum(new Integer(0));
		fsdRecord.setMsisdnNum(new Integer(0));
		fsdRecord.setSendSuccessNum(new Integer(0));
		fsdRecord.setSendFailNum(new Integer(0));
		fsdRecord.setBillSuccessNum(new Integer(0));
		fsdRecord.setBillFailNum(new Integer(0));
		fsdRecord.setSumAmount(new Double(0));
		fsdRecord.setSumDate(sumDate);
		// 新增记录，设置持久化状态为新增
		fsdRecord.setPersistenceState((short) 0);
	}

}
