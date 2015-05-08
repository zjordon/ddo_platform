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
 * @Description: 全量预警
 * @author zhangdaihao
 * @date 2015-05-08 16:32:14
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_f_complaint_month_view", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class FComplaintMonthViewEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**sumAmount*/
	private java.lang.Double sumAmount;
	/**sumMonth*/
	private java.lang.Integer sumMonth;
	/**msisdnNum*/
	private java.lang.Integer msisdnNum;
	/**num*/
	private java.lang.Integer num;
	/**fullComplaintId*/
	private java.lang.String fullComplaintId;
	/**scale*/
	private BigDecimal scale;
	
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  sumAmount
	 */
	@Column(name ="SUM_AMOUNT",nullable=false,precision=18,scale=2)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  num
	 */
	@Column(name ="NUM",nullable=true,precision=19,scale=0)
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  fullComplaintId
	 */
	@Column(name ="FULL_COMPLAINT_ID",nullable=true,length=32)
	public java.lang.String getFullComplaintId(){
		return this.fullComplaintId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  fullComplaintId
	 */
	public void setFullComplaintId(java.lang.String fullComplaintId){
		this.fullComplaintId = fullComplaintId;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  scale
	 */
	@Column(name ="SCALE",nullable=true,precision=18,scale=2)
	public BigDecimal getScale(){
		return this.scale;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  scale
	 */
	public void setScale(BigDecimal scale){
		this.scale = scale;
	}
}
