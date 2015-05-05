package com.jason.ddoWeb.entity.statistics;

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
 * @Description: 计费结果
 * @author zhangdaihao
 * @date 2015-04-23 15:10:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_bill_result_record", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class BillResultRecordEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**ddo消息id*/
	private java.lang.String ddoMsgId;
	/**计费结果*/
	private java.lang.Integer billResult;
	
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
	 *@return: java.lang.String  ddo消息id
	 */
	@Column(name ="DDO_MSG_ID",nullable=true,length=32)
	public java.lang.String getDdoMsgId(){
		return this.ddoMsgId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ddo消息id
	 */
	public void setDdoMsgId(java.lang.String ddoMsgId){
		this.ddoMsgId = ddoMsgId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  计费结果
	 */
	@Column(name ="BILL_RESULT",nullable=true,precision=3,scale=0)
	public java.lang.Integer getBillResult(){
		return this.billResult;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  计费结果
	 */
	public void setBillResult(java.lang.Integer billResult){
		this.billResult = billResult;
	}
}
