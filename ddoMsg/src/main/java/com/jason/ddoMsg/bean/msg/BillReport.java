package com.jason.ddoMsg.bean.msg;

/**   
 * @Title: Entity
 * @Description: 计费状态报告
 * @author zhangdaihao
 * @date 2015-03-30 09:03:15
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class BillReport implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**事务id*/
	private java.lang.String transationId;
	/**计费状态编码*/
	private java.lang.String billStateCode;
	/**状态*/
	private java.lang.Integer state;
	/**处理结果*/
	private java.lang.Integer processResult;
	/**下发状态报告结果编码*/
	private java.lang.String resultCode;
	/**创建时间*/
	private java.util.Date createDate;
	
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
	 *@return: java.lang.String  事务id
	 */
	
	public java.lang.String getTransationId(){
		return this.transationId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  事务id
	 */
	public void setTransationId(java.lang.String transationId){
		this.transationId = transationId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计费状态编码
	 */
	
	public java.lang.String getBillStateCode(){
		return this.billStateCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计费状态编码
	 */
	public void setBillStateCode(java.lang.String billStateCode){
		this.billStateCode = billStateCode;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  状态
	 */
	
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
	 *@return: java.lang.Integer  处理结果
	 */
	
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
	 *@return: java.lang.String  下发状态报告结果编码
	 */
	
	public java.lang.String getResultCode(){
		return this.resultCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  下发状态报告结果编码
	 */
	public void setResultCode(java.lang.String resultCode){
		this.resultCode = resultCode;
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
}
