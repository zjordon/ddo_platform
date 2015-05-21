package com.jason.ddoWeb.entity.msg;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: ddo消息
 * @author zhangdaihao
 * @date 2015-04-23 16:40:39
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_msg", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
@ExcelTarget("ddoMsgEntity")
public class DdoMsgEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**用户手机号码*/
	@Excel(name="用户手机号码")
	private java.lang.Long msisdn;
	/**计费业务ID*/
	@Excel(name="价格")
	private java.lang.String billingBusinessId;
	/**返回消息编码*/
	@Excel(name="发送结果",replace = {"成功_00000000","版本不匹配_00530001","未授权的接口调用_00530002","时间戳已经过期_00530004","非法的连接源IP地址_00530005","请求消息格式错误_00530012","系统内部错误_00539999"})
	private java.lang.String returnMsgCode;
	/**事务ID*/
	private java.lang.String transationId;
	/**发送时间*/
	private java.util.Date sendTime;
	/**计费状态编码*/
	@Excel(name="计费状态",replace = {"成功_00000000","版本不匹配_00530001","未授权的接口调用_00530002","时间戳已经过期_00530004","非法的连接源IP地址_00530005","请求消息格式错误_00530012","系统内部错误_00539999","未返_-1"})
	private java.lang.String billStateCode;
	/**计费状态返回时间*/
	private java.util.Date billStateTime;
	/**发送结果*/
	private java.lang.Integer sendResult;
	/**手机号码归属省份编码*/
	@Excel(name="号码归属省份")
	private java.lang.String msisdnProvinceCode;
	/**手机号码归属地市编码*/
	@Excel(name="号码归属地市")
	private java.lang.String msisdnCityCode;
	/**渠道ID*/
	@Excel(name="渠道")
	private java.lang.String channelId;
	/**请求id*/
	private java.lang.String requestId;
	/**重发标识*/
	private java.lang.Integer repeatFlag;
	/**创建时间*/
	@Excel(name="创建时间", exportFormat="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createDate;
	/**请求返回时间*/
	private java.util.Date responseTime;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  唯一标识
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  用户手机号码
	 */
	@Column(name ="MSISDN",nullable=false,precision=19,scale=0)
	public java.lang.Long getMsisdn(){
		return this.msisdn;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  用户手机号码
	 */
	public void setMsisdn(java.lang.Long msisdn){
		this.msisdn = msisdn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计费业务ID
	 */
	@Column(name ="BILLING_BUSINESS_ID",nullable=false,length=32)
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
	@Column(name ="RETURN_MSG_CODE",nullable=true,length=8)
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
	@Column(name ="TRANSATION_ID",nullable=true,length=24)
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
	@Column(name ="SEND_TIME",nullable=true)
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
	@Column(name ="BILL_STATE_CODE",nullable=true,length=8)
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
	@Column(name ="BILL_STATE_TIME",nullable=true)
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
	@Column(name ="SEND_RESULT",nullable=false,precision=3,scale=0)
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
	@Column(name ="MSISDN_PROVINCE_CODE",nullable=true,length=8)
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
	@Column(name ="MSISDN_CITY_CODE",nullable=true,length=8)
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
	@Column(name ="CHANNEL_ID",nullable=true,length=32)
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
	@Column(name ="REQUEST_ID",nullable=false,length=32)
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
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  重发标识
	 */
	@Column(name ="REPEAT_FLAG",nullable=true,precision=5,scale=0)
	public java.lang.Integer getRepeatFlag(){
		return this.repeatFlag;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  重发标识
	 */
	public void setRepeatFlag(java.lang.Integer repeatFlag){
		this.repeatFlag = repeatFlag;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  请求返回时间
	 */
	@Column(name ="RESPONSE_TIME",nullable=true)
	public java.util.Date getResponseTime(){
		return this.responseTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  请求返回时间
	 */
	public void setResponseTime(java.util.Date responseTime){
		this.responseTime = responseTime;
	}
}
