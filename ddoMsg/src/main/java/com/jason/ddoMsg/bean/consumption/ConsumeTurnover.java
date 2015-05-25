/**
 * 
 */
package com.jason.ddoMsg.bean.consumption;

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
	
	
}
