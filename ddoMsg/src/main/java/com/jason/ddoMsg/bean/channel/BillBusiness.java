package com.jason.ddoMsg.bean.channel;

/**   
 * @Title: Entity
 * @Description: 计费业务
 * @author zhangdaihao
 * @date 2015-03-30 09:17:46
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class BillBusiness implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**计费点名称*/
	private java.lang.String name;
	/**计费价格*/
	private Long price;
	/**计费业务代码*/
	private java.lang.String code;
	/**状态*/
	private Integer state;
	
	private String channelBillCode;
	
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
	 *@return: java.lang.String  计费点名称
	 */
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计费点名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  计费价格
	 */
	public Long getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  计费价格
	 */
	public void setPrice(Long price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计费业务代码
	 */
	public java.lang.String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计费业务代码
	 */
	public void setCode(java.lang.String code){
		this.code = code;
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

	public String getChannelBillCode() {
		return channelBillCode;
	}

	public void setChannelBillCode(String channelBillCode) {
		this.channelBillCode = channelBillCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(this.id).append("name:").append(this.name);
		builder.append("price:").append(this.price).append("code:").append(this.code);
		builder.append("state:").append(state);
		return builder.toString();
	}
}
