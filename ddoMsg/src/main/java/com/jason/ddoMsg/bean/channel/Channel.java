package com.jason.ddoMsg.bean.channel;

/**   
 * @Title: Entity
 * @Description: 渠道
 * @author zhangdaihao
 * @date 2015-03-30 09:23:54
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class Channel implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**渠道名称*/
	private java.lang.String name;
	/**渠道编号*/
	private Long no;
	/**状态*/
	private java.lang.Integer state;
	/**日限额*/
	private Long dayLimit;
	/**月限额*/
	private Long monthLimit;
	/**关停状态*/
	private java.lang.Integer closeState;
	/**上行地址*/
	private java.lang.String upUrl;
	/**下行地址*/
	private java.lang.String downUrl;
	
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
	 *@return: java.lang.String  渠道名称
	 */
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  渠道名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  渠道编号
	 */
	public Long getNo(){
		return this.no;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  渠道编号
	 */
	public void setNo(Long no){
		this.no = no;
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
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  日限额
	 */
	public Long getDayLimit(){
		return this.dayLimit;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  日限额
	 */
	public void setDayLimit(Long dayLimit){
		this.dayLimit = dayLimit;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  月限额
	 */
	public Long getMonthLimit(){
		return this.monthLimit;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  月限额
	 */
	public void setMonthLimit(Long monthLimit){
		this.monthLimit = monthLimit;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  关停状态
	 */
	public java.lang.Integer getCloseState(){
		return this.closeState;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  关停状态
	 */
	public void setCloseState(java.lang.Integer closeState){
		this.closeState = closeState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上行地址
	 */
	public java.lang.String getUpUrl(){
		return this.upUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上行地址
	 */
	public void setUpUrl(java.lang.String upUrl){
		this.upUrl = upUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  下行地址
	 */
	public java.lang.String getDownUrl(){
		return this.downUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  下行地址
	 */
	public void setDownUrl(java.lang.String downUrl){
		this.downUrl = downUrl;
	}
}
