package com.jason.ddoWeb.entity.channel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @Description: 渠道计费点
 * @author zhangdaihao
 * @date 2015-04-17 10:36:42
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_channel_business", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ChannelBusinessEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**产品指令*/
	private java.lang.String instruct;
	/**状态*/
	private java.lang.Integer state;
	/**关停状态*/
	private java.lang.Integer closeState;
	/**计费业务id*/
//	private java.lang.String billBusinessId;
	private BillBusinessEntity billBusiness;
	/**渠道id*/
	private java.lang.String channelId;
	
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
	 *@return: java.lang.String  产品指令
	 */
	@Column(name ="INSTRUCT",nullable=false,length=24)
	public java.lang.String getInstruct(){
		return this.instruct;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品指令
	 */
	public void setInstruct(java.lang.String instruct){
		this.instruct = instruct;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  状态
	 */
	@Column(name ="STATE",nullable=true,precision=5,scale=0)
	public java.lang.Integer getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  状态
	 */
	public void setState(java.lang.Integer state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  关停状态
	 */
	@Column(name ="CLOSE_STATE",nullable=true,precision=5,scale=0)
	public java.lang.Integer getCloseState(){
		return this.closeState;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  关停状态
	 */
	public void setCloseState(java.lang.Integer closeState){
		this.closeState = closeState;
	}
//	/**
//	 *方法: 取得java.lang.String
//	 *@return: java.lang.String  计费业务id
//	 */
//	@Column(name ="BILL_BUSINESS_ID",nullable=false,length=32)
//	public java.lang.String getBillBusinessId(){
//		return this.billBusinessId;
//	}
//
//	/**
//	 *方法: 设置java.lang.String
//	 *@param: java.lang.String  计费业务id
//	 */
//	public void setBillBusinessId(java.lang.String billBusinessId){
//		this.billBusinessId = billBusinessId;
//	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BILL_BUSINESS_ID")
	public BillBusinessEntity getBillBusiness() {
		return billBusiness;
	}

	public void setBillBusiness(BillBusinessEntity billBusiness) {
		this.billBusiness = billBusiness;
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
}
