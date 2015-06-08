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
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 投诉记录
 * @author zhangdaihao
 * @date 2015-06-04 14:55:45
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_complaint_record", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ComplaintRecordEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**投诉日期*/
	@Excel(name="投诉归档日期")
	private java.lang.Integer complaintDate;
	/**投诉月份*/
	private java.lang.Integer complaintMonth;
	/**用户手机号*/
	@Excel(name="用户号码")
	private java.lang.Long msisdn;
	/**省份*/
	@Excel(name="省份")
	private java.lang.String provice;
	/**地市*/
	@Excel(name="地市")
	private java.lang.String city;
	/**创建时间*/
	private java.util.Date createDate;
	
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
	 *@return: java.lang.Integer  投诉日期
	 */
	@Column(name ="COMPLAINT_DATE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getComplaintDate(){
		return this.complaintDate;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  投诉日期
	 */
	public void setComplaintDate(java.lang.Integer complaintDate){
		this.complaintDate = complaintDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  投诉月份
	 */
	@Column(name ="COMPLAINT_MONTH",nullable=false,precision=10,scale=0)
	public java.lang.Integer getComplaintMonth(){
		return this.complaintMonth;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  投诉月份
	 */
	public void setComplaintMonth(java.lang.Integer complaintMonth){
		this.complaintMonth = complaintMonth;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  用户手机号
	 */
	@Column(name ="MSISDN",nullable=true,precision=10,scale=0)
	public java.lang.Long getMsisdn(){
		return this.msisdn;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  用户手机号
	 */
	public void setMsisdn(java.lang.Long msisdn){
		this.msisdn = msisdn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  省份
	 */
	@Column(name ="PROVICE",nullable=true,length=64)
	public java.lang.String getProvice(){
		return this.provice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  省份
	 */
	public void setProvice(java.lang.String provice){
		this.provice = provice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地市
	 */
	@Column(name ="CITY",nullable=true,length=64)
	public java.lang.String getCity(){
		return this.city;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地市
	 */
	public void setCity(java.lang.String city){
		this.city = city;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
}
