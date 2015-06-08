package com.jason.ddoWeb.entity.warning;


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
 * @Description: 渠道预警按月
 * @author zhangdaihao
 * @date 2015-05-08 16:47:09
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_c_complaint_month_view", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class CComplaintMonthViewEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**channelId*/
	private java.lang.String channelId;
	/**sumAmount*/
	private java.lang.Double sumAmount;
	/**sumMonth*/
	private java.lang.Integer sumMonth;
	/**msisdnNum*/
	private java.lang.Integer msisdnNum;
	/**num*/
	private java.lang.Integer num;
	/**channelComplaintId*/
	private java.lang.String channelComplaintId;
	/**万投比*/
	private Double scale;
	/**投诉数管控阀值*/
	private Integer numThreshold;
	/**万投比管控阀值*/
	private Double ratioThreshold;
	/**渠道名称*/
	private String channelName;
	/**状态*/
	private Integer state;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
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
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  channelId
	 */
	@Column(name ="CHANNEL_ID",nullable=false,length=32)
	public java.lang.String getChannelId(){
		return this.channelId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  channelId
	 */
	public void setChannelId(java.lang.String channelId){
		this.channelId = channelId;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  sumAmount
	 */
	@Column(name ="SUM_AMOUNT",nullable=false,precision=18,scale=2)
	public java.lang.Double getSumAmount(){
		return this.sumAmount;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  sumAmount
	 */
	public void setSumAmount(java.lang.Double sumAmount){
		this.sumAmount = sumAmount;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  sumMonth
	 */
	@Column(name ="SUM_MONTH",nullable=false,precision=10,scale=0)
	public java.lang.Integer getSumMonth(){
		return this.sumMonth;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  sumMonth
	 */
	public void setSumMonth(java.lang.Integer sumMonth){
		this.sumMonth = sumMonth;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  msisdnNum
	 */
	@Column(name ="MSISDN_NUM",nullable=false,precision=10,scale=0)
	public java.lang.Integer getMsisdnNum(){
		return this.msisdnNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  msisdnNum
	 */
	public void setMsisdnNum(java.lang.Integer msisdnNum){
		this.msisdnNum = msisdnNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  num
	 */
	@Column(name ="NUM",nullable=true,precision=19,scale=0)
	public java.lang.Integer getNum(){
		return this.num;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  num
	 */
	public void setNum(java.lang.Integer num){
		this.num = num;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  channelComplaintId
	 */
	@Column(name ="CHANNEL_COMPLAINT_ID",nullable=true,length=32)
	public java.lang.String getChannelComplaintId(){
		return this.channelComplaintId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  channelComplaintId
	 */
	public void setChannelComplaintId(java.lang.String channelComplaintId){
		this.channelComplaintId = channelComplaintId;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  scale
	 */
	@Column(name ="SCALE",nullable=true,precision=18,scale=2)
	public Double getScale(){
		return this.scale;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  scale
	 */
	public void setScale(Double scale){
		this.scale = scale;
	}

	@Column(name ="NUM_THRESHOLD",nullable=true,precision=19,scale=0)
	public Integer getNumThreshold() {
		return numThreshold;
	}

	public void setNumThreshold(Integer numThreshold) {
		this.numThreshold = numThreshold;
	}

	@Column(name ="ratio_threshold",nullable=true,precision=18,scale=2)
	public Double getRatioThreshold() {
		return ratioThreshold;
	}

	public void setRatioThreshold(Double ratioThreshold) {
		this.ratioThreshold = ratioThreshold;
	}

	@Column(name ="channel_name",nullable=true,length=32)
	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	@Column(name ="state",nullable=true,precision=19,scale=0)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
