package com.jason.ddoMsg.bean.channel;

/**   
 * @Title: Entity
 * @Description: 渠道限额使用流水
 * @author zhangdaihao
 * @date 2015-03-30 09:15:42
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class LimitUseLog implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**本次使用的金额*/
	private Long useAmount;
	/**使用后剩余的日金额*/
	private Long remainDayAmount;
	/**使用后剩余的月金额*/
	private Long remainMonthAmount;
	/**创建时间*/
	private java.util.Date createDate;
	/**请求id*/
	private java.lang.String requestId;
	
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
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  本次使用的金额
	 */
	public Long getUseAmount(){
		return this.useAmount;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  本次使用的金额
	 */
	public void setUseAmount(Long useAmount){
		this.useAmount = useAmount;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  使用后剩余的日金额
	 */
	public Long getRemainDayAmount(){
		return this.remainDayAmount;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  使用后剩余的日金额
	 */
	public void setRemainDayAmount(Long remainDayAmount){
		this.remainDayAmount = remainDayAmount;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  使用后剩余的月金额
	 */
	public Long getRemainMonthAmount(){
		return this.remainMonthAmount;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  使用后剩余的月金额
	 */
	public void setRemainMonthAmount(Long remainMonthAmount){
		this.remainMonthAmount = remainMonthAmount;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请求id
	 */
	public java.lang.String getRequestId(){
		return this.requestId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请求id
	 */
	public void setRequestId(java.lang.String requestId){
		this.requestId = requestId;
	}
}
