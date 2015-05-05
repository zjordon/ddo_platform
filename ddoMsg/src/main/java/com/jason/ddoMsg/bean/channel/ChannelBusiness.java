package com.jason.ddoMsg.bean.channel;

/**   
 * @Title: Entity
 * @Description: 渠道业务
 * @author zhangdaihao
 * @date 2015-03-30 09:18:24
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class ChannelBusiness implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**产品指令*/
	private java.lang.String instruct;
	/**状态*/
	private Integer state;
	/**关停状态*/
	private Integer closeState;
	/**计费业务id*/
	private java.lang.String billBusinessId;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品指令
	 */
	public java.lang.String getInstruct(){
		return this.instruct;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品指令
	 */
	public void setInstruct(java.lang.String instruct){
		this.instruct = instruct;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  状态
	 */
	public Integer getState(){
		return this.state;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  状态
	 */
	public void setState(Integer state){
		this.state = state;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  关停状态
	 */
	public Integer getCloseState(){
		return this.closeState;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  关停状态
	 */
	public void setCloseState(Integer closeState){
		this.closeState = closeState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计费业务id
	 */
	public java.lang.String getBillBusinessId(){
		return this.billBusinessId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计费业务id
	 */
	public void setBillBusinessId(java.lang.String billBusinessId){
		this.billBusinessId = billBusinessId;
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
