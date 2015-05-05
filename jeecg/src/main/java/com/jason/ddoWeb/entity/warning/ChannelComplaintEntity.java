package com.jason.ddoWeb.entity.warning;

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
 * @Description: 渠道投诉
 * @author zhangdaihao
 * @date 2015-05-04 09:29:39
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_channel_complaint_view", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ChannelComplaintEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**渠道id*/
	private java.lang.String channelId;
	/**日期*/
	private Integer sumDate;
	/**计费金额*/
	private java.lang.Double sumAmount;
	/**用户数*/
	private java.lang.Integer msisdnNum;
	/**投诉数*/
	private java.lang.Integer num;
	/**唯一标识*/
	private java.lang.String channelComplaintId;
	/**万投比*/
	private Double scale;
	
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  计费金额
	 */
	@Column(name ="SUM_AMOUNT",nullable=false,precision=18,scale=2)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  投诉数
	 */
	@Column(name ="NUM",nullable=true,precision=10,scale=0)
	public java.lang.Integer getNum(){
		return this.num;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  投诉数
	 */
	public void setNum(java.lang.Integer num){
		this.num = num;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  唯一标识
	 */
	@Column(name ="CHANNEL_COMPLAINT_ID",nullable=true,length=32)
	public java.lang.String getChannelComplaintId(){
		return this.channelComplaintId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  唯一标识
	 */
	public void setChannelComplaintId(java.lang.String channelComplaintId){
		this.channelComplaintId = channelComplaintId;
	}

	@Column(name ="SUM_DATE",nullable=true,precision=10,scale=0)
	public Integer getSumDate() {
		return sumDate;
	}

	public void setSumDate(Integer sumDate) {
		this.sumDate = sumDate;
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}
}
