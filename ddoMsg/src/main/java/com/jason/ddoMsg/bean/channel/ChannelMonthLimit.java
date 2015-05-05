package com.jason.ddoMsg.bean.channel;

/**   
 * @Title: Entity
 * @Description: 渠道月使用限额
 * @author zhangdaihao
 * @date 2015-03-31 11:36:57
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class ChannelMonthLimit implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**日期*/
	private java.lang.Integer month;
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
	
	public java.lang.Integer getMonth() {
		return month;
	}

	public void setMonth(java.lang.Integer month) {
		this.month = month;
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
