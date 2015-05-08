/**
 * 
 */
package com.jason.ddoTimingTask.task.handler.sendResultRecord;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.ChannelStatisticsMonth;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.bean.SendResultRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;

/**
 * 按月统计渠道发送结果流程
 * 
 * @author jasonzhang
 *
 */
public class SRRChannelStatisMonthHandler extends AbstractSRRStatisHandler {

	private static final Logger logger = Logger
			.getLogger(SRRChannelStatisMonthHandler.class);

	private ChannelStatisticsMonth csmRecord;
	private boolean isFail;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jason.ddoTimingTask.task.handler.sendResultRecord.
	 * AbstractSRRStatisHandler#commit()
	 */
	@Override
	public void commit() throws HandlerException {
		try {
			if (this.isFail) {

				DaoManager.getInstance().getChannelStatisticsMonthDao()
						.addSendFailNum(this.csmRecord.getId(), 1);

			} else {
				DaoManager.getInstance().getChannelStatisticsMonthDao()
						.addSendSuccessNum(this.csmRecord.getId(), 1);
			}
		} catch (DaoException e) {
			logger.error("exception when commit", e);
			throw new HandlerException(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jason.ddoTimingTask.task.handler.sendResultRecord.
	 * AbstractSRRStatisHandler
	 * #isExistStatisRecord(com.jason.ddoTimingTask.bean.SendRecord)
	 */
	@Override
	protected boolean isExistStatisRecord(SendRecord sendRecord)
			throws HandlerException {
		this.csmRecord = this.getChannelStatisticsMonth(
				sendRecord.getSendMonth(), sendRecord.getChannelId());
		return this.csmRecord != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jason.ddoTimingTask.task.handler.sendResultRecord.
	 * AbstractSRRStatisHandler
	 * #isSuccess(com.jason.ddoTimingTask.bean.SendResultRecord)
	 */
	@Override
	protected boolean isSuccess(SendResultRecord record)
			throws HandlerException {
		return record.getSendResult().intValue() == 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jason.ddoTimingTask.task.handler.sendResultRecord.
	 * AbstractSRRStatisHandler#increaseFailNum()
	 */
	@Override
	protected void increaseFailNum() {
		this.isFail = true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jason.ddoTimingTask.task.handler.sendResultRecord.
	 * AbstractSRRStatisHandler#increaseSuccessNum()
	 */
	@Override
	protected void increaseSuccessNum() {
		this.isFail = false;

	}

	private ChannelStatisticsMonth getChannelStatisticsMonth(int sumMonth,
			String channelId) throws HandlerException {
		ChannelStatisticsMonth record = null;
		try {
			record = DaoManager.getInstance().getChannelStatisticsMonthDao()
					.getChannelStatisticsMonth(sumMonth, channelId);
		} catch (DaoException e) {
			logger.error("exception when getChannelStatisticsMonth", e);
			throw new HandlerException(e.getMessage());
		}
		return record;
	}

}
