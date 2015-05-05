package com.jason.ddoMsg.bean.msg;

/**   
 * @Title: Entity
 * @Description: 短信任务对应的手机号码
 * @author zhangdaihao
 * @date 2015-03-30 09:00:40
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class RequestMsisdn implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**手机号*/
	private Long msisdn;
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
	 *@return: BigDecimal  手机号
	 */
	public Long getMsisdn(){
		return this.msisdn;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  手机号
	 */
	public void setMsisdn(Long msisdn){
		this.msisdn = msisdn;
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
