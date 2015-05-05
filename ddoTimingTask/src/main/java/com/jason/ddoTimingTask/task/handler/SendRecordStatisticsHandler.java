/**
 * 
 */
package com.jason.ddoTimingTask.task.handler;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.ChannelStatisticsDay;
import com.jason.ddoTimingTask.bean.ChannelStatisticsMsisdn;
import com.jason.ddoTimingTask.bean.FullStatisticsDay;
import com.jason.ddoTimingTask.bean.FullStatisticsMsisdn;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.util.UUIDGenerator;

/**
 * 发送量统计处理器
 * @author jasonzhang
 *
 */
public class SendRecordStatisticsHandler extends BaseStaticsHandler {
	
	private static final Logger logger = Logger
			.getLogger(SendRecordStatisticsHandler.class);
	
	private final static SendRecordStatisticsHandler instance = new SendRecordStatisticsHandler();
	
	private SendRecordStatisticsHandler(){}
	
	public final static SendRecordStatisticsHandler getInstacne() {
		return instance;
	}

	public void handle(SendRecord sendRecord) throws HandlerException {
		//根据日期在全量表中查找对应记录
		FullStatisticsDay fsdRecord = super.getFullStatisticsDay(sendRecord.getSendDate().intValue());
		if (fsdRecord == null) {
			fsdRecord = this.initFsdRecord(sendRecord.getSendDate().intValue());
		} else {
			//设置持久化状态为已存在
			fsdRecord.setPersistenceState((short)1);
		}
		//根据手机号码和日期查找对应手机吴是否已经被统计
		FullStatisticsMsisdn fullStatisticsMsisdn = null;
		
		if (!this.isExistFSMsisdn(sendRecord.getMsisdn().longValue(), sendRecord.getSendDate().intValue())) {
			//新建一条记录
			fullStatisticsMsisdn = new FullStatisticsMsisdn();
			fullStatisticsMsisdn.setMsisdn(sendRecord.getMsisdn().longValue());
			fullStatisticsMsisdn.setSumDate(sendRecord.getSendDate().intValue());
			
		}
		
		//根据日期和渠道id在分渠道统计表中查找对应记录
		ChannelStatisticsDay csdRecord = super.getChannelStatisticsDay(sendRecord.getSendDate(), sendRecord.getChannelId());
		if (csdRecord == null) {
			csdRecord = this.initCsdRecord(sendRecord.getSendDate(), sendRecord.getChannelId());
		} else {
			//设置持久化状态为已存在
			csdRecord.setPersistenceState((short)1);
		}
		//根据手机号码，日期，渠道id查找对应记录
		ChannelStatisticsMsisdn channelStatisticsMsisdn = null;
		
		if (!this.isExistCSMsisdn(sendRecord.getMsisdn().longValue(), sendRecord.getSendDate(), sendRecord.getChannelId())) {
			//新建一条记录
			channelStatisticsMsisdn = new ChannelStatisticsMsisdn();
			channelStatisticsMsisdn.setMsisdn(sendRecord.getMsisdn().longValue());
			channelStatisticsMsisdn.setSumDate(sendRecord.getSendDate());
			channelStatisticsMsisdn.setChannelId(sendRecord.getChannelId());
			
		}
		
		//提交更新
		try {
			//更新全量统计记录
			//如果号码统计记录不为空，则往数据库新增一条记录
			if (fullStatisticsMsisdn != null) {
				DaoManager.getInstance().getFullStatisticsDayDao().saveMsisdn(fullStatisticsMsisdn.getMsisdn(), fullStatisticsMsisdn.getSumDate());
			}
			if (fsdRecord.getPersistenceState() == 0) {
				DaoManager.getInstance().getFullStatisticsDayDao().saveFullStatisticsDay(fsdRecord);
			} else {
				//如果号码统计记录不为空，则表明该号码未统计过，则更新对应统计记录的号码数(+1)
				if (fullStatisticsMsisdn != null) {
					DaoManager.getInstance().getFullStatisticsDayDao().addMsgAndMsisdnNum(fsdRecord.getId(), 1, 1);
				} else {
					DaoManager.getInstance().getFullStatisticsDayDao().addMsgNum(fsdRecord.getId(), 1);
				}
			}
			//更新渠道统计记录
			if (channelStatisticsMsisdn != null) {
				DaoManager.getInstance().getChannelStatisticsDayDao().saveMsisdn(channelStatisticsMsisdn.getMsisdn(), channelStatisticsMsisdn.getSumDate(), channelStatisticsMsisdn.getChannelId());
			}
			if (csdRecord.getPersistenceState() == 0) {
				DaoManager.getInstance().getChannelStatisticsDayDao().saveChannelStatisticsDay(csdRecord);
			} else {
				if (channelStatisticsMsisdn != null) {
					DaoManager.getInstance().getChannelStatisticsDayDao().addMsgAndMsisdnNum(csdRecord.getId(), 1, 1);
				} else {
					DaoManager.getInstance().getChannelStatisticsDayDao().addMsgNum(csdRecord.getId(), 1);
				}
			}
			//更新发送状态为已处理
			DaoManager.getInstance().getSendRecordDao().updateStateProcessed(sendRecord.getId());
		} catch (DaoException e) {
			logger.error("exception when handle", e);
			throw new HandlerException(e.getMessage());
		}
		
	}
	
	
	
	private FullStatisticsDay initFsdRecord(int sumDate) {
		FullStatisticsDay fsdRecord = new FullStatisticsDay();
		fsdRecord.setId((new UUIDGenerator()).generate());
		fsdRecord.setMsgNum(new Integer(1));
		fsdRecord.setMsisdnNum(new Integer(1));
		fsdRecord.setSendSuccessNum(new Integer(0));
		fsdRecord.setSendFailNum(new Integer(0));
		fsdRecord.setBillSuccessNum(new Integer(0));
		fsdRecord.setBillFailNum(new Integer(0));
		fsdRecord.setSumAmount(new Double(0));
		fsdRecord.setSumDate(sumDate);
		//新增记录，设置持久化状态为新增
		fsdRecord.setPersistenceState((short)0);
		return fsdRecord;
	}
	
	private boolean isExistFSMsisdn(long msisdn, int sumDate) throws HandlerException {
		boolean exist = false;
		try {
			exist = DaoManager.getInstance().getFullStatisticsDayDao().isMsisdnExist(sumDate, msisdn);
		} catch (DaoException e) {
			logger.error("exception when isExistFSMsisdn", e);
			throw new HandlerException(e.getMessage());
		}
		return exist;
	}
	
	private ChannelStatisticsDay initCsdRecord(int sumDate, String channelId) {
		ChannelStatisticsDay csdRecord = new ChannelStatisticsDay();
		csdRecord.setId((new UUIDGenerator()).generate());
		csdRecord.setMsgNum(new Integer(1));
		csdRecord.setMsisdnNum(new Integer(1));
		csdRecord.setSendSuccessNum(new Integer(0));
		csdRecord.setSendFailNum(new Integer(0));
		csdRecord.setBillSuccessNum(new Integer(0));
		csdRecord.setBillFailNum(new Integer(0));
		csdRecord.setSumAmount(new Double(0));
		csdRecord.setSumDate(sumDate);
		csdRecord.setChannelId(channelId);
		//新增记录，设置持久化状态为新增
		csdRecord.setPersistenceState((short)0);
		return csdRecord;
	}
	
	private boolean isExistCSMsisdn(long msisdn, int sumDate, String channelId)  throws HandlerException {
		boolean exist = false;
		try {
			exist = DaoManager.getInstance().getChannelStatisticsDayDao().isMsisdnExist(sumDate, msisdn, channelId);
		} catch (DaoException e) {
			logger.error("exception when isExistCSMsisdn", e);
			throw new HandlerException(e.getMessage());
		}
		return exist;
	}
}
