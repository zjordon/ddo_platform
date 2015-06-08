package com.jason.ddoWeb.entity.warning;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 分省预警
 * @author zhangdaihao
 * @date 2015-06-05 09:30:28
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_provice_complaint_month", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ProviceComplaintMonthEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**省份编码*/
	private java.lang.String proviceCode;
	/**统计月份*/
	private java.lang.Integer sumMonth;
	/**投诉数*/
	private java.lang.Integer num;
	/**投诉数阀值*/
	private java.lang.Integer numThreshold;
	/**万投比阀值*/
	private java.lang.Double ratioThreshold;
	
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
	 *@return: java.lang.String  省份编码
	 */
	@Column(name ="PROVICE_CODE",nullable=false,length=32)
	public java.lang.String getProviceCode(){
		return this.proviceCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  省份编码
	 */
	public void setProviceCode(java.lang.String proviceCode){
		this.proviceCode = proviceCode;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  统计月份
	 */
	@Column(name ="SUM_MONTH",nullable=true,precision=10,scale=0)
	public java.lang.Integer getSumMonth(){
		return this.sumMonth;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  统计月份
	 */
	public void setSumMonth(java.lang.Integer sumMonth){
		this.sumMonth = sumMonth;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  投诉数阀值
	 */
	@Column(name ="NUM_THRESHOLD",nullable=true,precision=10,scale=0)
	public java.lang.Integer getNumThreshold(){
		return this.numThreshold;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  投诉数阀值
	 */
	public void setNumThreshold(java.lang.Integer numThreshold){
		this.numThreshold = numThreshold;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  万投比阀值
	 */
	@Column(name ="RATIO_THRESHOLD",nullable=true,precision=18,scale=0)
	public java.lang.Double getRatioThreshold(){
		return this.ratioThreshold;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  万投比阀值
	 */
	public void setRatioThreshold(java.lang.Double ratioThreshold){
		this.ratioThreshold = ratioThreshold;
	}
}
