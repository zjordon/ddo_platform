/**
 * 
 */
package com.jason.ddoTimingTask.bean;

/**
 * 消费记录流水
 * @author jasonzhang
 *
 */
public class ConsumeTurnover {

	private String id;
	private long msisdn;
	private int amount;
	private int createDate;
	private int state;
	private int sumMonth;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(long msisdn) {
		this.msisdn = msisdn;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getCreateDate() {
		return createDate;
	}
	public void setCreateDate(int createDate) {
		this.createDate = createDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getSumMonth() {
		return sumMonth;
	}
	public void calcuateSendMonth() {
		this.sumMonth = this.createDate/100;
	}
}
