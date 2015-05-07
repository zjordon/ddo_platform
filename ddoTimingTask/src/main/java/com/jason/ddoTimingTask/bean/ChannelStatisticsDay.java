package com.jason.ddoTimingTask.bean;

/**   
 * @Title: Entity
 * @Description: 分渠道统计
 * @author zhangdaihao
 * @date 2015-04-23 15:12:11
 * @version V1.0   
 *
 */
public class ChannelStatisticsDay implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**统计日期*/
	private java.lang.Integer sumDate;
	/**渠道id*/
	private java.lang.String channelId;
	/**用户数*/
	private java.lang.Integer msisdnNum;
	/**计费金额*/
	private java.lang.Double sumAmount;
	/**短信条数*/
	private java.lang.Integer msgNum;
	/**发送成功数*/
	private java.lang.Integer sendSuccessNum;
	/**发送失败数*/
	private java.lang.Integer sendFailNum;
	/**计费成功数*/
	private java.lang.Integer billSuccessNum;
	/**计费失败数*/
	private java.lang.Integer billFailNum;
	/**持久化状态*/
	private short persistenceState;
	
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
	 *@return: java.lang.Integer  统计日期
	 */
	public java.lang.Integer getSumDate(){
		return this.sumDate;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  统计日期
	 */
	public void setSumDate(java.lang.Integer sumDate){
		this.sumDate = sumDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  渠道id
	 */
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
	 *@return: java.lang.Integer  用户数
	 */
	public java.lang.Integer getMsisdnNum(){
		return this.msisdnNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  用户数
	 */
	public void setMsisdnNum(java.lang.Integer msisdnNum){
		this.msisdnNum = msisdnNum;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  计费金额
	 */
	public java.lang.Double getSumAmount(){
		return this.sumAmount;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  计费金额
	 */
	public void setSumAmount(java.lang.Double sumAmount){
		this.sumAmount = sumAmount;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  短信条数
	 */
	public java.lang.Integer getMsgNum(){
		return this.msgNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  短信条数
	 */
	public void setMsgNum(java.lang.Integer msgNum){
		this.msgNum = msgNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  发送成功数
	 */
	public java.lang.Integer getSendSuccessNum(){
		return this.sendSuccessNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  发送成功数
	 */
	public void setSendSuccessNum(java.lang.Integer sendSuccessNum){
		this.sendSuccessNum = sendSuccessNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  发送失败数
	 */
	public java.lang.Integer getSendFailNum(){
		return this.sendFailNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  发送失败数
	 */
	public void setSendFailNum(java.lang.Integer sendFailNum){
		this.sendFailNum = sendFailNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  计费成功数
	 */
	public java.lang.Integer getBillSuccessNum(){
		return this.billSuccessNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  计费成功数
	 */
	public void setBillSuccessNum(java.lang.Integer billSuccessNum){
		this.billSuccessNum = billSuccessNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  计费失败数
	 */
	public java.lang.Integer getBillFailNum(){
		return this.billFailNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  计费失败数
	 */
	public void setBillFailNum(java.lang.Integer billFailNum){
		this.billFailNum = billFailNum;
	}
	
	public short getPersistenceState() {
		return persistenceState;
	}

	public void setPersistenceState(short persistenceState) {
		this.persistenceState = persistenceState;
	}
	
	public void increaseMsisdnNum() {
		this.msisdnNum = new Integer(this.msisdnNum + 1);
	}
	
	public void increaseMsgNum() {
		this.msgNum = new Integer(this.msgNum + 1);
	}
}
