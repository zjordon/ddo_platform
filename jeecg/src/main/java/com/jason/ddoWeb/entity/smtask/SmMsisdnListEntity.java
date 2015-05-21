package com.jason.ddoWeb.entity.smtask;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 号码清单
 * @author zhangdaihao
 * @date 2015-04-20 15:03:31
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_sm_msisdn_list", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SmMsisdnListEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**手机号码*/
	private java.lang.Long msisdn;
	/**任务id*/
	private java.lang.String smTaskId;
//	private SmTaskEntity smTask;
	/**请求id*/
	private java.lang.String smRequestId;
	
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  手机号码
	 */
	@Column(name ="MSISDN",nullable=true,precision=19,scale=0)
	public java.lang.Long getMsisdn(){
		return this.msisdn;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  手机号码
	 */
	public void setMsisdn(java.lang.Long msisdn){
		this.msisdn = msisdn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务id
	 */
	@Column(name ="SM_TASK_ID",nullable=false,length=32)
	public java.lang.String getSmTaskId(){
		return this.smTaskId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务id
	 */
	public void setSmTaskId(java.lang.String smTaskId){
		this.smTaskId = smTaskId;
	}
	
//	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
//    @JoinColumn(name = "sm_task_id")
//	public SmTaskEntity getSmTask() {
//		return smTask;
//	}

//	public void setSmTask(SmTaskEntity smTask) {
//		this.smTask = smTask;
//	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请求id
	 */
	@Column(name ="SM_REQUEST_ID",nullable=true,length=32)
	public java.lang.String getSmRequestId(){
		return this.smRequestId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请求id
	 */
	public void setSmRequestId(java.lang.String smRequestId){
		this.smRequestId = smRequestId;
	}
}
