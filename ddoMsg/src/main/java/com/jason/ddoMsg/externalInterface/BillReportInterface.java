/**
 * 
 */
package com.jason.ddoMsg.externalInterface;

import java.util.Date;

import com.jason.ddoMsg.bean.msg.BillReport;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.queue.BillReportQueue;
import com.jason.ddoMsg.util.UUIDGenerator;

/**
 * 接收计费状态报告接口
 * @author jasonzhang
 *
 */
public class BillReportInterface {
	private final static BillReportInterface instance = new BillReportInterface();
	
	private BillReportInterface(){}
	
	public final static BillReportInterface getInstance() {
		return instance;
	}

	/**
	 * 接收计费状态报告
	 * @param transationId 事务id
	 * @param chargeResultCode 计费状态编码
	 * @return
	 */
	public String receiveBillReport(String transationId, String chargeResultCode) {
		String ret = "";
		if (this.isStopReceiveRequest()) {
			ret = "00539999";
		} else {
			BillReport report = new BillReport();
			report.setId((new UUIDGenerator()).generate());
			report.setTransationId(transationId);
			report.setBillStateCode(chargeResultCode);
			report.setState(new Integer(1));
			report.setCreateDate(new Date());
			BillReportQueue.getInstnace().addBillReport(report);
			ret = "00000000";
		}
		return ret;
	}
	
	private boolean isStopReceiveRequest() {
		return CacheManager.getInstance().getConfigCache().isStopAll();
	}
}
