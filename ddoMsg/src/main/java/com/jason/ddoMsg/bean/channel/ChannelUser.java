package com.jason.ddoMsg.bean.channel;

/**   
 * @Title: Entity
 * @Description: 渠道用户
 * @author zhangdaihao
 * @date 2015-03-30 09:22:59
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class ChannelUser implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**用户名*/
	private java.lang.String username;
	/**密码*/
	private java.lang.String password;
	/**手机号*/
	private Long msisdn;
	/**状态*/
	private java.lang.Integer state;
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
	 *@return: java.lang.String  用户名
	 */
	public java.lang.String getUsername(){
		return this.username;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户名
	 */
	public void setUsername(java.lang.String username){
		this.username = username;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  密码
	 */
	public java.lang.String getPassword(){
		return this.password;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  密码
	 */
	public void setPassword(java.lang.String password){
		this.password = password;
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
