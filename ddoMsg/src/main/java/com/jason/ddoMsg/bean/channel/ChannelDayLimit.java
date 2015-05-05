package com.jason.ddoMsg.bean.channel;

/**   
 * @Title: Entity
 * @Description: 渠道日使用限额
 * @author zhangdaihao
 * @date 2015-03-31 11:36:36
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class ChannelDayLimit implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**日期*/
	private java.lang.Integer day;
	/**限额*/
	private Long limitAmount;
	/**渠道id*/
	private java.lang.String channelId;
	
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
	 *@return: java.lang.Integer  日期
	 */
	public java.lang.Integer getDay(){
		return this.day;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  日期
	 */
	public void setDay(java.lang.Integer day){
		this.day = day;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  限额
	 */
	public Long getLimitAmount(){
		return this.limitAmount;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  限额
	 */
	public void setLimitAmount(Long limitAmount){
		this.limitAmount = limitAmount;
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
}
