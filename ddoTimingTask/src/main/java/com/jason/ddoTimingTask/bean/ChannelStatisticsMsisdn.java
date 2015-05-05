/**
 * 
 */
package com.jason.ddoTimingTask.bean;

/**
 * 已统计渠道号码
 * @author jasonzhang
 *
 */
public class ChannelStatisticsMsisdn {

	private long msisdn;
	private int sumDate;
	private String channelId;
	public long getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(long msisdn) {
		this.msisdn = msisdn;
	}
	public int getSumDate() {
		return sumDate;
	}
	public void setSumDate(int sumDate) {
		this.sumDate = sumDate;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	
}
