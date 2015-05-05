package com.jason.ddoWeb.entity.channel;

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
 * @Description: 渠道限额使用流水
 * @author zhangdaihao
 * @date 2015-03-30 09:15:42
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_limit_use_log", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class LimitUseLogEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**本次使用的金额*/
	private BigDecimal useAmount;
	/**使用后剩余的日金额*/
	private BigDecimal remainDayAmount;
	/**使用后剩余的月金额*/
	private BigDecimal remainMonthAmount;
	/**创建时间*/
	private java.util.Date createDate;
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
	 *@return: BigDecimal  本次使用的金额
	 */
	@Column(name ="USE_AMOUNT",nullable=true,precision=17,scale=0)
	public BigDecimal getUseAmount(){
		return this.useAmount;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  本次使用的金额
	 */
	public void setUseAmount(BigDecimal useAmount){
		this.useAmount = useAmount;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  使用后剩余的日金额
	 */
	@Column(name ="REMAIN_DAY_AMOUNT",nullable=true,precision=17,scale=0)
	public BigDecimal getRemainDayAmount(){
		return this.remainDayAmount;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  使用后剩余的日金额
	 */
	public void setRemainDayAmount(BigDecimal remainDayAmount){
		this.remainDayAmount = remainDayAmount;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  使用后剩余的月金额
	 */
	@Column(name ="REMAIN_MONTH_AMOUNT",nullable=true,precision=17,scale=0)
	public BigDecimal getRemainMonthAmount(){
		return this.remainMonthAmount;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  使用后剩余的月金额
	 */
	public void setRemainMonthAmount(BigDecimal remainMonthAmount){
		this.remainMonthAmount = remainMonthAmount;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_DATE",nullable=true)
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
	@Column(name ="REQUEST_ID",nullable=true,length=32)
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
