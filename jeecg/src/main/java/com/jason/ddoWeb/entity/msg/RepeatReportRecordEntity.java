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
 * @Description: 重新下发状态报告记录
 * @author zhangdaihao
 * @date 2015-04-07 16:46:27
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_repeat_report_record", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class RepeatReportRecordEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**重发原因*/
	private java.lang.String repeatReason;
	/**创建时间*/
	private java.util.Date createDate;
	/**重发时间*/
	private java.util.Date repeatDate;
	/**下发状态报告结果编码*/
	private java.lang.String resultCode;
	/**对应的计费状态报告id*/
	private java.lang.String billReportId;
	
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
	 *@return: java.lang.String  重发原因
	 */
	@Column(name ="REPEAT_REASON",nullable=true,length=32)
	public java.lang.String getRepeatReason(){
		return this.repeatReason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  重发原因
	 */
	public void setRepeatReason(java.lang.String repeatReason){
		this.repeatReason = repeatReason;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  重发时间
	 */
	@Column(name ="REPEAT_DATE",nullable=true)
	public java.util.Date getRepeatDate(){
		return this.repeatDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  重发时间
	 */
	public void setRepeatDate(java.util.Date repeatDate){
		this.repeatDate = repeatDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  下发状态报告结果编码
	 */
	@Column(name ="RESULT_CODE",nullable=true,length=32)
	public java.lang.String getResultCode(){
		return this.resultCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  下发状态报告结果编码
	 */
	public void setResultCode(java.lang.String resultCode){
		this.resultCode = resultCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对应的计费状态报告id
	 */
	@Column(name ="BILL_REPORT_ID",nullable=false,length=32)
	public java.lang.String getBillReportId(){
		return this.billReportId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对应的计费状态报告id
	 */
	public void setBillReportId(java.lang.String billReportId){
		this.billReportId = billReportId;
	}
}
