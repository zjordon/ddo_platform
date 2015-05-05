package com.jason.ddoWeb.entity.msg;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 短信任务对应的手机号码
 * @author zhangdaihao
 * @date 2015-03-30 09:00:40
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_request_msisdn", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class RequestMsisdnEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**手机号*/
	private BigDecimal msisdn;
	/**请求id*/
	private java.lang.String requestId;
	
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
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  手机号
	 */
	@Column(name ="MSISDN",nullable=true,precision=11,scale=0)
	public BigDecimal getMsisdn(){
		return this.msisdn;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  手机号
	 */
	public void setMsisdn(BigDecimal msisdn){
		this.msisdn = msisdn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请求id
	 */
	@Column(name ="REQUEST_ID",nullable=false,length=32)
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
