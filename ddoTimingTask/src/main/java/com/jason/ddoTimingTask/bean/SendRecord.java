package com.jason.ddoTimingTask.bean;

/**   
 * @Title: Entity
 * @Description: 发送记录
 * @author zhangdaihao
 * @date 2015-04-23 15:09:38
 * @version V1.0   
 *
 */
public class SendRecord implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**ddo消息id*/
	private java.lang.String ddoMsgId;
	/**手机号码*/
	private java.lang.Long msisdn;
	/**渠道id*/
	private java.lang.String channelId;
	/**计费业务id*/
	private java.lang.String billingBusinessId;
	/**发送日期*/
	private java.lang.Integer sendDate;
	
	private int state;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  唯一标识
	 */
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  唯一标识
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ddo消息id
	 */
	public java.lang.String getDdoMsgId(){
		return this.ddoMsgId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ddo消息id
	 */
	public void setDdoMsgId(java.lang.String ddoMsgId){
		this.ddoMsgId = ddoMsgId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  手机号码
	 */
	public java.lang.Long getMsisdn(){
		return this.msisdn;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  手机号码
	 */
	public void setMsisdn(java.lang.Long msisdn){
		this.msisdn = msisdn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  渠道id
	 */
	public java.lang.String getChannelId(){
		return this.channelId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  渠道id
	 */
	public void setChannelId(java.lang.String channelId){
		this.channelId = channelId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计费业务id
	 */
	public java.lang.String getBillingBusinessId(){
		return this.billingBusinessId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计费业务id
	 */
	public void setBillingBusinessId(java.lang.String billingBusinessId){
		this.billingBusinessId = billingBusinessId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  发送日期
	 */
	public java.lang.Integer getSendDate(){
		return this.sendDate;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  发送日期
	 */
	public void setSendDate(java.lang.Integer sendDate){
		this.sendDate = sendDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
