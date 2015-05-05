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
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 发送记录
 * @author zhangdaihao
 * @date 2015-04-23 15:09:38
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_send_record", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SendRecordEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**ddo消息id*/
	private java.lang.String ddoMsgId;
	/**手机号码*/
	private java.lang.Integer msisdn;
	/**渠道id*/
	private java.lang.String channelId;
	/**计费业务id*/
	private java.lang.String billingBusinessId;
	/**发送日期*/
	private java.lang.Integer sendDate;
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ddo消息id
	 */
	@Column(name ="DDO_MSG_ID",nullable=false,length=32)
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
	@Column(name ="MSISDN",nullable=false,precision=19,scale=0)
	public java.lang.Integer getMsisdn(){
		return this.msisdn;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  手机号码
	 */
	public void setMsisdn(java.lang.Integer msisdn){
		this.msisdn = msisdn;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计费业务id
	 */
	@Column(name ="BILLING_BUSINESS_ID",nullable=false,length=32)
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
	@Column(name ="SEND_DATE",nullable=false,precision=10,scale=0)
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
}
