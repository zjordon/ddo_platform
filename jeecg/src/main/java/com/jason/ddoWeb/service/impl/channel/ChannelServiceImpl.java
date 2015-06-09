package com.jason.ddoWeb.service.impl.channel;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.entity.channel.ChannelEntity;
import com.jason.ddoWeb.entity.event.EventEntity;
import com.jason.ddoWeb.service.channel.ChannelBusinessServiceI;
import com.jason.ddoWeb.service.channel.ChannelDayLimitServiceI;
import com.jason.ddoWeb.service.channel.ChannelMonthLimitServiceI;
import com.jason.ddoWeb.service.channel.ChannelServiceI;
import com.jason.ddoWeb.service.channel.ChannelUserServiceI;
import com.jason.ddoWeb.service.smtask.SmTaskServiceI;
import com.jason.ddoWeb.util.NumberUtils;
import com.jason.ddoWeb.util.StringUtils;
import com.jason.ddoWeb.util.UUIDGenerator;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DynamicDBUtil;

@Service("channelService")
@Transactional
public class ChannelServiceImpl extends CommonServiceImpl implements ChannelServiceI {

	private static final Logger logger = Logger.getLogger(ChannelServiceImpl.class);
	
	private final static String GET_CHANNEL_INFO = "select state, close_state, up_url, down_url, month_limit, day_limit, post_url from ddo_channel where id = ?";
	private final static String INSERT_EVENT = "insert into sm_event(id, event_id, create_date, param) values(?, ?, ?, ?)";
	
	@Autowired
	private ChannelBusinessServiceI channelBusinessService;
	@Autowired
	private ChannelUserServiceI channelUserService;
	@Autowired
	private SmTaskServiceI smTaskService;
//	@Autowired
//	private ChannelDayLimitServiceI channelDayLimitService;
//	@Autowired
//	private ChannelMonthLimitServiceI channelMonthLimitService;

	@Override
	public <T> Serializable save(T entity) {
		String id = (String)super.save(entity);
		//自动生成渠道计费点
		this.channelBusinessService.saveChannelBusinessesByChannel(id, ((ChannelEntity)entity).getNo());
		//产生新增渠道事件
		EventEntity event = new EventEntity();
		event.setEventId("AddChannelEvent");
		String param = this.buildAllParam((ChannelEntity)entity);
		event.setParam(param);
		super.save(event);
		this.createEventToSm(event);
		return id;
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		ChannelEntity channel = (ChannelEntity)entity;
		Map map = super.findOneForJdbc(GET_CHANNEL_INFO, (channel.getId()));
		//获取更新前的数据
//		ChannelEntity persistent  = super.getEntity(ChannelEntity.class, channel.getId());
		
		if (!map.isEmpty()) {
			//检测状态或关停状态是否改变
			Integer oldState = (Integer)map.get("state");
			Integer oldCloseState= (Integer)map.get("close_state");
			if (oldState.intValue() != channel.getState().intValue() || oldCloseState.intValue() != channel.getCloseState().intValue()) {
				//创建更新渠道信息事件
				EventEntity event = new EventEntity();
				event.setEventId("UpdateChannelInfoEvent");
				StringBuilder builder = new StringBuilder();
				builder.append("id:").append(channel.getId()).append(",state:").append(channel.getState());
				builder.append(",closeState:").append(channel.getCloseState());
				event.setParam(builder.toString());
				super.save(event);
			}
			String upUrl = (String)map.get("up_url");
			String downUrl = (String)map.get("down_url");
			//检测渠道上下行地址是否改变
			if (!StringUtils.isEquals(upUrl, channel.getUpUrl()) || !StringUtils.isEquals(downUrl, channel.getDownUrl())) {
				//创建更新上下行地址事件
				EventEntity event = new EventEntity();
				event.setEventId("UpdateChannelUrlEvent");
				StringBuilder builder = new StringBuilder();
				builder.append("id:").append(channel.getId()).append(",upUrl:").append(channel.getUpUrl());
				builder.append(",downUrl:").append(channel.getDownUrl());
				event.setParam(builder.toString());
				super.save(event);
			}
			Long oldMonthLimit = (Long)map.get("month_limit");
			Long newMonthLimit = NumberUtils.doubleToLong(channel.getMonthLimitDouble());
			//检测渠道月限额是否改变
			if (oldMonthLimit.longValue() != newMonthLimit.longValue()) {
				//创建更新渠道日限额事件
				EventEntity event = new EventEntity();
				event.setEventId("UpdateMonthLimitEvent");
				StringBuilder builder = new StringBuilder();
				builder.append("channelId:").append(channel.getId()).append(",oldValue:").append(oldMonthLimit.longValue());
				builder.append(",newValue:").append(newMonthLimit.longValue());
				builder.append(",updateMonth:").append(DateFormatUtils.format(new Date(), "yyyyMMdd"));
				event.setParam(builder.toString());
				super.save(event);
			}
			Long oldDayLimit = (Long)map.get("day_limit");
			Long newDayLimit = NumberUtils.doubleToLong(channel.getDayLimitDouble());
			//检测渠道日限额是否改变
			if (oldDayLimit.longValue() != newDayLimit.longValue()) {
				//创建更新渠道月限额事件
				EventEntity event = new EventEntity();
				event.setEventId("UpdateDayLimitEvent");
				StringBuilder builder = new StringBuilder();
				builder.append("channelId:").append(channel.getId()).append(",oldValue:").append(oldDayLimit.longValue());
				builder.append(",newValue:").append(newDayLimit.longValue());
				builder.append(",updateDate:").append(DateFormatUtils.format(new Date(), "yyyyMM"));
				event.setParam(builder.toString());
				super.save(event);
			}
			//检测posturl是否改变
			String oldPostUrl = (String) map.get("post_url");
			String newPostUrl = channel.getPostUrl();
			if (!StringUtils.isEquals(oldPostUrl, newPostUrl)) {
				//创建更新postUrl地址事件(只针对短信平台)
				EventEntity event = new EventEntity();
				//event.setId(id);
				event.setId((new UUIDGenerator()).generate());
				event.setEventId("UpdatChannelPostUrlEvent");
				StringBuilder builder = new StringBuilder();
				builder.append("id:").append(channel.getId()).append(",postUrl:").append(channel.getPostUrl());
				event.setParam(builder.toString());
				event.setCreateDate(new Date());
				this.createEventToSm(event);
			}
		}
		//此处必须用merge来更新实体，不能用super.saveOrUpdate,否则会报org.hibernate.NonUniqueObjectException异常
//		super.getSession().merge(channel);
//		super.saveOrUpdate(entity);
		super.updateEntitie(entity);
	}
	
	private String buildAllParam(ChannelEntity channel) {
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(channel.getId()).append(",upUrl:").append(channel.getUpUrl()).append(",downUrl:").append(channel.getDownUrl());
		builder.append(",state:").append(channel.getState()).append(",closeState:").append(channel.getCloseState());
		builder.append(",name:").append(channel.getName()).append(",no:").append(channel.getNo()).append(",dayLimit:").append(channel.getDayLimit());
		builder.append(",monthLimit:").append(channel.getMonthLimit()).append(",postUrl:").append(channel.getPostUrl());
		return builder.toString();
	}

	@Override
	public Map<String, String> getChannelNameMap() {
		Map<String, String> channelNameMap = new HashMap<String, String>();
		List<ChannelEntity> channelList = super.getList(ChannelEntity.class);
		for (ChannelEntity entity : channelList) {
			channelNameMap.put(entity.getId(), entity.getName());
		}
		return channelNameMap;
	}
	
	@Override
	public <T> void delete(T entity) {
		String channelId = ((ChannelEntity)entity).getId();
		//删除对应的渠道用户
		this.channelUserService.deleteByChannelId(channelId);
		//删除对应的渠道业务
		this.channelBusinessService.deleteByChannelId(channelId);
		//删除对应的短信任务
		this.smTaskService.deleteByChannelId(channelId);
		//其它删除使用hibernate的一对多关系进行关联删除
		//删除对应的日限额和月限额
//		this.channelDayLimitService.deleteByChannelId(channelId);
//		this.channelMonthLimitService.deleteByChannelId(channelId);
		super.delete(entity);
		EventEntity event = new EventEntity();
		event.setEventId("DeleteChannelEvent");
		StringBuilder builder = new StringBuilder("id:");
		builder.append(channelId);
		event.setParam(builder.toString());
		super.save(event);
		this.createEventToSm(event);
	}
	
	private void createEventToSm(EventEntity event) {
		DynamicDBUtil.update("SM_DB", INSERT_EVENT, event.getId(), event.getEventId(), event.getCreateDate(), event.getParam());
	}
	
}