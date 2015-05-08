/**
 * 
 */
package com.jason.ddoTimingTask.task.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.BillResultRecord;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.bean.SendResultRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.billResultRecord.AbstractBRRStatisHandler;
import com.jason.ddoTimingTask.task.handler.billResultRecord.BRRChannelStatisMonthHandler;
import com.jason.ddoTimingTask.task.handler.billResultRecord.BRRFullStatisMonthHandler;

/**
 * 计费结果统计处理链，主要是调用各个处理器进行处理
 * 
 * @author jasonzhang
 *
 */
public class BRRSHandlerChain {

	private static final Logger logger = Logger
			.getLogger(BRRSHandlerChain.class);

	private final static BRRSHandlerChain instance = new BRRSHandlerChain();

	public final static BRRSHandlerChain getInstance() {
		return instance;
	}

	private List<AbstractBRRStatisHandler> handlerList;

	private BRRSHandlerChain() {
		this.handlerList = new ArrayList<AbstractBRRStatisHandler>(2);
		this.handlerList.add(new BRRFullStatisMonthHandler());
		this.handlerList.add(new BRRChannelStatisMonthHandler());
	}
	
	public void doHandler(BillResultRecord resultRecord) throws HandlerException {
		SendRecord sendRecord = this.getSendRecord(resultRecord.getDdoMsgId());
		if (sendRecord != null && sendRecord.getState() == 1) {
			SendResultRecord sendResultRecord = this.getSendResultRecord(resultRecord.getDdoMsgId());
			if (sendResultRecord != null && sendResultRecord.getState() == 1) {
				boolean success = true;
				for (AbstractBRRStatisHandler handler : this.handlerList) {
					if (!handler.handle(sendRecord, resultRecord)) {
						success = false;
						break;
					}
				}
				if (success) {
					for (AbstractBRRStatisHandler handler : this.handlerList) {
						handler.commit();
						//更新记录状态为已处理
						try {
							DaoManager.getInstance().getBillResultRecordDao().updateStateProcessed(resultRecord.getId());
						} catch (DaoException e) {
							logger.error("exception when doHandler", e);
							throw new HandlerException(e.getMessage());
						}
					}
				}
			}
		}
	}
	
	private SendRecord getSendRecord(String ddoMsgId) throws HandlerException {
		SendRecord record = null;
		try {
			record = DaoManager.getInstance().getSendRecordDao().getSendRecord(ddoMsgId);
		} catch (DaoException e) {
			logger.error("exception when getSendRecord", e);
			throw new HandlerException(e.getMessage());
		}
		return record;
	}
	
	private SendResultRecord getSendResultRecord(String ddoMsgId) throws HandlerException {
		SendResultRecord record = null;
		try {
			record = DaoManager.getInstance().getSendResultRecordDao().getSendResultRecord(ddoMsgId);
		} catch (DaoException e) {
			logger.error("exception when getSendResultRecord", e);
			throw new HandlerException(e.getMessage());
		}
		return record;
	}
}
