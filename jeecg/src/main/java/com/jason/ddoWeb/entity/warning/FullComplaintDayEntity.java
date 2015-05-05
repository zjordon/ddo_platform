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
 * @Description: 全量预警投诉
 * @author zhangdaihao
 * @date 2015-05-04 11:40:16
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_full_complaint_day", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class FullComplaintDayEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**统计日期*/
	private java.lang.Integer sumDate;
	/**投诉数*/
	private java.lang.Integer num;
	
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
	 *@return: java.lang.Integer  统计日期
	 */
	@Column(name ="SUM_DATE",nullable=true,precision=10,scale=0)
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
}
