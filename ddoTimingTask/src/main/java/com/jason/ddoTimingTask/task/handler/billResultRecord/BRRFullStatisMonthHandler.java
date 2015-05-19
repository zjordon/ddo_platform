/**
 * 
 */
package com.jason.ddoTimingTask.task.handler.billResultRecord;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.BillResultRecord;
import com.jason.ddoTimingTask.bean.FSMsisdnMonth;
import com.jason.ddoTimingTask.bean.FullStatisticsMonth;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;

/**
 * 按月统计全量计费结果流程
 * 
 * @author jasonzhang
 *
 */
public class BRRFullStatisMonthHandler extends AbstractBRRStatisHandler {

	private static final Logger logger = Logger
			.getLogger(BRRFullStatisMonthHandler.class);

	private FullStatisticsMonth fsmRecord;
	private boolean isFail;
	private String billBusinessId;
	private FSMsisdnMonth fsMsisdn;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jason.ddoTimingTask.task.handler.billResultRecord.
	 * AbstractBRRStatisHandler#commit()
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

			}
			if (this.isFail) {
				if (this.fsMsisdn != null) {
					DaoManager.getInstance().getFullStatisticsMonthDao()
					.addBillFailAndMsisdnNum(this.fsmRecord.getId(), 1, 1);
				} else {
					DaoManager.getInstance().getFullStatisticsMonthDao()
					.addBillFailNum(this.fsmRecord.getId(), 1);
				}
				

			} else {
				double price = DaoManager.getInstance().getBillBusinessDao()
						.getPrice(this.billBusinessId);
				if (this.fsMsisdn != null) {
					DaoManager.getInstance().getFullStatisticsMonthDao()
					.addBillSuccessAndMsisdnNum(this.fsmRecord.getId(), 1, price, 1);
				} else {
					DaoManager.getInstance().getFullStatisticsMonthDao()
					.addBillSuccessNum(this.fsmRecord.getId(), 1, price);
				}
				
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
	 * @see com.jason.ddoTimingTask.task.handler.billResultRecord.
	 * AbstractBRRStatisHandler
	 * #isExistStatisRecord(com.jason.ddoTimingTask.bean.SendRecord)
	 */
	@Override
	protected boolean isExistStatisRecord(SendRecord sendRecord)
			throws HandlerException {
		this.billBusinessId = sendRecord.getBillingBusinessId();
		this.fsmRecord = this.getFullStatisticsMonth(sendRecord.getSendMonth());
		return this.fsmRecord != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jason.ddoTimingTask.task.handler.billResultRecord.
	 * AbstractBRRStatisHandler
	 * #isSuccess(com.jason.ddoTimingTask.bean.BillResultRecord)
	 */
	@Override
	protected boolean isSuccess(BillResultRecord record)
			throws HandlerException {
		return record.getBillResult().intValue() == 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jason.ddoTimingTask.task.handler.billResultRecord.
	 * AbstractBRRStatisHandler#increaseFailNum()
	 */
	@Override
	protected void increaseFailNum() {
		this.isFail = true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jason.ddoTimingTask.task.handler.billResultRecord.
	 * AbstractBRRStatisHandler#increaseSuccessNum()
	 */
	@Override
	protected void increaseSuccessNum() {
		this.isFail = false;

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

	@Override
	protected void addMsisdnRecord(SendRecord sendRecord)
			throws HandlerException {
		this.fsMsisdn = new FSMsisdnMonth();
		this.fsMsisdn.setMsisdn(sendRecord.getMsisdn().longValue());
		this.fsMsisdn.setSumMonth(sendRecord.getSendMonth().intValue());
		
	}

	@Override
	protected void increaseMsisdnNum() throws HandlerException {
		this.fsmRecord.increaseMsgNum();
		
	}

}
