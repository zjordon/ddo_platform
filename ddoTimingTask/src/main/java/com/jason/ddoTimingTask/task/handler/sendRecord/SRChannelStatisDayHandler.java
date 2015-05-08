/**
 * 
 */
package com.jason.ddoTimingTask.task.handler.sendRecord;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.ChannelStatisticsDay;
import com.jason.ddoTimingTask.bean.ChannelStatisticsMsisdn;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;
import com.jason.ddoTimingTask.util.UUIDGenerator;

/**
 *  按日统计渠道发送量流程
 * @author jasonzhang
 *
 */
public class SRChannelStatisDayHandler extends AbstractSRStatisHandler {
	private static final Logger logger = Logger
			.getLogger(SRChannelStatisDayHandler.class);
	
	private ChannelStatisticsDay csdRecord;
	private ChannelStatisticsMsisdn csMsisdn;

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#commit()
	 */
	@Override
	public void commit() throws HandlerException {
		try {
			if (this.csMsisdn != null) {
				DaoManager.getInstance().getChannelStatisticsDayDao().saveMsisdn(this.csMsisdn.getMsisdn(), this.csMsisdn.getSumDate(), this.csMsisdn.getChannelId());
			} else {
				DaoManager.getInstance().getChannelStatisticsDayDao().addMsgNum(this.csdRecord.getId(), 1);
			}
		} catch (DaoException e) {
			logger.error("exception when commit", e);
			throw new HandlerException(e.getMessage());
		}
		this.csdRecord = null;
		this.csMsisdn = null;
		
	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#isExistStatisRecord(com.jason.ddoTimingTask.bean.SendRecord)
	 */
	@Override
	protected boolean isExistStatisRecord(SendRecord sendRecord)
			throws HandlerException {
		ChannelStatisticsDay record = this.getChannelStatisticsDay(sendRecord.getSendDate(), sendRecord.getChannelId());
		if (record != null) {
			this.csdRecord = record;
		}
		return record != null;
	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#addStatisRecord(com.jason.ddoTimingTask.bean.SendRecord)
	 */
	@Override
	protected void addStatisRecord(SendRecord sendRecord)
			throws HandlerException {
		this.initCsdRecord(sendRecord.getSendDate(), sendRecord.getChannelId());

	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#isExistMsisdn(com.jason.ddoTimingTask.bean.SendRecord)
	 */
	@Override
	protected boolean isExistMsisdn(SendRecord sendRecord)
			throws HandlerException {
		boolean exist = false;
		try {
			exist = DaoManager.getInstance().getChannelStatisticsDayDao().isMsisdnExist(sendRecord.getSendDate(), sendRecord.getMsisdn(), sendRecord.getChannelId());
		} catch (DaoException e) {
			logger.error("exception when isExistCSMsisdn", e);
			throw new HandlerException(e.getMessage());
		}
		return exist;
	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#addMsisdnRecord(com.jason.ddoTimingTask.bean.SendRecord)
	 */
	@Override
	protected void addMsisdnRecord(SendRecord sendRecord)
			throws HandlerException {
		this.csMsisdn = new ChannelStatisticsMsisdn();
		this.csMsisdn.setMsisdn(sendRecord.getMsisdn().longValue());
		this.csMsisdn.setSumDate(sendRecord.getSendDate());
		this.csMsisdn.setChannelId(sendRecord.getChannelId());

	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#increaseMsisdnNum()
	 */
	@Override
	protected void increaseMsisdnNum() throws HandlerException {
		this.csdRecord.increaseMsisdnNum();

	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#increaseMsgNum()
	 */
	@Override
	protected void increaseMsgNum() throws HandlerException {
		this.csdRecord.increaseMsgNum();

	}
	
	private ChannelStatisticsDay getChannelStatisticsDay(int sumDate, String channelId) throws HandlerException {
		ChannelStatisticsDay record = null;
		try {
			record = DaoManager.getInstance().getChannelStatisticsDayDao().getChannelStatisticsDay(sumDate, channelId);
		} catch (DaoException e) {
			logger.error("exception when getChannelStatisticsDay", e);
			throw new HandlerException(e.getMessage());
		}
		return record;
	}
	
	private void initCsdRecord(int sumDate, String channelId) {
		this.csdRecord = new ChannelStatisticsDay();
		csdRecord.setId((new UUIDGenerator()).generate());
		csdRecord.setMsgNum(new Integer(0));
		csdRecord.setMsisdnNum(new Integer(0));
		csdRecord.setSendSuccessNum(new Integer(0));
		csdRecord.setSendFailNum(new Integer(0));
		csdRecord.setBillSuccessNum(new Integer(0));
		csdRecord.setBillFailNum(new Integer(0));
		csdRecord.setSumAmount(new Double(0));
		csdRecord.setSumDate(sumDate);
		csdRecord.setChannelId(channelId);
		//新增记录，设置持久化状态为新增
		csdRecord.setPersistenceState((short)0);
	}

}
