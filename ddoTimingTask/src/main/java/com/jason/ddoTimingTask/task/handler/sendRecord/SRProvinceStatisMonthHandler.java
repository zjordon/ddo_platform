/**
 * 
 */
package com.jason.ddoTimingTask.task.handler.sendRecord;

import org.apache.log4j.Logger;

//import com.jason.ddoTimingTask.bean.PSMsisdnMonth;
import com.jason.ddoTimingTask.bean.ProvinceStatisticsMonth;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;
import com.jason.ddoTimingTask.util.UUIDGenerator;

/**
 * 按月统计分省发送量流程
 * @author jasonzhang
 *
 */
public class SRProvinceStatisMonthHandler extends AbstractSRStatisHandler {
	
	private static final Logger logger = Logger
			.getLogger(SRProvinceStatisMonthHandler.class);
	
	private ProvinceStatisticsMonth psmRecord;
//	private PSMsisdnMonth psMsisdn;

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.sendRecord.AbstractSRStatisHandler#commit()
	 */
	@Override
	public void commit() throws HandlerException {
		try {
//			if (this.psMsisdn != null) {
//				DaoManager.getInstance().getProvinceStatisticsMonthDao().saveMsisdn(this.psMsisdn.getMsisdn(), this.psMsisdn.getSumMonth(), this.psMsisdn.getProvinceCode());
//			}
//			if (this.psmRecord.getPersistenceState() == 0) {
//				DaoManager.getInstance().getProvinceStatisticsMonthDao().saveProvinceStatisticsMonth(this.psmRecord);
//			} else if (this.psMsisdn == null){
//				DaoManager.getInstance().getProvinceStatisticsMonthDao().addMsgNum(this.psmRecord.getId(), 1);
//			} else if (this.psMsisdn != null) {
//				DaoManager.getInstance().getProvinceStatisticsMonthDao().addMsgAndMsisdnNum(this.psmRecord.getId(), 1, 1);
//			}
			
			if (this.psmRecord.getPersistenceState() == 0) {
				DaoManager.getInstance().getProvinceStatisticsMonthDao().saveProvinceStatisticsMonth(this.psmRecord);
			} else {
				DaoManager.getInstance().getProvinceStatisticsMonthDao().addMsgNum(this.psmRecord.getId(), 1);
			}
		} catch (DaoException e) {
			logger.error("exception when commit", e);
			throw new HandlerException(e.getMessage());
		}
		this.psmRecord = null;
//		this.psMsisdn = null;

	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.sendRecord.AbstractSRStatisHandler#isExistStatisRecord(com.jason.ddoTimingTask.bean.SendRecord)
	 */
	@Override
	protected boolean isExistStatisRecord(SendRecord sendRecord)
			throws HandlerException {
		this.psmRecord = this.getProvinceStatisticsMonth(sendRecord.getSendMonth(), sendRecord.getProvinceCode());
		if (this.psmRecord != null) {
			this.psmRecord.setPersistenceState((short)1);
		}
		return this.psmRecord != null;
	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.sendRecord.AbstractSRStatisHandler#addStatisRecord(com.jason.ddoTimingTask.bean.SendRecord)
	 */
	@Override
	protected void addStatisRecord(SendRecord sendRecord)
			throws HandlerException {
		this.initPsdRecord(sendRecord.getSendMonth().intValue(), sendRecord.getProvinceCode());

	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.sendRecord.AbstractSRStatisHandler#isExistMsisdn(com.jason.ddoTimingTask.bean.SendRecord)
	 */
//	@Override
//	protected boolean isExistMsisdn(SendRecord sendRecord)
//			throws HandlerException {
//		boolean exist = false;
//		try {
//			exist = DaoManager.getInstance().getProvinceStatisticsMonthDao().isMsisdnExist(sendRecord.getSendMonth().intValue(), sendRecord.getMsisdn().longValue(), sendRecord.getProvinceCode());
//		} catch (DaoException e) {
//			logger.error("exception when isExistMsisdn", e);
//			throw new HandlerException(e.getMessage());
//		}
//		return exist;
//	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.sendRecord.AbstractSRStatisHandler#addMsisdnRecord(com.jason.ddoTimingTask.bean.SendRecord)
	 */
//	@Override
//	protected void addMsisdnRecord(SendRecord sendRecord)
//			throws HandlerException {
//		this.psMsisdn = new PSMsisdnMonth();
//		this.psMsisdn.setMsisdn(sendRecord.getMsisdn().longValue());
//		this.psMsisdn.setProvinceCode(sendRecord.getProvinceCode());
//		this.psMsisdn.setSumMonth(sendRecord.getSendMonth().intValue());
//
//	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.sendRecord.AbstractSRStatisHandler#increaseMsisdnNum()
	 */
//	@Override
//	protected void increaseMsisdnNum() throws HandlerException {
//		this.psmRecord.increaseMsisdnNum();
//
//	}

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.handler.sendRecord.AbstractSRStatisHandler#increaseMsgNum()
	 */
	@Override
	protected void increaseMsgNum() throws HandlerException {
		this.psmRecord.increaseMsgNum();

	}
	
	private ProvinceStatisticsMonth getProvinceStatisticsMonth(int sumMonth, String provinceCode) throws HandlerException {
		ProvinceStatisticsMonth record = null;
		try {
			record = DaoManager.getInstance().getProvinceStatisticsMonthDao().getProvinceStatisticsMonth(sumMonth, provinceCode);
		} catch (DaoException e) {
			logger.error("exception when getProvinceStatisticsMonth", e);
			throw new HandlerException(e.getMessage());
		}
		return record;
	}
	
	private void initPsdRecord(int sumMonth, String provinceCode) {
		this.psmRecord = new ProvinceStatisticsMonth();
		psmRecord.setId((new UUIDGenerator()).generate());
		psmRecord.setMsgNum(new Integer(0));
		psmRecord.setMsisdnNum(new Integer(0));
		psmRecord.setSendSuccessNum(new Integer(0));
		psmRecord.setSendFailNum(new Integer(0));
		psmRecord.setBillSuccessNum(new Integer(0));
		psmRecord.setBillFailNum(new Integer(0));
		psmRecord.setSumAmount(new Double(0));
		psmRecord.setSumMonth(sumMonth);
		psmRecord.setProvinceCode(provinceCode);
		//新增记录，设置持久化状态为新增
		psmRecord.setPersistenceState((short)0);
	}

}
