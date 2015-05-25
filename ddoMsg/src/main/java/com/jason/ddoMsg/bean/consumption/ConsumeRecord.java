/**
 * 
 */
package com.jason.ddoMsg.bean.consumption;

/**
 * 手机消费记录
 * @author jasonzhang
 *
 */
public class ConsumeRecord {

	private int amount;
	private int num;
	private long msisdn;
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
	
	public void increaseAmount(int increaseAmount) {
		this.amount += increaseAmount;
		this.num++;
	}
}
