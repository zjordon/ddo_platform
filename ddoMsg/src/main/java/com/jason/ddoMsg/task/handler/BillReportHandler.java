/**
 * 
 */
package com.jason.ddoMsg.task.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.channel.Channel;
import com.jason.ddoMsg.bean.msg.BillReport;
import com.jason.ddoMsg.bean.msg.DdoMsg;
import com.jason.ddoMsg.bean.statistics.BillResultRecord;
import com.jason.ddoMsg.cache.BillReportCache;
import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.cache.DdoMsgCache;
import com.jason.ddoMsg.cache.ElementNotFoundException;
import com.jason.ddoMsg.queue.StatisticsQueue;
import com.jason.ddoMsg.util.UUIDGenerator;

/**
 * 计费状态报告处理
 * @author jasonzhang
 *
 */
public class BillReportHandler {
	private static final Logger logger = Logger
			.getLogger(BillReportHandler.class);

	private final static BillReportHandler instance = new BillReportHandler();

	private BillReportHandler() {
	}

	public final static BillReportHandler getInstance() {
		return instance;
	}
	
	
	public void handle(List<BillReport> billReportList, boolean needStatistics) {
		//从数据库中获取对应的ddo消息
		Map<String, DdoMsg> ddoMsgMap = this.getDdoMsgMap(billReportList);
		DdoMsgCache ddoMsgCache = CacheManager.getInstance().getDdoMsgCache();
		DeliverReportHandler deliverReportHandler = DeliverReportHandler.getInstance();
		BillReportCache billReportCache = CacheManager.getInstance().getBillReportCache();
		Date current = new Date();
		for (BillReport billReport : billReportList) {
			//判断对应的消息是否存在
			DdoMsg ddoMsg = ddoMsgMap.get(billReport.getTransationId());
			if (ddoMsg != null) {
				//对应的ddo消息存在
				//更新消息的计费编码
				try {
					ddoMsgCache.updateMsgBillingCode(ddoMsg.getId(), billReport.getBillStateCode(), current);
				} catch (CacheException e) {
					logger.error("excpetion when handle bill report", e);
				}
				if (needStatistics) {
					//加入统计队列 20150423
					BillResultRecord billResultRecord = new BillResultRecord();
					billResultRecord.setId((new UUIDGenerator()).generate());
					billResultRecord.setDdoMsgId(ddoMsg.getId());
					if ("00000000".equals(billReport.getBillStateCode())) {
						billResultRecord.setBillResult(new Integer(1));
					} else {
						billResultRecord.setBillResult(new Integer(0));
					}
					StatisticsQueue.getInstance().addBillResultRecord(billResultRecord);
				}
				
				//判断是否需要下发状态报告
				Channel channel = null;
				try {
					channel = CacheManager.getInstance().getChannelCache().getChannel(ddoMsg.getChannelId());
				} catch (ElementNotFoundException e) {
					logger.error("excpetion when handle bill report", e);
				}
				if (channel != null) {
					if (StringUtils.isNotBlank(channel.getDownUrl())) {
						//需要下发状态报告
						String statusCode = billReport.getBillStateCode();
						if ("00000000".equals(statusCode)) {
							statusCode = "0";
						}
						deliverReportHandler.handle(billReport.getId(), ddoMsg.getRequestId(), ddoMsg.getMsisdn().longValue(), statusCode, channel.getDownUrl());
					} else {
						//不需要下发状态报告
						try {
							billReportCache.updateState(billReport.getId(), 2, 1, false);
						} catch (CacheException e) {
							logger.error("excpetion when handle bill report", e);
						}
					}
				}
			} else {
				//对应的ddo消息不存在
				//标记信息为未找到对应的消息id
				try {
					billReportCache.updateState(billReport.getId(), 2, 2, false);
				} catch (CacheException e) {
					logger.error("excpetion when handle bill report", e);
				}
			}
		}
		ddoMsgMap.clear();
	}
	
	private Map<String, DdoMsg> getDdoMsgMap(List<BillReport> billReportList) {
		Map<String, DdoMsg> ddoMsgMap = new HashMap<String, DdoMsg>(billReportList.size());
		List<String> transationIds = new ArrayList<String>(billReportList.size());
		for (BillReport billReport : billReportList) {
			transationIds.add(billReport.getTransationId());
		}
		List<DdoMsg> ddoMsgList = null;
		try {
			ddoMsgList = CacheManager.getInstance().getDdoMsgCache().getDdoMsgs(transationIds);
		} catch (CacheException e) {
			logger.error("excpetion when getDdoMsgMap", e);
		}
		if (ddoMsgList != null) {
			for (DdoMsg ddoMsg : ddoMsgList) {
				ddoMsgMap.put(ddoMsg.getTransationId(), ddoMsg);
			}
		}
		return ddoMsgMap;
	}
}
