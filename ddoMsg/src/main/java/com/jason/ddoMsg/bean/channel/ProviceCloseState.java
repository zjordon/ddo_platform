package com.jason.ddoMsg.bean.channel;

/**   
 * @Title: Entity
 * @Description: 省份关停状态
 * @author zhangdaihao
 * @date 2015-03-30 09:19:40
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class ProviceCloseState implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**省份编码*/
	private java.lang.String proviceCode;
	/**关停状态*/
	private Integer closeState;
	
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
	 *@return: java.lang.String  省份编码
	 */
	public java.lang.String getProviceCode(){
		return this.proviceCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  省份编码
	 */
	public void setProviceCode(java.lang.String proviceCode){
		this.proviceCode = proviceCode;
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
}
