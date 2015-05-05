package com.jason.ddoTimingTask.bean;

/**   
 * @Title: Entity
 * @Description: 短信请求
 * @author zhangdaihao
 * @date 2015-04-20 13:41:26
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class SmRequest implements java.io.Serializable {
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
