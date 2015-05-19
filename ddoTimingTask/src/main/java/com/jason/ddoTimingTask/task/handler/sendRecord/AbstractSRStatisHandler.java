/**
 * 
 */
package com.jason.ddoTimingTask.task.handler.sendRecord;

import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.task.handler.HandlerException;

/**
 * 统计发送量的抽象类，定义了统计的抽象流程
 * @author jasonzhang
 *
 */
public abstract class AbstractSRStatisHandler {
	
	public void handler(SendRecord sendRecord) throws HandlerException {
		if (!this.isExistStatisRecord(sendRecord)) {
			this.addStatisRecord(sendRecord);
		}
//		if (!this.isExistMsisdn(sendRecord)) {
//			this.addMsisdnRecord(sendRecord);
//			this.increaseMsisdnNum();
//		}
		this.increaseMsgNum();
	}
	/**
	 * 提交更新
	 * @throws HandlerException
	 */
	public abstract void commit() throws HandlerException;

	/**
	 * 对应的统计是否存在
	 * @param sendRecord
	 * @return
	 */
	protected abstract boolean isExistStatisRecord(SendRecord sendRecord) throws HandlerException;
	/**
	 * 新增一条统计记录
	 * @param sendRecord
	 */
	protected abstract void addStatisRecord(SendRecord sendRecord) throws HandlerException;
	/**
	 * 号码是否被统计过
	 * @param sendRecord
	 * @return
	 */
//	protected abstract boolean isExistMsisdn(SendRecord sendRecord) throws HandlerException;
	/**
	 * 新增号码记录
	 * @param sendRecord
	 * @return
	 */
//	protected abstract void addMsisdnRecord(SendRecord sendRecord) throws HandlerException;
	/**
	 * 增加统计记录的用户数
	 * @param num
	 */
//	protected abstract void increaseMsisdnNum() throws HandlerException;
	/**
	 * 增加统计记录的消息数
	 * @param num
	 */
	protected abstract void increaseMsgNum() throws HandlerException;
}
