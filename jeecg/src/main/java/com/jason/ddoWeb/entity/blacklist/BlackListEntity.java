package com.jason.ddoWeb.entity.blacklist;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**   
 * @Title: Entity
 * @Description: 黑名单管理
 * @author zhangdaihao
 * @date 2015-04-16 08:45:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_black_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
@ExcelTarget("blackListEntity")
public class BlackListEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**手机号码*/
	@Excel(name="手机号码")
	private Long msisdn;
	/**状态*/
	//@Excel(name="状态",replace = {"启用_1","禁用_0"})
	private Integer state;
	/**创建时间*/
	private java.util.Date createDate;
	
	public BlackListEntity(){
		//默认状态为启用
		//state = new Integer(1);
	}
	
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
	 *@return: BigDecimal  手机号码
	 */
	@Column(name ="MSISDN",nullable=false,precision=11,scale=0)
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
	@Column(name ="STATE",nullable=false,precision=1,scale=0)
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
	@Column(name ="CREATE_DATE",nullable=false)
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
