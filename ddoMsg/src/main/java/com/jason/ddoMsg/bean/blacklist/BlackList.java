package com.jason.ddoMsg.bean.blacklist;

/**   
 * @Title: Entity
 * @Description: 黑名单
 * @author zhangdaihao
 * @date 2015-03-30 09:22:10
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class BlackList implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**手机号码*/
	private Long msisdn;
	/**状态*/
	private Integer state;
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
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  手机号码
	 */
	
	public Long getMsisdn(){
		return this.msisdn;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  手机号码
	 */
	public void setMsisdn(Long msisdn){
		this.msisdn = msisdn;
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
