/**
 * 
 */
package com.jason.ddoTimingTask.task.handler.billResultRecord;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.BillResultRecord;
import com.jason.ddoTimingTask.bean.ChannelStatisticsDay;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;

/**
 * 按日统计渠道计费结果流程
 * 
 * @author jasonzhang
 *
 */
public class BRRChannelStatisDayHandler extends AbstractBRRStatisHandler {

	private static final Logger logger = Logger
			.getLogger(BRRChannelStatisDayHandler.class);

	private ChannelStatisticsDay csdRecord;
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

				DaoManager.getInstance().getChannelStatisticsDayDao()
						.addBillFailNum(this.csdRecord.getId(), 1);

			} else {
				double price = DaoManager.getInstance().getBillBusinessDao()
						.getPrice(this.billBusinessId);
				DaoManager.getInstance().getChannelStatisticsDayDao()
						.addBillSuccessNum(this.csdRecord.getId(), 1, price);
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
		this.csdRecord = this.getChannelStatisticsDay(sendRecord.getSendDate(),
				sendRecord.getChannelId());
		return (this.csdRecord != null);
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

	private ChannelStatisticsDay getChannelStatisticsDay(int sumDate,
			String channelId) throws HandlerException {
		ChannelStatisticsDay record = null;
		try {
			record = DaoManager.getInstance().getChannelStatisticsDayDao()
					.getChannelStatisticsDay(sumDate, channelId);
		} catch (DaoException e) {
			logger.error("exception when getChannelStatisticsDay", e);
			throw new HandlerException(e.getMessage());
		}
		return record;
	}

}
