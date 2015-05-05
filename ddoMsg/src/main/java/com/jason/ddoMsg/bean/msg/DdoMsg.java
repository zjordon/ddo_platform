package com.jason.ddoMsg.bean.msg;

import java.util.Date;

/**   
 * @Title: Entity
 * @Description: ddo消息
 * @author zhangdaihao
 * @date 2015-03-30 09:02:23
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class DdoMsg implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**用户手机号码*/
	private Long msisdn;
	/**计费业务ID*/
	private java.lang.String billingBusinessId;
	/**返回消息编码*/
	private java.lang.String returnMsgCode;
	/**事务ID*/
	private java.lang.String transationId;
	/**发送时间*/
	private java.util.Date sendTime;
	/**计费状态编码*/
	private java.lang.String billStateCode;
	/**计费状态返回时间*/
	private java.util.Date billStateTime;
	/**发送结果*/
	private java.lang.Integer sendResult;
	/**手机号码归属省份编码*/
	private java.lang.String msisdnProvinceCode;
	/**手机号码归属地市编码*/
	private java.lang.String msisdnCityCode;
	/**渠道ID*/
	private java.lang.String channelId;
	/**请求id*/
	private java.lang.String requestId;
	private Date createDate;
	private Date responseTime;
	
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
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  用户手机号码
	 */
	public Long getMsisdn(){
		return this.msisdn;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  用户手机号码
	 */
	public void setMsisdn(Long msisdn){
		this.msisdn = msisdn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计费业务ID
	 */
	public java.lang.String getBillingBusinessId(){
		return this.billingBusinessId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计费业务ID
	 */
	public void setBillingBusinessId(java.lang.String billingBusinessId){
		this.billingBusinessId = billingBusinessId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  返回消息编码
	 */
	public java.lang.String getReturnMsgCode(){
		return this.returnMsgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  返回消息编码
	 */
	public void setReturnMsgCode(java.lang.String returnMsgCode){
		this.returnMsgCode = returnMsgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  事务ID
	 */
	public java.lang.String getTransationId(){
		return this.transationId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  事务ID
	 */
	public void setTransationId(java.lang.String transationId){
		this.transationId = transationId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  发送时间
	 */
	public java.util.Date getSendTime(){
		return this.sendTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  发送时间
	 */
	public void setSendTime(java.util.Date sendTime){
		this.sendTime = sendTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计费状态编码
	 */
	public java.lang.String getBillStateCode(){
		return this.billStateCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计费状态编码
	 */
	public void setBillStateCode(java.lang.String billStateCode){
		this.billStateCode = billStateCode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  计费状态返回时间
	 */
	public java.util.Date getBillStateTime(){
		return this.billStateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  计费状态返回时间
	 */
	public void setBillStateTime(java.util.Date billStateTime){
		this.billStateTime = billStateTime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  发送结果
	 */
	public java.lang.Integer getSendResult(){
		return this.sendResult;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  发送结果
	 */
	public void setSendResult(java.lang.Integer sendResult){
		this.sendResult = sendResult;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号码归属省份编码
	 */
	public java.lang.String getMsisdnProvinceCode(){
		return this.msisdnProvinceCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号码归属省份编码
	 */
	public void setMsisdnProvinceCode(java.lang.String msisdnProvinceCode){
		this.msisdnProvinceCode = msisdnProvinceCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号码归属地市编码
	 */
	public java.lang.String getMsisdnCityCode(){
		return this.msisdnCityCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号码归属地市编码
	 */
	public void setMsisdnCityCode(java.lang.String msisdnCityCode){
		this.msisdnCityCode = msisdnCityCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  渠道ID
	 */
	public java.lang.String getChannelId(){
		return this.channelId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  渠道ID
	 */
	public void setChannelId(java.lang.String channelId){
		this.channelId = channelId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请求id
	 */
	public java.lang.String getRequestId(){
		return this.requestId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请求id
	 */
	public void setRequestId(java.lang.String requestId){
		this.requestId = requestId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
}
