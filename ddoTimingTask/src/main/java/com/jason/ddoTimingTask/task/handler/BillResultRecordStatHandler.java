/**
 * 
 */
package com.jason.ddoTimingTask.task.handler;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.BillResultRecord;
import com.jason.ddoTimingTask.bean.ChannelStatisticsDay;
import com.jason.ddoTimingTask.bean.FullStatisticsDay;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.bean.SendResultRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;

/**
 * 计费结果统计处理器
 * @author jasonzhang
 *
 */
public class BillResultRecordStatHandler extends BaseStaticsHandler {

	private static final Logger logger = Logger
			.getLogger(BillResultRecordStatHandler.class);
	
	private final static BillResultRecordStatHandler instance = new BillResultRecordStatHandler();
	
	private BillResultRecordStatHandler(){}
	
	public final static BillResultRecordStatHandler getInstacne() {
		return instance;
	}
	
	public void handle(BillResultRecord record) throws HandlerException {
		//对应发送记录是否存在
		SendRecord sendRecord = super.getSendRecord(record.getDdoMsgId());
		if (sendRecord != null) {
			//对应发送记录状态是否为已处理
			if (sendRecord.getState() == 1) {
				//对应发送结果记录是否已经存在
				SendResultRecord sendResultRecord = this.getSendResultRecord(record.getDdoMsgId());
				if (sendResultRecord != null) {
					//对应发送结果状态是否已处理
					if (sendResultRecord.getState() == 1) {
						//对应全量统计记录是否存在
						FullStatisticsDay fsdRecord = super.getFullStatisticsDay(sendRecord.getSendDate().intValue());
						if (fsdRecord != null) {
							//对应分渠道统计记录是否存在
							ChannelStatisticsDay csdRecord = super.getChannelStatisticsDay(sendRecord.getSendDate(), sendRecord.getChannelId());
							if (csdRecord != null) {
								//提交更新
								try {
									if (record.getBillResult().intValue() == 1) {
										//根据计费业务id获取对应的计费价格
										double price = DaoManager.getInstance().getBillBusinessDao().getPrice(sendRecord.getBillingBusinessId());
										//计费成功则增加成功数目和计费价格
										DaoManager.getInstance().getFullStatisticsDayDao().addBillSuccessNum(fsdRecord.getId(), 1, price);
										DaoManager.getInstance().getChannelStatisticsDayDao().addBillSuccessNum(csdRecord.getId(), 1, price);
									} else {
										//计费成功则增加计费失败数目
										DaoManager.getInstance().getFullStatisticsDayDao().addBillFailNum(fsdRecord.getId(), 1);
										DaoManager.getInstance().getChannelStatisticsDayDao().addBillFailNum(csdRecord.getId(), 1);
									}
									//更新记录状态为已处理
									DaoManager.getInstance().getBillResultRecordDao().updateStateProcessed(record.getId());
								} catch (DaoException e) {
									logger.error("exception when handle", e);
									throw new HandlerException(e.getMessage());
								}
							}
						}
					}
				}
			}
		}
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
