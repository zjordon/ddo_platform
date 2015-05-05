package com.jason.ddoWeb.entity.smtask;

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
 * @Description: 短信请求
 * @author zhangdaihao
 * @date 2015-04-20 13:41:26
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_sm_request", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SmRequestEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**返回请求编号*/
	private java.lang.String responseNo;
	/**返回状态*/
	private java.lang.Integer responseState;
	/**请求返回时间*/
	private java.util.Date responseTime;
	/**发送时间*/
	private java.util.Date sendTime;
	/**任务id*/
	private java.lang.String smTaskId;
	
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
	 *@return: java.lang.String  返回请求编号
	 */
	@Column(name ="RESPONSE_NO",nullable=true,length=32)
	public java.lang.String getResponseNo(){
		return this.responseNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  返回请求编号
	 */
	public void setResponseNo(java.lang.String responseNo){
		this.responseNo = responseNo;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  返回状态
	 */
	@Column(name ="RESPONSE_STATE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getResponseState(){
		return this.responseState;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  返回状态
	 */
	public void setResponseState(java.lang.Integer responseState){
		this.responseState = responseState;
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
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  发送时间
	 */
	@Column(name ="SEND_TIME",nullable=true)
	public java.util.Date getSendTime(){
		return this.sendTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  发送时间
	 */
	public void setSendTime(java.util.Date sendTime){
		this.sendTime = sendTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务id
	 */
	@Column(name ="SM_TASK_ID",nullable=false,length=32)
	public java.lang.String getSmTaskId(){
		return this.smTaskId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务id
	 */
	public void setSmTaskId(java.lang.String smTaskId){
		this.smTaskId = smTaskId;
	}
}
