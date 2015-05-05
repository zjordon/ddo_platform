package com.jason.ddoMsg.bean.msg;

/**   
 * @Title: Entity
 * @Description: 重新上行记录
 * @author zhangdaihao
 * @date 2015-03-30 09:08:27
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class RepeatUpRecord implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**创建时间*/
	private java.util.Date createDate;
	/**重发原因*/
	private java.lang.String repeatReason;
	/**重发时间*/
	private java.util.Date repeatTime;
	/**上行结果编码*/
	private java.lang.String resultCode;
	/**重发记录id*/
	private java.lang.String recordId;
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  重发记录id
	 */
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
