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
 * @Description: 短信任务
 * @author zhangdaihao
 * @date 2015-03-30 08:54:27
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_channel_request", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ChannelRequestEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**用户名*/
	private java.lang.String username;
	/**密码*/
	private java.lang.String password;
	/**发送指令*/
	private java.lang.String content;
	/**产品id*/
	private java.lang.String productId;
	/**定时时间*/
	private java.util.Date dstime;
	/**请求时间*/
	private java.util.Date requestTime;
	/**状态*/
	private java.lang.Integer state;
	/**返回状态*/
	private java.lang.Integer returnState;
	/**开始处理时间*/
	private java.util.Date beginTime;
	/**处理完成时间*/
	private java.util.Date endTime;
	/**渠道id*/
	private java.lang.String channelId;
	/**来源类型*/
	private java.lang.Integer sourceType;
	/**处理结果*/
	private java.lang.Integer processResult;
	
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
	 *@return: java.lang.String  用户名
	 */
	@Column(name ="USERNAME",nullable=false,length=32)
	public java.lang.String getUsername(){
		return this.username;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户名
	 */
	public void setUsername(java.lang.String username){
		this.username = username;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  密码
	 */
	@Column(name ="PASSWORD",nullable=false,length=32)
	public java.lang.String getPassword(){
		return this.password;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  密码
	 */
	public void setPassword(java.lang.String password){
		this.password = password;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发送指令
	 */
	@Column(name ="CONTENT",nullable=true,length=32)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发送指令
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品id
	 */
	@Column(name ="PRODUCT_ID",nullable=true,length=32)
	public java.lang.String getProductId(){
		return this.productId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品id
	 */
	public void setProductId(java.lang.String productId){
		this.productId = productId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  定时时间
	 */
	@Column(name ="DSTIME",nullable=true)
	public java.util.Date getDstime(){
		return this.dstime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  定时时间
	 */
	public void setDstime(java.util.Date dstime){
		this.dstime = dstime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  请求时间
	 */
	@Column(name ="REQUEST_TIME",nullable=true)
	public java.util.Date getRequestTime(){
		return this.requestTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  请求时间
	 */
	public void setRequestTime(java.util.Date requestTime){
		this.requestTime = requestTime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  状态
	 */
	@Column(name ="STATE",nullable=false,precision=5,scale=0)
	public java.lang.Integer getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  状态
	 */
	public void setState(java.lang.Integer state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  返回状态
	 */
	@Column(name ="RETURN_STATE",nullable=false,precision=5,scale=0)
	public java.lang.Integer getReturnState(){
		return this.returnState;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  返回状态
	 */
	public void setReturnState(java.lang.Integer returnState){
		this.returnState = returnState;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始处理时间
	 */
	@Column(name ="BEGIN_TIME",nullable=false)
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  处理完成时间
	 */
	@Column(name ="END_TIME",nullable=true,length=10)
	public java.util.Date getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  处理完成时间
	 */
	public void setEndTime(java.util.Date endTime){
		this.endTime = endTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  渠道id
	 */
	@Column(name ="CHANNEL_ID",nullable=true,length=32)
	public java.lang.String getChannelId(){
		return this.channelId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  渠道id
	 */
	public void setChannelId(java.lang.String channelId){
		this.channelId = channelId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  来源类型
	 */
	@Column(name ="SOURCE_TYPE",nullable=false,precision=5,scale=0)
	public java.lang.Integer getSourceType(){
		return this.sourceType;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  来源类型
	 */
	public void setSourceType(java.lang.Integer sourceType){
		this.sourceType = sourceType;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  处理结果
	 */
	@Column(name ="PROCESS_RESULT",nullable=false,precision=5,scale=0)
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
}
