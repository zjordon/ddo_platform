package com.jason.ddoWeb.entity.statistics;

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
 * @Description: 分渠道统计
 * @author zhangdaihao
 * @date 2015-04-23 15:12:11
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_channel_statistics_day", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
@ExcelTarget("channelStatisticsDayEntity")
public class ChannelStatisticsDayEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**统计日期*/
	@Excel(name="统计日期")
	private java.lang.Integer sumDate;
	/**渠道id*/
	@Excel(name="渠道")
	private java.lang.String channelId;
	/**用户数*/
	@Excel(name="用户数")
	private java.lang.Integer msisdnNum;
	/**计费金额*/
	@Excel(name="计费金额")
	private java.lang.Double sumAmount;
	/**短信条数*/
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
	 *@return: java.lang.Integer  统计日期
	 */
	@Column(name ="SUM_DATE",nullable=false,precision=10,scale=0)
	public java.lang.Integer getSumDate(){
		return this.sumDate;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  统计日期
	 */
	public void setSumDate(java.lang.Integer sumDate){
		this.sumDate = sumDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  渠道id
	 */
	@Column(name ="CHANNEL_ID",nullable=false,length=32)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  用户数
	 */
	@Column(name ="MSISDN_NUM",nullable=false,precision=10,scale=0)
	public java.lang.Integer getMsisdnNum(){
		return this.msisdnNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  用户数
	 */
	public void setMsisdnNum(java.lang.Integer msisdnNum){
		this.msisdnNum = msisdnNum;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  计费金额
	 */
	@Column(name ="SUM_AMOUNT",nullable=false,precision=18,scale=0)
	public java.lang.Double getSumAmount(){
		return this.sumAmount;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  计费金额
	 */
	public void setSumAmount(java.lang.Double sumAmount){
		this.sumAmount = sumAmount;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  短信条数
	 */
	@Column(name ="MSG_NUM",nullable=false,precision=10,scale=0)
	public java.lang.Integer getMsgNum(){
		return this.msgNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  短信条数
	 */
	public void setMsgNum(java.lang.Integer msgNum){
		this.msgNum = msgNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  发送成功数
	 */
	@Column(name ="SEND_SUCCESS_NUM",nullable=false,precision=10,scale=0)
	public java.lang.Integer getSendSuccessNum(){
		return this.sendSuccessNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  发送成功数
	 */
	public void setSendSuccessNum(java.lang.Integer sendSuccessNum){
		this.sendSuccessNum = sendSuccessNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  发送失败数
	 */
	@Column(name ="SEND_FAIL_NUM",nullable=false,precision=10,scale=0)
	public java.lang.Integer getSendFailNum(){
		return this.sendFailNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  发送失败数
	 */
	public void setSendFailNum(java.lang.Integer sendFailNum){
		this.sendFailNum = sendFailNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  计费成功数
	 */
	@Column(name ="BILL_SUCCESS_NUM",nullable=false,precision=10,scale=0)
	public java.lang.Integer getBillSuccessNum(){
		return this.billSuccessNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  计费成功数
	 */
	public void setBillSuccessNum(java.lang.Integer billSuccessNum){
		this.billSuccessNum = billSuccessNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  计费失败数
	 */
	@Column(name ="BILL_FAIL_NUM",nullable=false,precision=10,scale=0)
	public java.lang.Integer getBillFailNum(){
		return this.billFailNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  计费失败数
	 */
	public void setBillFailNum(java.lang.Integer billFailNum){
		this.billFailNum = billFailNum;
	}
}
