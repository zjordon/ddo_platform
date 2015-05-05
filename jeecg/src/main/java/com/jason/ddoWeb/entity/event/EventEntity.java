package com.jason.ddoWeb.entity.event;

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
 * @Description: 事件
 * @author zhangdaihao
 * @date 2015-04-14 15:41:52
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_event", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class EventEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**事件ID*/
	private java.lang.String eventId;
	/**创建时间*/
	private java.util.Date createDate;
	/**参数*/
	private java.lang.String param;
	/**开始处理时间*/
	private java.util.Date beginTime;
	/**处理结束时间*/
	private java.util.Date endTime;
	/**处理结果*/
	private java.lang.Integer processResult;
	/**失败原因*/
	private java.lang.String failMsg;
	
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
	 *@return: java.lang.String  事件ID
	 */
	@Column(name ="EVENT_ID",nullable=true,length=64)
	public java.lang.String getEventId(){
		return this.eventId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  事件ID
	 */
	public void setEventId(java.lang.String eventId){
		this.eventId = eventId;
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
	 *@return: java.lang.String  参数
	 */
	@Column(name ="PARAM",nullable=true,length=128)
	public java.lang.String getParam(){
		return this.param;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参数
	 */
	public void setParam(java.lang.String param){
		this.param = param;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始处理时间
	 */
	@Column(name ="BEGIN_TIME",nullable=true)
	public java.util.Date getBeginTime(){
		return this.beginTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始处理时间
	 */
	public void setBeginTime(java.util.Date beginTime){
		this.beginTime = beginTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  处理结束时间
	 */
	@Column(name ="END_TIME",nullable=true)
	public java.util.Date getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  处理结束时间
	 */
	public void setEndTime(java.util.Date endTime){
		this.endTime = endTime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  处理结果
	 */
	@Column(name ="PROCESS_RESULT",nullable=true,precision=3,scale=0)
	public java.lang.Integer getProcessResult(){
		return this.processResult;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  处理结果
	 */
	public void setProcessResult(java.lang.Integer processResult){
		this.processResult = processResult;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  失败原因
	 */
	@Column(name ="FAIL_MSG",nullable=true,length=256)
	public java.lang.String getFailMsg(){
		return this.failMsg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  失败原因
	 */
	public void setFailMsg(java.lang.String failMsg){
		this.failMsg = failMsg;
	}
}
