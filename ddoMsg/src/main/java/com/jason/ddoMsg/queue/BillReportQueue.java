/**
 * 
 */
package com.jason.ddoMsg.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.jason.ddoMsg.bean.msg.BillReport;

/**
 * 状态报告请求队列
 * @author jasonzhang
 *
 */
public class BillReportQueue {
	
	private final static BillReportQueue instance = new BillReportQueue();
	
	private ConcurrentLinkedQueue<BillReport> queue;
	
	private BillReportQueue() {
		queue = new ConcurrentLinkedQueue<BillReport>();
	}
	
	public final static BillReportQueue getInstnace() {
		return instance;
	}

	/**
	 * 新增状态报告
	 * @param report 状态报告
	 */
	public void addBillReport(BillReport report) {
		this.queue.add(report);
	}
	/**
	 * 获取状态报告列表
	 * @param nums 获取数目
	 * @return
	 */
	public List<BillReport> getBillReports(int nums) {
		 List<BillReport> billReportList = new ArrayList<BillReport>(nums);
		 for (int i=0; i<nums; i++) {
			 BillReport billReport = this.queue.poll();
			 if (billReport != null) {
				 billReportList.add(billReport);
			 } else {
				 break;
			 }
		 }
		 return billReportList;
	}
}
