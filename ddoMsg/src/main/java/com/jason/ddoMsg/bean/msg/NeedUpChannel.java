package com.jason.ddoMsg.bean.msg;

/**   
 * @Title: Entity
 * @Description: 需要重新上行的记录
 * @author zhangdaihao
 * @date 2015-03-30 09:07:42
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class NeedUpChannel implements java.io.Serializable {
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
