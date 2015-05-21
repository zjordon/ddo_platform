package com.jason.ddoWeb.entity.smtask;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.jason.ddoWeb.entity.channel.ChannelEntity;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 短信任务
 * @author zhangdaihao
 * @date 2015-04-20 13:53:12
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_sm_task", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SmTaskEntity implements java.io.Serializable {
	/**唯一标识*/
	private java.lang.String id;
	/**号码数量*/
	private java.lang.Integer msisdnNum;
	/**是否去重*/
	private java.lang.Integer recapture;
	/**发送类型*/
	private java.lang.Integer sendType;
	/**定时发送时间*/
	private java.util.Date timeToSendTime;
	/**状态*/
	private java.lang.Integer state;
	/**执行时间*/
	private java.util.Date executeTime;
	/**创建时间*/
	private java.util.Date createDate;
	/**创建人*/
	private java.lang.String createName;
	/**渠道id*/
	private java.lang.String channelId;
	//private ChannelEntity channel;
	/**计费业务id*/
	private java.lang.String billBusinessId;
	
	private String channelUserName;
	private String channelUserPass;
	private String failMsg;
	
//	private List<SmRequestEntity> smRequests;
//	private List<SmMsisdnListEntity> smMsisdnLists;
	
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
	 *@return: java.lang.Integer  号码数量
	 */
	@Column(name ="MSISDN_NUM",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMsisdnNum(){
		return this.msisdnNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  号码数量
	 */
	public void setMsisdnNum(java.lang.Integer msisdnNum){
		this.msisdnNum = msisdnNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否去重
	 */
	@Column(name ="RECAPTURE",nullable=true,precision=3,scale=0)
	public java.lang.Integer getRecapture(){
		return this.recapture;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否去重
	 */
	public void setRecapture(java.lang.Integer recapture){
		this.recapture = recapture;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  发送类型
	 */
	@Column(name ="SEND_TYPE",nullable=false,precision=5,scale=0)
	public java.lang.Integer getSendType(){
		return this.sendType;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  发送类型
	 */
	public void setSendType(java.lang.Integer sendType){
		this.sendType = sendType;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  定时发送时间
	 */
	@Column(name ="TIME_TO_SEND_TIME",nullable=true)
	public java.util.Date getTimeToSendTime(){
		return this.timeToSendTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  定时发送时间
	 */
	public void setTimeToSendTime(java.util.Date timeToSendTime){
		this.timeToSendTime = timeToSendTime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  状态
	 */
	@Column(name ="STATE",nullable=true,precision=5,scale=0)
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  执行时间
	 */
	@Column(name ="EXECUTE_TIME",nullable=true)
	public java.util.Date getExecuteTime(){
		return this.executeTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  执行时间
	 */
	public void setExecuteTime(java.util.Date executeTime){
		this.executeTime = executeTime;
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
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=32)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  渠道id
	 */
	@Column(name ="CHANNEL_ID",nullable=false,length=32)
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
	
//	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
//    @JoinColumn(name = "channel_id")
//	public ChannelEntity getChannel() {
//		return channel;
//	}
//
//	public void setChannel(ChannelEntity channel) {
//		this.channel = channel;
//	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计费业务id
	 */
	@Column(name ="BILL_BUSINESS_ID",nullable=false,length=32)
	public java.lang.String getBillBusinessId(){
		return this.billBusinessId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计费业务id
	 */
	public void setBillBusinessId(java.lang.String billBusinessId){
		this.billBusinessId = billBusinessId;
	}
	
	@Column(name ="channel_user_name",nullable=true)
	public String getChannelUserName() {
		return channelUserName;
	}

	public void setChannelUserName(String channelUserName) {
		this.channelUserName = channelUserName;
	}

	@Column(name ="channel_user_pass",nullable=true)
	public String getChannelUserPass() {
		return channelUserPass;
	}

	public void setChannelUserPass(String channelUserPass) {
		this.channelUserPass = channelUserPass;
	}

	@Column(name ="fail_msg",nullable=true)
	public String getFailMsg() {
		return failMsg;
	}

	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}

	//@OneToMany(cascade=CascadeType.REMOVE)
	//@JoinColumn(name="SM_TASK_ID")
//	@OneToMany(mappedBy="channel", cascade=CascadeType.REMOVE,orphanRemoval=true)
//	public List<SmRequestEntity> getSmRequests() {
//		return smRequests;
//	}
//
//	public void setSmRequests(List<SmRequestEntity> smRequests) {
//		this.smRequests = smRequests;
//	}

	//@OneToMany(cascade=CascadeType.REMOVE)
	//@JoinColumn(name="SM_TASK_ID")
//	@OneToMany(mappedBy="channel", cascade=CascadeType.REMOVE,orphanRemoval=true)
//	public List<SmMsisdnListEntity> getSmMsisdnLists() {
//		return smMsisdnLists;
//	}
//
//	public void setSmMsisdnLists(List<SmMsisdnListEntity> smMsisdnLists) {
//		this.smMsisdnLists = smMsisdnLists;
//	}

}
