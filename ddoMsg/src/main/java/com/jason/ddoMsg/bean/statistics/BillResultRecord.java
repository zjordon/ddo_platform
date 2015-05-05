package com.jason.ddoMsg.bean.statistics;

/**   
 * @Title: Entity
 * @Description: 计费结果
 * @author zhangdaihao
 * @date 2015-04-23 15:10:58
 * @version V1.0   
 *
 */
public class BillResultRecord implements java.io.Serializable {
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
