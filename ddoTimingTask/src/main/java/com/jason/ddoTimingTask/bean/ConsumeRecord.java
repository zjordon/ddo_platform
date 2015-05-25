/**
 * 
 */
package com.jason.ddoTimingTask.bean;

/**
 * 手机消费记录
 * @author jasonzhang
 *
 */
public class ConsumeRecord {

	private int amount;
	private int num;
	private long msisdn;
	private int sumMonth;
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
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
}
