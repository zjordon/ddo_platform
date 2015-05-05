package com.jason.ddoWeb.entity.channel;

import java.math.BigDecimal;

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
 * @Description: 省份关停状态
 * @author zhangdaihao
 * @date 2015-05-04 15:17:37
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_provice_close_state", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ProviceCloseStateEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**省份编码*/
	private java.lang.String proviceCode;
	/**关停状态*/
	private Integer closeState;
	/**省份的拼音名称*/
	private java.lang.String provicePinyin;
	
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
	 *@return: java.lang.String  省份编码
	 */
	@Column(name ="PROVICE_CODE",nullable=false,length=10)
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
	@Column(name ="CLOSE_STATE",nullable=false,precision=1,scale=0)
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  省份的拼音名称
	 */
	@Column(name ="PROVICE_PINYIN",nullable=false,length=32)
	public java.lang.String getProvicePinyin(){
		return this.provicePinyin;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  省份的拼音名称
	 */
	public void setProvicePinyin(java.lang.String provicePinyin){
		this.provicePinyin = provicePinyin;
	}
}
