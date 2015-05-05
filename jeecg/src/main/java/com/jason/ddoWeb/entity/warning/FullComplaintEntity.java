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
 * @date 2015-05-04 11:38:13
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_full_complaint_view", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class FullComplaintEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**计费金额*/
	private java.lang.Double sumAmount;
	/**统计日期*/
	private java.lang.Integer sumDate;
	/**用户数*/
	private java.lang.Integer msisdnNum;
	/**num*/
	private java.lang.Integer num;
	/**唯一标识*/
	private java.lang.String fullComplaintId;
	/**scale*/
	private BigDecimal scale;
	
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
	 *@return: java.lang.String  唯一标识
	 */
	@Column(name ="FULL_COMPLAINT_ID",nullable=true,length=32)
	public java.lang.String getFullComplaintId(){
		return this.fullComplaintId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  唯一标识
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
