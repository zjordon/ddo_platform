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
 * @Description: 全量投诉按月
 * @author zhangdaihao
 * @date 2015-05-08 16:46:18
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_channel_complaint_month", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ChannelComplaintMonthEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**channelId*/
	private java.lang.String channelId;
	/**sumMonth*/
	private java.lang.Integer sumMonth;
	/**num*/
	private java.lang.Integer num;
	
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
	 *@return: java.lang.Integer  sumMonth
	 */
	@Column(name ="SUM_MONTH",nullable=true,precision=10,scale=0)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  num
	 */
	@Column(name ="NUM",nullable=true,precision=10,scale=0)
	public java.lang.Integer getNum(){
		return this.num;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  num
	 */
	public void setNum(java.lang.Integer num){
		this.num = num;
	}
}
