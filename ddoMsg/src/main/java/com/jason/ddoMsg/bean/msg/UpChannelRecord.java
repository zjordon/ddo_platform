package com.jason.ddoMsg.bean.msg;

import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 上行渠道记录
 * @author zhangdaihao
 * @date 2015-03-30 09:06:52
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class UpChannelRecord implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**创建时间*/
	private java.util.Date createDate;
	/**上行结果编码*/
	private java.lang.String resultCode;
	/**上行处理结果*/
	private java.lang.Integer processResult;
	/**消息id*/
	private java.lang.String ddoMsgId;
	
	private Date responseTime;
	private Date sendTime;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  唯一标识
	 */
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
	 *@return: java.lang.String  上行结果编码
	 */
	public java.lang.String getResultCode(){
		return this.resultCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上行结果编码
	 */
	public void setResultCode(java.lang.String resultCode){
		this.resultCode = resultCode;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  上行处理结果
	 */
	public java.lang.Integer getProcessResult(){
		return this.processResult;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  上行处理结果
	 */
	public void setProcessResult(java.lang.Integer processResult){
		this.processResult = processResult;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  消息id
	 */
	public java.lang.String getDdoMsgId(){
		return this.ddoMsgId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  消息id
	 */
	public void setDdoMsgId(java.lang.String ddoMsgId){
		this.ddoMsgId = ddoMsgId;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
}
