package com.jason.ddoWeb.entity.channel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 渠道用户
 * @author zhangdaihao
 * @date 2015-04-14 17:11:17
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_channel_user", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ChannelUserEntity implements java.io.Serializable {
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
	
	private String channelId;
	
//	private ChannelEntity channel;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  唯一标识
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
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
	@Column(name ="USERNAME",nullable=false,length=32)
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
	@Column(name ="PASSWORD",nullable=false,length=32)
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
	@Column(name ="MSISDN",nullable=true,precision=11,scale=0)
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
	@Column(name ="STATE",nullable=true,precision=3,scale=0)
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

	@Column(name ="channel_id")
	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "channel_id")
//	public ChannelEntity getChannel() {
//		return channel;
//	}
//
//	public void setChannel(ChannelEntity channel) {
//		this.channel = channel;
//	}
	
}
