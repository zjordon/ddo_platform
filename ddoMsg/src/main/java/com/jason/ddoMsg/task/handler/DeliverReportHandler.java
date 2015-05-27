/**
 * 
 */
package com.jason.ddoMsg.task.handler;

import java.util.Date;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.DeliverResponse;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.externalInterface.ChannelBillRepInterface;

/**
 * 下发状态报告到渠道的处理
 * 
 * @author jasonzhang
 *
 */
public class DeliverReportHandler {
	private static final Logger logger = Logger
			.getLogger(DeliverReportHandler.class);

	private final static DeliverReportHandler instance = new DeliverReportHandler();

	private DeliverReportHandler() {
	}

	public final static DeliverReportHandler getInstance() {
		return instance;
	}

	/**
	 * 处理下发状态报告到渠道
	 * 
	 * @param billReportId
	 *            状态报告id
	 * @param msgId
	 *            消息id
	 * @param msisdn
	 *            手机号码
	 * @param statusCode
	 *            计费状态
	 * @param reportUrl
	 *            渠道接收状态报告的url
	 */
	public void handle(String recordId, String msgId, long msisdn,
			String statusCode, String reportUrl) {
		Date sendDate = new Date();
		DeliverResponse resp = ChannelBillRepInterface.getInstnace()
				.deliverReport(msgId, msisdn, statusCode, reportUrl);
		boolean needRepeat = false;
		// 判断是否下发成功
		if (resp.getStatusCode() == 200) {
			// 下发成功
			// 判断是否要重新下发
			if ("ok".equalsIgnoreCase(resp.getMsg())) {
				// 不需要重新下发
				// 标记为下发成功
			} else {
				// 需要重新下发
				// 标记为需要重新下发
				needRepeat = true;
			}
		} else {
			// 下发失败
			// 标记为需要重新下发
			needRepeat = true;
		}
		Date responseDate = new Date();
		try {
			CacheManager
					.getInstance()
					.getBillReportCache()
					.updateResultCode(recordId, 2, 1, resp.getMsg(), needRepeat, sendDate, responseDate);
		} catch (CacheException e) {
			logger.error("excpetion when handle deliver report", e);
		}

	}
}
