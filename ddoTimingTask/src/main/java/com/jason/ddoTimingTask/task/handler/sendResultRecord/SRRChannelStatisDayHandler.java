/**
 * 
 */
package com.jason.ddoTimingTask.task.handler.sendResultRecord;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.ChannelStatisticsDay;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.bean.SendResultRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;

/**
 * 按日统计渠道发送结果流程
 * 
 * @author jasonzhang
 *
 */
public class SRRChannelStatisDayHandler extends AbstractSRRStatisHandler {

	private static final Logger logger = Logger
			.getLogger(SRRChannelStatisDayHandler.class);

	private ChannelStatisticsDay csdRecord;
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

				DaoManager.getInstance().getChannelStatisticsDayDao()
						.addSendSuccessNum(this.csdRecord.getId(), 1);

			} else {
				DaoManager.getInstance().getChannelStatisticsDayDao()
						.addSendFailNum(this.csdRecord.getId(), 1);
			}
		} catch (DaoException e) {
			logger.error("exception when commit", e);
			throw new HandlerException(e.getMessage());
		}
		this.csdRecord = null;
		this.isFail = false;
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
		this.csdRecord = this.getChannelStatisticsDay(sendRecord.getSendDate(),
				sendRecord.getChannelId());
		return this.csdRecord != null;
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
