package com.jason.ddoWeb.entity.statistics;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 分渠道统计
 * @author zhangdaihao
 * @date 2015-05-07 15:25:39
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_channel_statistics_month", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ChannelStatisticsMonthEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**sumMonth*/
	@Excel(name="统计月份")
	private java.lang.Integer sumMonth;
	/**channelId*/
	@Excel(name="渠道")
	private java.lang.String channelId;
	/**msisdnNum*/
	@Excel(name="用户数")
	private java.lang.Integer msisdnNum;
	/**sumAmount*/
	@Excel(name="计费金额")
	private java.lang.Double sumAmount;
	/**msgNum*/
	@Excel(name="短信条数")
	private java.lang.Integer msgNum;
	/**发送成功数*/
	@Excel(name="发送成功数")
	private java.lang.Integer sendSuccessNum;
	/**发送失败数*/
	@Excel(name="发送失败数")
	private java.lang.Integer sendFailNum;
	/**计费成功数*/
	@Excel(name="计费成功数")
	private java.lang.Integer billSuccessNum;
	/**计费失败数*/
	@Excel(name="计费失败数")
	private java.lang.Integer billFailNum;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
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
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  sumMonth
	 */
	@Column(name ="SUM_MONTH",nullable=false,precision=10,scale=0)
	public java.lang.Integer getSumMonth(){
		return this.sumMonth;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  sumMonth
	 */
	public void setSumMonth(java.lang.Integer sumMonth){
		this.sumMonth = sumMonth;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  channelId
	 */
	@Column(name ="CHANNEL_ID",nullable=false,length=32)
	public java.lang.String getChannelId(){
		return this.channelId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  channelId
	 */
	public void setChannelId(java.lang.String channelId){
		this.channelId = channelId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  msisdnNum
	 */
	@Column(name ="MSISDN_NUM",nullable=false,precision=10,scale=0)
	public java.lang.Integer getMsisdnNum(){
		return this.msisdnNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  msisdnNum
	 */
	public void setMsisdnNum(java.lang.Integer msisdnNum){
		this.msisdnNum = msisdnNum;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  sumAmount
	 */
	@Column(name ="SUM_AMOUNT",nullable=false,precision=18,scale=0)
	public java.lang.Double getSumAmount(){
		return this.sumAmount;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  sumAmount
	 */
	public void setSumAmount(java.lang.Double sumAmount){
		this.sumAmount = sumAmount;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  msgNum
	 */
	@Column(name ="MSG_NUM",nullable=false,precision=10,scale=0)
	public java.lang.Integer getMsgNum(){
		return this.msgNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  msgNum
	 */
	public void setMsgNum(java.lang.Integer msgNum){
		this.msgNum = msgNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  sendSuccessNum
	 */
	@Column(name ="SEND_SUCCESS_NUM",nullable=false,precision=10,scale=0)
	public java.lang.Integer getSendSuccessNum(){
		return this.sendSuccessNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  sendSuccessNum
	 */
	public void setSendSuccessNum(java.lang.Integer sendSuccessNum){
		this.sendSuccessNum = sendSuccessNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  sendFailNum
	 */
	@Column(name ="SEND_FAIL_NUM",nullable=false,precision=10,scale=0)
	public java.lang.Integer getSendFailNum(){
		return this.sendFailNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  sendFailNum
	 */
	public void setSendFailNum(java.lang.Integer sendFailNum){
		this.sendFailNum = sendFailNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  billSuccessNum
	 */
	@Column(name ="BILL_SUCCESS_NUM",nullable=false,precision=10,scale=0)
	public java.lang.Integer getBillSuccessNum(){
		return this.billSuccessNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  billSuccessNum
	 */
	public void setBillSuccessNum(java.lang.Integer billSuccessNum){
		this.billSuccessNum = billSuccessNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  billFailNum
	 */
	@Column(name ="BILL_FAIL_NUM",nullable=true,precision=10,scale=0)
	public java.lang.Integer getBillFailNum(){
		return this.billFailNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  billFailNum
	 */
	public void setBillFailNum(java.lang.Integer billFailNum){
		this.billFailNum = billFailNum;
	}
}
