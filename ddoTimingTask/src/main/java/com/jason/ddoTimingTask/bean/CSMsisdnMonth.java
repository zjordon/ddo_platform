/**
 * 
 */
package com.jason.ddoTimingTask.bean;

/**
 * 已统计渠道号码
 * @author jasonzhang
 *
 */
public class CSMsisdnMonth {

	private long msisdn;
	private int sumMonth;
	private String channelId;
	public long getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(long msisdn) {
		this.msisdn = msisdn;
	}
	
	public int getSumMonth() {
		return sumMonth;
	}
	public void setSumMonth(int sumMonth) {
		this.sumMonth = sumMonth;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	
}
