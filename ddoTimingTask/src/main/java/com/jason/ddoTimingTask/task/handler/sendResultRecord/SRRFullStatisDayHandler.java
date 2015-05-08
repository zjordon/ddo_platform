/**
 * 
 */
package com.jason.ddoTimingTask.task.handler.sendResultRecord;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.FullStatisticsDay;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.bean.SendResultRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;

/**
 * 按日统计全量发送发送结果流程
 * 
 * @author jasonzhang
 *
 */
public class SRRFullStatisDayHandler extends AbstractSRRStatisHandler {

	private static final Logger logger = Logger
			.getLogger(SRRFullStatisDayHandler.class);

	private FullStatisticsDay fsdRecord;
	private boolean isFail;

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
		this.fsdRecord = this.getFullStatisticsDay(sendRecord.getSendDate());
		return (this.fsdRecord != null);
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
	public void commit() throws HandlerException {
		try {
			if (this.isFail) {

				DaoManager.getInstance().getFullStatisticsDayDao()
						.addSendFailNum(this.fsdRecord.getId(), 1);

			} else {
				DaoManager.getInstance().getFullStatisticsDayDao()
						.addSendSuccessNum(this.fsdRecord.getId(), 1);
			}
		} catch (DaoException e) {
			logger.error("exception when commit", e);
			throw new HandlerException(e.getMessage());
		}
		this.fsdRecord = null;
		this.isFail = false;
	}

}
