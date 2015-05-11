/**
 * 
 */
package com.jason.ddoTimingTask.bean;

/**
 * 已统计省份号码
 * @author jasonzhang
 *
 */
public class PSMsisdnMonth {

	private long msisdn;
	private int sumMonth;
	private String provinceCode;
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
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
}
