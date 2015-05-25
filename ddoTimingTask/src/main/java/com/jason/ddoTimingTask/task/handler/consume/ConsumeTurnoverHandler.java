/**
 * 
 */
package com.jason.ddoTimingTask.task.handler.consume;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.ConsumeRecord;
import com.jason.ddoTimingTask.bean.ConsumeTurnover;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.task.handler.HandlerException;

/**
 * 消费流水记录处理器
 * @author jasonzhang
 *
 */
public class ConsumeTurnoverHandler {

	private static final Logger logger = Logger
			.getLogger(ConsumeTurnoverHandler.class);
	
private final static ConsumeTurnoverHandler instance = new ConsumeTurnoverHandler();
	
	private ConsumeTurnoverHandler(){}
	
	public final static ConsumeTurnoverHandler getInstacne() {
		return instance;
	}
	
	public void handle(ConsumeTurnover record) throws HandlerException {
		//消费记录是否存在
		ConsumeRecord consumeRecord = null;
		try {
			consumeRecord = DaoManager.getInstance().getConsumeDao().getConsumeRecord(record.getMsisdn(), record.getSumMonth());
		} catch (DaoException e) {
			logger.error("exception when handle", e);
			throw new HandlerException(e.getMessage());
		}
		if (consumeRecord == null) {
			//新增消费记录
			consumeRecord = new ConsumeRecord();
			consumeRecord.setAmount(record.getAmount());
			consumeRecord.setMsisdn(record.getMsisdn());
			consumeRecord.setNum(1);
			consumeRecord.setSumMonth(record.getSumMonth());
			try {
				DaoManager.getInstance().getConsumeDao().saveConsumeRecord(consumeRecord);
			} catch (DaoException e) {
				logger.error("exception when handle", e);
				throw new HandlerException(e.getMessage());
			}
		} else {
			//增加消费记录的消费金额和次数
			try {
				DaoManager.getInstance().getConsumeDao().updateConsumeRecord(consumeRecord.getMsisdn(), consumeRecord.getSumMonth(), record.getAmount(), 1);
			} catch (DaoException e) {
				logger.error("exception when handle", e);
				throw new HandlerException(e.getMessage());
			}
		}
		//标记记录状态为已处理
		try {
			DaoManager.getInstance().getConsumeDao().updateStateProcessed(record.getId());
		} catch (DaoException e) {
			logger.error("exception when handle", e);
			throw new HandlerException(e.getMessage());
		}
	}
}
