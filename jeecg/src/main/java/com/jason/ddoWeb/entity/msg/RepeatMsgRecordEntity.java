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
 * @Description: 消息重发记录
 * @author zhangdaihao
 * @date 2015-04-14 14:21:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_repeat_msg_record", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class RepeatMsgRecordEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**创建时间*/
	private java.util.Date createDate;
	/**返回消息编码*/
	private java.lang.String returnMsgCode;
	/**请求id*/
	private java.lang.String ddoMsgId;
	/**请求返回时间*/
	private java.util.Date responseTime;
	
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
	 *@return: java.lang.String  返回消息编码
	 */
	@Column(name ="RETURN_MSG_CODE",nullable=true,length=8)
	public java.lang.String getReturnMsgCode(){
		return this.returnMsgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  返回消息编码
	 */
	public void setReturnMsgCode(java.lang.String returnMsgCode){
		this.returnMsgCode = returnMsgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请求id
	 */
	@Column(name ="DDO_MSG_ID",nullable=false,length=32)
	public java.lang.String getDdoMsgId(){
		return this.ddoMsgId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请求id
	 */
	public void setDdoMsgId(java.lang.String ddoMsgId){
		this.ddoMsgId = ddoMsgId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  请求返回时间
	 */
	@Column(name ="RESPONSE_TIME",nullable=true)
	public java.util.Date getResponseTime(){
		return this.responseTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  请求返回时间
	 */
	public void setResponseTime(java.util.Date responseTime){
		this.responseTime = responseTime;
	}
}
