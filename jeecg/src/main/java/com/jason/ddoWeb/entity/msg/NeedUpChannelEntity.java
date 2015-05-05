package com.jason.ddoWeb.entity.msg;

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
 * @Description: 需要重新上行的记录
 * @author zhangdaihao
 * @date 2015-03-30 09:07:42
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_need_up_channel", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class NeedUpChannelEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**创建时间*/
	private java.util.Date createDate;
	/**重发原因*/
	private java.lang.String repeatReason;
	/**重发记录id*/
	private java.lang.String recordId;
	
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  重发原因
	 */
	@Column(name ="REPEAT_REASON",nullable=true,length=32)
	public java.lang.String getRepeatReason(){
		return this.repeatReason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  重发原因
	 */
	public void setRepeatReason(java.lang.String repeatReason){
		this.repeatReason = repeatReason;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  重发记录id
	 */
	@Column(name ="RECORD_ID",nullable=true,length=32)
	public java.lang.String getRecordId(){
		return this.recordId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  重发记录id
	 */
	public void setRecordId(java.lang.String recordId){
		this.recordId = recordId;
	}
}
