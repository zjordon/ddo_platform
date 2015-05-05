package com.jason.ddoMsg.bean.msg;

/**   
 * @Title: Entity
 * @Description: 消息的重发记录
 * @author zhangdaihao
 * @date 2015-03-30 09:05:55
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class RepeatMsgRecord implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**重发原因*/
	private java.lang.String repeatReason;
	/**创建时间*/
	private java.util.Date createDate;
	/**重发时间*/
	private java.util.Date repeatTime;
	/**返回消息编码*/
	private java.lang.String returnMsgCode;
	/**请求id*/
	private java.lang.String ddoMsgId;
	
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
	 *@return: java.lang.String  重发原因
	 */
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  重发时间
	 */
	public java.util.Date getRepeatTime(){
		return this.repeatTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  重发时间
	 */
	public void setRepeatTime(java.util.Date repeatTime){
		this.repeatTime = repeatTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  返回消息编码
	 */
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
	 *@return: java.lang.String  消息id
	 */
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
}
