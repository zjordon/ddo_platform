/**
 * 
 */
package com.jason.ddoTimingTask.task.handler;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.ChannelStatisticsDay;
import com.jason.ddoTimingTask.bean.FullStatisticsDay;
import com.jason.ddoTimingTask.bean.SendRecord;
import com.jason.ddoTimingTask.bean.SendResultRecord;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;

/**
 * 发送结果统计处理器
 * @author jasonzhang
 *
 */
public class SendResultRecordStatHandler extends BaseStaticsHandler {

	private static final Logger logger = Logger
			.getLogger(SendResultRecordStatHandler.class);
	
	private final static SendResultRecordStatHandler instance = new SendResultRecordStatHandler();
	
	private SendResultRecordStatHandler(){}
	
	public final static SendResultRecordStatHandler getInstacne() {
		return instance;
	}
	
	public void handle(SendResultRecord record) throws HandlerException {
		//对应的发送记录是否存在
		SendRecord sendRecord = super.getSendRecord(record.getDdoMsgId());
		if (sendRecord != null) {
			//对应发送记录是的状态是否为已处理
			if (sendRecord.getState() == 1) {
				//对应的全量统计记录是否存在
				FullStatisticsDay fsdRecord = super.getFullStatisticsDay(sendRecord.getSendDate().intValue());
				if (fsdRecord != null) {
					//对应的分渠道统计记录是否存在
					//根据日期和渠道id在分渠道统计表中查找对应记录
					ChannelStatisticsDay csdRecord = super.getChannelStatisticsDay(sendRecord.getSendDate(), sendRecord.getChannelId());
					if (csdRecord != null) {
						//提交更新
						try {
							if (record.getSendResult().intValue() == 1) {
								//发送结果为成功则更新发送消息的成功数
								DaoManager.getInstance().getFullStatisticsDayDao().addSendSuccessNum(fsdRecord.getId(), 1);
								DaoManager.getInstance().getChannelStatisticsDayDao().addSendSuccessNum(csdRecord.getId(), 1);
							} else {
								//发送结果为失败则更新发送消息的失败数
								DaoManager.getInstance().getFullStatisticsDayDao().addSendFailNum(fsdRecord.getId(), 1);
								DaoManager.getInstance().getChannelStatisticsDayDao().addSendFailNum(csdRecord.getId(), 1);
							}
							//更新发送结果记录状态为已处理
							DaoManager.getInstance().getSendResultRecordDao().updateStateProcessed(record.getId());
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
