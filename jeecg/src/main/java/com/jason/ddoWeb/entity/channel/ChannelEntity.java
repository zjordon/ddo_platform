package com.jason.ddoWeb.entity.channel;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 渠道组管理
 * @author zhangdaihao
 * @date 2015-04-14 14:08:20
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ddo_channel", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ChannelEntity implements java.io.Serializable {
//public class ChannelEntity implements java.io.Serializable, SaveEventI, FlushDirtyEventI {
	/**唯一标识*/
	private java.lang.String id;
	/**渠道名称*/
	private java.lang.String name;
	/**渠道编号*/
	private Long no;
	/**状态*/
	private java.lang.Integer state;
	/**日限额*/
	private Long dayLimit;
	/**月限额*/
	private Long monthLimit;
	/**关停状态*/
	private java.lang.Integer closeState;
	/**上行地址*/
	private java.lang.String upUrl;
	/**下行地址*/
	private java.lang.String downUrl;
	
	private Double dayLimitDouble;
	private Double monthLimitDouble;
	
	private List<ChannelDayLimitEntity> chanelDayLimits;
	private List<ChannelMonthLimitEntity> chanelMonthLimits;
//	private List<SmTaskEntity> smTasks;
	
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
	 *@return: java.lang.String  渠道名称
	 */
	@Column(name ="NAME",nullable=false,length=64)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  渠道名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  渠道编号
	 */
	@Column(name ="NO",nullable=false,precision=11,scale=0)
	public Long getNo(){
		return this.no;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  渠道编号
	 */
	public void setNo(Long no){
		this.no = no;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  状态
	 */
	@Column(name ="STATE",nullable=false,precision=3,scale=0)
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
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  日限额
	 */
	@Column(name ="DAY_LIMIT",nullable=false,precision=17,scale=0)
	public Long getDayLimit(){
		return this.dayLimit;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  日限额
	 */
	public void setDayLimit(Long dayLimit){
		this.dayLimit = dayLimit;
		//this.dayLimitDouble = NumberUtils.longToDouble(dayLimit);
	}
	
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  月限额
	 */
	@Column(name ="MONTH_LIMIT",nullable=false,precision=17,scale=0)
	public Long getMonthLimit(){
		return this.monthLimit;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  月限额
	 */
	public void setMonthLimit(Long monthLimit){
		this.monthLimit = monthLimit;
		//this.monthLimitDouble = NumberUtils.longToDouble(monthLimit);
		
	}
	
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  关停状态
	 */
	@Column(name ="CLOSE_STATE",nullable=true,precision=3,scale=0)
	public java.lang.Integer getCloseState(){
		return this.closeState;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  关停状态
	 */
	public void setCloseState(java.lang.Integer closeState){
		this.closeState = closeState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上行地址
	 */
	@Column(name ="UP_URL",nullable=true,length=256)
	public java.lang.String getUpUrl(){
		return this.upUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上行地址
	 */
	public void setUpUrl(java.lang.String upUrl){
		this.upUrl = upUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  下行地址
	 */
	@Column(name ="DOWN_URL",nullable=true,length=256)
	public java.lang.String getDownUrl(){
		return this.downUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  下行地址
	 */
	public void setDownUrl(java.lang.String downUrl){
		this.downUrl = downUrl;
	}
    @Transient
	public Double getDayLimitDouble() {
		return dayLimitDouble;
	}

	public void setDayLimitDouble(Double dayLimitDouble) {
		this.dayLimitDouble = dayLimitDouble;
	}

	@Transient
	public Double getMonthLimitDouble() {
		return monthLimitDouble;
	}

	public void setMonthLimitDouble(Double monthLimitDouble) {
		this.monthLimitDouble = monthLimitDouble;
	}

	//@OneToMany(cascade=CascadeType.REMOVE, orphanRemoval=true)
	@OneToMany(mappedBy="channel", cascade=CascadeType.REMOVE,orphanRemoval=true)
	//@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE)
	//@JoinColumn(name="channel_id")
	public List<ChannelDayLimitEntity> getChanelDayLimits() {
		return chanelDayLimits;
	}

	public void setChanelDayLimits(List<ChannelDayLimitEntity> chanelDayLimits) {
		this.chanelDayLimits = chanelDayLimits;
	}

	//@OneToMany(cascade=CascadeType.REMOVE, orphanRemoval=true)
	@OneToMany(mappedBy="channel", cascade=CascadeType.REMOVE,orphanRemoval=true)
	//@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE)
	//@JoinColumn(name="channel_id")
	public List<ChannelMonthLimitEntity> getChanelMonthLimits() {
		return chanelMonthLimits;
	}

	public void setChanelMonthLimits(List<ChannelMonthLimitEntity> chanelMonthLimits) {
		this.chanelMonthLimits = chanelMonthLimits;
	}

//	@OneToMany(mappedBy="channel", cascade=CascadeType.REMOVE,orphanRemoval=true)
	//@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE)
	//@JoinColumn(name="channel_id")
//	public List<SmTaskEntity> getSmTasks() {
//		return smTasks;
//	}
//
//	public void setSmTasks(List<SmTaskEntity> smTasks) {
//		this.smTasks = smTasks;
//	}
	
	

//	@Override
//	public void onSave(Serializable id, Object[] state,
//			String[] propertyNames, Type[] types) {
//		this.updateLimitValue(propertyNames, state);
//		
//	}
//
//	@Override
//	public void onFlushDirty(Serializable id, Object[] currentState,
//			Object[] previousState, String[] propertyNames, Type[] types) {
//		this.updateLimitValue(propertyNames, currentState);
//		
//	}
//	
//	private void updateLimitValue(String[] propertyNames, Object[] state) {
//		for (int index = 0; index < propertyNames.length; index++) {
//			if (propertyNames[index].equals("dayLimit")) {
//				state[index] = NumberUtils.doubleToLong(this.dayLimitDouble);
//				continue;
//			} else if (propertyNames[index].equals("monthLimit")) {
//				state[index] = NumberUtils.doubleToLong(this.monthLimitDouble);
//				continue;
//			}
//		}
//	}

}
