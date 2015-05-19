/**
 * 
 */
package com.jason.ddoTimingTask.task.handler.billResultRecord;

import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.bean.BillResultRecord;
import com.jason.ddoTimingTask.task.handler.HandlerException;

/**
 * 统计计费结果的抽象类，定义了统计的抽象流程
 * @author jasonzhang
 *
 */
public abstract class AbstractBRRStatisHandler {
	
	public boolean handle(SendRecord sendRecord, BillResultRecord resultRecord)  throws HandlerException {
		if (this.isExistStatisRecord(sendRecord)) {
			if (this.isSuccess(resultRecord)) {
				this.increaseSuccessNum();
			} else {
				this.increaseFailNum();
			}
			if (!this.isExistMsisdn(sendRecord)) {
				this.addMsisdnRecord(sendRecord);
				this.increaseMsisdnNum();
			}
		} else {
			return false;
		}
		
		return true;
	}
	
	public abstract void commit() throws HandlerException;

	/**
	 * 对应的统计是否存在
	 * @param sendRecord
	 * @return
	 */
	protected abstract boolean isExistStatisRecord(SendRecord sendRecord) throws HandlerException;
	
	/**
	 * 是否成功
	 * @param record
	 * @return
	 * @throws HandlerException
	 */
	protected abstract boolean isSuccess(BillResultRecord record) throws HandlerException;
	
	protected abstract void increaseFailNum();
	
	protected abstract void increaseSuccessNum();
	
	/**
	 * 号码是否被统计过
	 * @param sendRecord
	 * @return
	 */
	protected abstract boolean isExistMsisdn(SendRecord sendRecord) throws HandlerException;
	/**
	 * 新增号码记录
	 * @param sendRecord
	 * @return
	 */
	protected abstract void addMsisdnRecord(SendRecord sendRecord) throws HandlerException;
	/**
	 * 增加统计记录的用户数
	 * @param num
	 */
	protected abstract void increaseMsisdnNum() throws HandlerException;
}
