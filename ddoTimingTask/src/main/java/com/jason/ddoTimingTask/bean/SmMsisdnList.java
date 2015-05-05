package com.jason.ddoTimingTask.bean;

/**   
 * @Title: Entity
 * @Description: 号码清单
 * @author zhangdaihao
 * @date 2015-04-20 15:03:31
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class SmMsisdnList implements java.io.Serializable {

	/**唯一标识*/
	private java.lang.String id;
	/**手机号码*/
	private java.lang.Long msisdn;
	/**任务id*/
	private java.lang.String smTaskId;
	/**请求id*/
	private java.lang.String smRequestId;
	
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  手机号码
	 */
	public java.lang.Long getMsisdn(){
		return this.msisdn;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  手机号码
	 */
	public void setMsisdn(java.lang.Long msisdn){
		this.msisdn = msisdn;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请求id
	 */
	public java.lang.String getSmRequestId(){
		return this.smRequestId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请求id
	 */
	public void setSmRequestId(java.lang.String smRequestId){
		this.smRequestId = smRequestId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SmMsisdnList) {
			return ((SmMsisdnList)obj).getMsisdn().equals(this.msisdn);
		}
		return false;
	}
}
