/**
 * 
 */
package com.jason.ddoTimingTask.task.handler.billResultRecord;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.BillResultRecord;
import com.jason.ddoTimingTask.bean.ProvinceStatisticsMonth;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;

/**
 * @author jasonzhang
 *
 */
public class BRRProvinceStatisMonthHandler extends AbstractBRRStatisHandler {

	private static final Logger logger = Logger
			.getLogger(BRRProvinceStatisMonthHandler.class);

	private ProvinceStatisticsMonth csmRecord;
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
				DaoManager.getInstance().getProvinceStatisticsMonthDao()
						.addBillFailNum(this.csmRecord.getId(), 1);
			} else {
				double price = DaoManager.getInstance().getBillBusinessDao()
						.getPrice(this.billBusinessId);
				DaoManager.getInstance().getProvinceStatisticsMonthDao()
						.addBillSuccessNum(this.csmRecord.getId(), 1, price);
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
		this.csmRecord = this.getProvinceStatisticsMonth(sendRecord.getSendMonth(), sendRecord.getProvinceCode());
		return this.csmRecord != null;
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

	private ProvinceStatisticsMonth getProvinceStatisticsMonth(int sumMonth,
			String channelId) throws HandlerException {
		ProvinceStatisticsMonth record = null;
		try {
			record = DaoManager.getInstance().getProvinceStatisticsMonthDao()
					.getProvinceStatisticsMonth(sumMonth, channelId);
		} catch (DaoException e) {
			logger.error("exception when getProvinceStatisticsMonth", e);
			throw new HandlerException(e.getMessage());
		}
		return record;
	}

}