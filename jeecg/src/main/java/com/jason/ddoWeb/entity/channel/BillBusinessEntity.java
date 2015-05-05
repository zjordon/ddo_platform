package com.jason.ddoWeb.entity.channel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 计费业务
 * @author zhangdaihao
 * @date 2015-04-17 09:16:43
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_bill_business", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class BillBusinessEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**计费点名称*/
	private java.lang.String name;
	/**计费价格*/
	private java.lang.Integer price;
	/**计费业务代码*/
	private java.lang.String code;
	/**状态*/
	private java.lang.Integer state;
	/**渠道计费业务代码*/
	private java.lang.String channelBillCode;
	
	private Double priceDouble;
	
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
	 *@return: java.lang.String  计费点名称
	 */
	@Column(name ="NAME",nullable=false,length=64)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计费点名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  计费价格
	 */
	@Column(name ="PRICE",nullable=true,precision=19,scale=0)
	public java.lang.Integer getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  计费价格
	 */
	public void setPrice(java.lang.Integer price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计费业务代码
	 */
	@Column(name ="CODE",nullable=false,length=12)
	public java.lang.String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计费业务代码
	 */
	public void setCode(java.lang.String code){
		this.code = code;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  状态
	 */
	@Column(name ="STATE",nullable=false,precision=5,scale=0)
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  渠道计费业务代码
	 */
	@Column(name ="CHANNEL_BILL_CODE",nullable=false,length=12)
	public java.lang.String getChannelBillCode(){
		return this.channelBillCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  渠道计费业务代码
	 */
	public void setChannelBillCode(java.lang.String channelBillCode){
		this.channelBillCode = channelBillCode;
	}

	@Transient
	public Double getPriceDouble() {
		return priceDouble;
	}

	public void setPriceDouble(Double priceDouble) {
		this.priceDouble = priceDouble;
	}
}
