/**
 * 
 */
package com.jason.ddoTimingTask.task.handler;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.ChannelStatisticsDay;
import com.jason.ddoTimingTask.bean.FullStatisticsDay;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;

/**
 * 统计处理器的基类
 * @author jasonzhang
 *
 */
public abstract class BaseStaticsHandler {
	private static final Logger logger = Logger
			.getLogger(BaseStaticsHandler.class);

	protected FullStatisticsDay getFullStatisticsDay(int date) throws HandlerException {
		FullStatisticsDay record = null;
		try {
			record = DaoManager.getInstance().getFullStatisticsDayDao().getFullStatisticsDay(date);
		} catch (DaoException e) {
			logger.error("exception when getFullStatisticsDay", e);
			throw new HandlerException(e.getMessage());
		}
		
		return record;
	}
	
	protected ChannelStatisticsDay getChannelStatisticsDay(int sumDate, String channelId) throws HandlerException {
		ChannelStatisticsDay record = null;
		try {
			record = DaoManager.getInstance().getChannelStatisticsDayDao().getChannelStatisticsDay(sumDate, channelId);
		} catch (DaoException e) {
			logger.error("exception when getChannelStatisticsDay", e);
			throw new HandlerException(e.getMessage());
		}
		return record;
	}
	
	protected SendRecord getSendRecord(String ddoMsgId) throws HandlerException {
		SendRecord record = null;
		try {
			record = DaoManager.getInstance().getSendRecordDao().getSendRecord(ddoMsgId);
		} catch (DaoException e) {
			logger.error("exception when getSendRecord", e);
			throw new HandlerException(e.getMessage());
		}
		return record;
	}
}
