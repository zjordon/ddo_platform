/**
 * 
 */
package com.jason.ddoTimingTask.task.handler.sendRecord;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.ChannelStatisticsMonth;
import com.jason.ddoTimingTask.bean.CSMsisdnMonth;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;
import com.jason.ddoTimingTask.util.UUIDGenerator;

/**
 *  按月统计渠道发送量流程
 * @author jasonzhang
 *
 */
public class SRChannelStatisMonthHandler extends AbstractSRStatisHandler {
	private static final Logger logger = Logger
			.getLogger(SRChannelStatisMonthHandler.class);
	
	private ChannelStatisticsMonth csmRecord;
	private CSMsisdnMonth csMsisdn;

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#commit()
	 */
	@Override
	public void commit() throws HandlerException {
		try {
			if (this.csMsisdn != null) {
				DaoManager.getInstance().getChannelStatisticsMonthDao().saveMsisdn(this.csMsisdn.getMsisdn(), this.csMsisdn.getSumMonth(), this.csMsisdn.getChannelId());
			}
			if (this.csmRecord.getPersistenceState() == 0) {
				DaoManager.getInstance().getChannelStatisticsMonthDao().saveChannelStatisticsMonth(this.csmRecord);
			} else {
				DaoManager.getInstance().getChannelStatisticsMonthDao().addMsgNum(this.csmRecord.getId(), 1);
			}
		} catch (DaoException e) {
			logger.error("exception when commit", e);
			throw new HandlerException(e.getMessage());
		}
		this.csmRecord = null;
		this.csMsisdn = null;
		
	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#isExistStatisRecord(com.jason.ddoTimingTask.bean.SendRecord)
	 */
	@Override
	protected boolean isExistStatisRecord(SendRecord sendRecord)
			throws HandlerException {
		ChannelStatisticsMonth record = this.getChannelStatisticsMonth(sendRecord.getSendMonth(), sendRecord.getChannelId());
		if (record != null) {
			this.csmRecord = record;
			this.csmRecord.setPersistenceState((short)1);
		}
		return record != null;
	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#addStatisRecord(com.jason.ddoTimingTask.bean.SendRecord)
	 */
	@Override
	protected void addStatisRecord(SendRecord sendRecord)
			throws HandlerException {
		this.initCsdRecord(sendRecord.getSendMonth(), sendRecord.getChannelId());

	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#isExistMsisdn(com.jason.ddoTimingTask.bean.SendRecord)
	 */
	@Override
	protected boolean isExistMsisdn(SendRecord sendRecord)
			throws HandlerException {
		boolean exist = false;
		try {
			exist = DaoManager.getInstance().getChannelStatisticsMonthDao().isMsisdnExist(sendRecord.getSendMonth(), sendRecord.getMsisdn(), sendRecord.getChannelId());
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
		this.csMsisdn = new CSMsisdnMonth();
		this.csMsisdn.setMsisdn(sendRecord.getMsisdn().longValue());
		this.csMsisdn.setSumMonth(sendRecord.getSendMonth());
		this.csMsisdn.setChannelId(sendRecord.getChannelId());

	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#increaseMsisdnNum()
	 */
	@Override
	protected void increaseMsisdnNum() throws HandlerException {
		this.csmRecord.increaseMsisdnNum();

	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.AbstractSRStatisHandler#increaseMsgNum()
	 */
	@Override
	protected void increaseMsgNum() throws HandlerException {
		this.csmRecord.increaseMsgNum();

	}
	
	private ChannelStatisticsMonth getChannelStatisticsMonth(int sumMonth, String channelId) throws HandlerException {
		ChannelStatisticsMonth record = null;
		try {
			record = DaoManager.getInstance().getChannelStatisticsMonthDao().getChannelStatisticsMonth(sumMonth, channelId);
		} catch (DaoException e) {
			logger.error("exception when getChannelStatisticsMonth", e);
			throw new HandlerException(e.getMessage());
		}
		return record;
	}
	
	private void initCsdRecord(int sumMonth, String channelId) {
		this.csmRecord = new ChannelStatisticsMonth();
		csmRecord.setId((new UUIDGenerator()).generate());
		csmRecord.setMsgNum(new Integer(0));
		csmRecord.setMsisdnNum(new Integer(0));
		csmRecord.setSendSuccessNum(new Integer(0));
		csmRecord.setSendFailNum(new Integer(0));
		csmRecord.setBillSuccessNum(new Integer(0));
		csmRecord.setBillFailNum(new Integer(0));
		csmRecord.setSumAmount(new Double(0));
		csmRecord.setSumMonth(sumMonth);
		csmRecord.setChannelId(channelId);
		//新增记录，设置持久化状态为新增
		csmRecord.setPersistenceState((short)0);
	}

}
