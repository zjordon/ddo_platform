/**
 * 
 */
package com.jason.ddoTimingTask.task.handler.billResultRecord;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.BillResultRecord;
import com.jason.ddoTimingTask.bean.PSMsisdnMonth;
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

	private ProvinceStatisticsMonth psmRecord;
	private boolean isFail;
	private String billBusinessId;
	private PSMsisdnMonth psMsisdn;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jason.ddoTimingTask.task.handler.billResultRecord.
	 * AbstractBRRStatisHandler#commit()
	 */
	@Override
	public void commit() throws HandlerException {
		try {
			if (this.psMsisdn != null) {
				DaoManager.getInstance().getProvinceStatisticsMonthDao().saveMsisdn(this.psMsisdn.getMsisdn(), this.psMsisdn.getSumMonth(), this.psMsisdn.getProvinceCode());
			}
			if (this.isFail) {
				if (this.psMsisdn != null) {
					DaoManager.getInstance().getProvinceStatisticsMonthDao()
					.addBillFailAndMsisdnNum(this.psmRecord.getId(), 1, 1);
				} else {
					DaoManager.getInstance().getProvinceStatisticsMonthDao()
					.addBillFailNum(this.psmRecord.getId(), 1);
				}
				
			} else {
				double price = DaoManager.getInstance().getBillBusinessDao()
						.getPrice(this.billBusinessId);
				if (this.psMsisdn != null) {
					DaoManager.getInstance().getProvinceStatisticsMonthDao()
					.addBillSuccessAndMsisdnNum(this.psmRecord.getId(), 1, price, 1);
				} else {
					DaoManager.getInstance().getProvinceStatisticsMonthDao()
					.addBillSuccessNum(this.psmRecord.getId(), 1, price);
				}
				
			}
		} catch (DaoException e) {
			logger.error("exception when commit", e);
			throw new HandlerException(e.getMessage());
		}
		this.psmRecord = null;
		this.psMsisdn = null;
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
		this.psmRecord = this.getProvinceStatisticsMonth(sendRecord.getSendMonth(), sendRecord.getProvinceCode());
		return this.psmRecord != null;
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

	@Override
	protected boolean isExistMsisdn(SendRecord sendRecord)
			throws HandlerException {
		boolean exist = false;
		try {
			exist = DaoManager.getInstance().getProvinceStatisticsMonthDao().isMsisdnExist(sendRecord.getSendMonth().intValue(), sendRecord.getMsisdn().longValue(), sendRecord.getProvinceCode());
		} catch (DaoException e) {
			logger.error("exception when isExistMsisdn", e);
			throw new HandlerException(e.getMessage());
		}
		return exist;
	}

	@Override
	protected void addMsisdnRecord(SendRecord sendRecord)
			throws HandlerException {
		this.psMsisdn = new PSMsisdnMonth();
		this.psMsisdn.setMsisdn(sendRecord.getMsisdn().longValue());
		this.psMsisdn.setProvinceCode(sendRecord.getProvinceCode());
		this.psMsisdn.setSumMonth(sendRecord.getSendMonth().intValue());
		
	}

	@Override
	protected void increaseMsisdnNum() throws HandlerException {
		this.psmRecord.increaseMsisdnNum();
		
	}

}
