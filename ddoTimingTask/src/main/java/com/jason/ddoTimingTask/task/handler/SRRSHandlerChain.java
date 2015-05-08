/**
 * 
 */
package com.jason.ddoTimingTask.task.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.bean.SendResultRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.sendResultRecord.AbstractSRRStatisHandler;
import com.jason.ddoTimingTask.task.handler.sendResultRecord.SRRChannelStatisMonthHandler;
import com.jason.ddoTimingTask.task.handler.sendResultRecord.SRRFullStatisMonthHandler;

/**
 * 发送结果统计处理链，主要是调用各个处理器进行处理
 * @author jasonzhang
 *
 */
public class SRRSHandlerChain {

	private static final Logger logger = Logger
			.getLogger(SRRSHandlerChain.class);
	
	private final static SRRSHandlerChain instance = new SRRSHandlerChain();

	public final static SRRSHandlerChain getInstance() {
		return instance;
	}
	
	private List<AbstractSRRStatisHandler> handlerList;
	
	private SRRSHandlerChain() {
		this.handlerList = new ArrayList<AbstractSRRStatisHandler>(2);
		this.handlerList.add(new SRRFullStatisMonthHandler());
		this.handlerList.add(new SRRChannelStatisMonthHandler());
	}
	
	public void doHandler(SendResultRecord resultRecord) throws HandlerException {
		//对应的发送记录是否存在
		SendRecord sendRecord = this.getSendRecord(resultRecord.getDdoMsgId());
		if (sendRecord != null && sendRecord.getState() == 1) {
			boolean success = true;
			for (AbstractSRRStatisHandler handler : this.handlerList) {
				if (!handler.handle(sendRecord, resultRecord)) {
					success = false;
					break;
				}
			}
			if (success) {
				for (AbstractSRRStatisHandler handler : this.handlerList) {
					handler.commit();
				}
				try {
					DaoManager.getInstance().getSendResultRecordDao().updateStateProcessed(resultRecord.getId());
				} catch (DaoException e) {
					logger.error("exception when doHandler", e);
					throw new HandlerException(e.getMessage());
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
}
