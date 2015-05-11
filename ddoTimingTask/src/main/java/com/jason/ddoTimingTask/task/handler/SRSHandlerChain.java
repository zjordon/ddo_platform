/**
 * 
 */
package com.jason.ddoTimingTask.task.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.sendRecord.AbstractSRStatisHandler;
import com.jason.ddoTimingTask.task.handler.sendRecord.SRChannelStatisMonthHandler;
import com.jason.ddoTimingTask.task.handler.sendRecord.SRFullStatisMonthHandler;
import com.jason.ddoTimingTask.task.handler.sendRecord.SRProvinceStatisMonthHandler;

/**
 * 发送量统计处理链，主要是调用各个处理器进行处理
 * @author jasonzhang
 *
 */
public class SRSHandlerChain {
	
	private static final Logger logger = Logger
			.getLogger(SRSHandlerChain.class);
	
	private final static SRSHandlerChain instance = new SRSHandlerChain();

	public final static SRSHandlerChain getInstance() {
		return instance;
	}
	
	private List<AbstractSRStatisHandler> handlerList;
	
	private SRSHandlerChain() {
		this.handlerList = new ArrayList<AbstractSRStatisHandler>(4);
		this.handlerList.add(new SRFullStatisMonthHandler());
		this.handlerList.add(new SRChannelStatisMonthHandler());
		this.handlerList.add(new SRProvinceStatisMonthHandler());
	}
	
	public void doHandler(SendRecord sendRecord) throws HandlerException {
		for (AbstractSRStatisHandler handler : this.handlerList) {
			handler.handler(sendRecord);
		}
		for (AbstractSRStatisHandler handler : this.handlerList) {
			handler.commit();
		}
		//更新发送状态为已处理
		try {
			DaoManager.getInstance().getSendRecordDao().updateStateProcessed(sendRecord.getId());
		} catch (DaoException e) {
			logger.error("exception when doHandler", e);
			throw new HandlerException(e.getMessage());
		}
	}
}
