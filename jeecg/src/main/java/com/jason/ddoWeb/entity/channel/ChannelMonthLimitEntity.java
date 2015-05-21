package com.jason.ddoWeb.entity.channel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 渠道月使用限额
 * @author zhangdaihao
 * @date 2015-03-31 11:36:57
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_channel_month_limit", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ChannelMonthLimitEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**日期*/
	private java.lang.Integer month;
	/**限额*/
	private Long limitAmount;
	/**渠道id*/
	//private java.lang.String channelId;
	private ChannelEntity channel;
	
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
	 *@return: java.lang.Integer  月份
	 */
	@Column(name ="MONTH",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMonth(){
		return this.month;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  月份
	 */
	public void setMonth(java.lang.Integer month){
		this.month = month;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  限额
	 */
	@Column(name ="LIMIT_AMOUNT",nullable=true,precision=17,scale=0)
	public Long getLimitAmount(){
		return this.limitAmount;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  限额
	 */
	public void setLimitAmount(Long limitAmount){
		this.limitAmount = limitAmount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  渠道id
	 */
//	@Column(name ="CHANNEL_ID",nullable=false,length=32)
//	public java.lang.String getChannelId(){
//		return this.channelId;
//	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  渠道id
	 */
//	public void setChannelId(java.lang.String channelId){
//		this.channelId = channelId;
//	}
	
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "channel_id")
	public ChannelEntity getChannel() {
		return channel;
	}

	public void setChannel(ChannelEntity channel) {
		this.channel = channel;
	}
}
