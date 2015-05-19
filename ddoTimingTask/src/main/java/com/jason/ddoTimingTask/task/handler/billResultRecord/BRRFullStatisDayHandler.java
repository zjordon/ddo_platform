/**
 * 
 */
package com.jason.ddoTimingTask.task.handler.billResultRecord;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.BillResultRecord;
import com.jason.ddoTimingTask.bean.FullStatisticsDay;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;

/**
 * 按日统计全量计费结果流程
 * @author jasonzhang
 *
 */
public class BRRFullStatisDayHandler extends AbstractBRRStatisHandler {
	private static final Logger logger = Logger
			.getLogger(BRRFullStatisDayHandler.class);

	private FullStatisticsDay fsdRecord;
	private boolean isFail;
	private String billBusinessId;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jason.ddoTimingTask.task.handler.billResultRecord.
	 * AbstractBRRStatisHandler#commit()
	 */
	@Override
	public void commit() throws HandlerException {
		try {
			if (this.isFail) {

				DaoManager.getInstance().getFullStatisticsDayDao()
						.addBillFailNum(this.fsdRecord.getId(), 1);

			} else {
				double price = DaoManager.getInstance().getBillBusinessDao()
						.getPrice(this.billBusinessId);
				DaoManager.getInstance().getFullStatisticsDayDao()
						.addBillSuccessNum(this.fsdRecord.getId(), 1, price);
			}
		} catch (DaoException e) {
			logger.error("exception when commit", e);
			throw new HandlerException(e.getMessage());
		}
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
		this.fsdRecord = this.getFullStatisticsDay(sendRecord.getSendDate());
		return (this.fsdRecord != null);
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

	@Override
	protected boolean isExistMsisdn(SendRecord sendRecord)
			throws HandlerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void addMsisdnRecord(SendRecord sendRecord)
			throws HandlerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void increaseMsisdnNum() throws HandlerException {
		// TODO Auto-generated method stub
		
	}

}
