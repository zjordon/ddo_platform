package com.jason.ddoWeb.service.impl.channel;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.entity.channel.ChannelUserEntity;
import com.jason.ddoWeb.entity.event.EventEntity;
import com.jason.ddoWeb.service.channel.ChannelUserServiceI;
import com.jason.ddoWeb.util.UUIDGenerator;

import org.apache.commons.codec.digest.DigestUtils;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.jeecgframework.core.util.StringUtil;

@Service("channelUserService")
@Transactional
public class ChannelUserServiceImpl extends CommonServiceImpl implements
		ChannelUserServiceI {
	private final static String GET_CHANNEL_USER = "from ChannelUserEntity obj where obj.username = ? and obj.password = ? and obj.state = 1";
	private final static String INSERT_EVENT = "insert into sm_event(id, event_id, create_date, param) values(?, ?, ?, ?)";

	@Override
	public <T> Serializable save(T entity) {
		super.save(entity);
		// 创建新增渠道用户事件
		ChannelUserEntity channelUser = (ChannelUserEntity) entity;
		EventEntity event = new EventEntity();
		event.setEventId("AddChannelUserEvent");
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(channelUser.getId()).append(",username:")
				.append(channelUser.getUsername());
		builder.append(",password:").append(channelUser.getPassword())
				.append(",msisdn:").append(channelUser.getMsisdn());
		builder.append(",state:").append(channelUser.getState())
				.append(",channelId:").append(channelUser.getChannelId());
		event.setParam(builder.toString());
		super.save(event);
		//创建到短信平台的事件
		this.createEventToSm(event);
		return channelUser.getId();
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		super.updateEntitie(entity);
		ChannelUserEntity channelUser = (ChannelUserEntity) entity;

		EventEntity event = new EventEntity();
		event.setEventId("UpdateChannelUserStateEvent");
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(((ChannelUserEntity) entity).getId())
				.append(",state:").append(channelUser.getState());
		event.setParam(builder.toString());
		super.save(event);
	}

	@Override
	public <T> void delete(T entity) {
		// 创建删除渠道用户事件
		this.createDeleteEvent((ChannelUserEntity)entity);
		super.delete(entity);
	}

	@Override
	public void updatePassword(String id, String password) {
		if (StringUtil.isNotEmpty(id)) {
			ChannelUserEntity channelUser = super.getEntity(
					ChannelUserEntity.class, id);
			if (channelUser != null) {
				channelUser.setPassword(DigestUtils.md5Hex(password));
				super.updateEntitie(channelUser);
				// 创建更新渠道用户密码事件
				EventEntity event = new EventEntity();
				event.setEventId("UpdateCUPasswordEvent");
				StringBuilder builder = new StringBuilder();
				builder.append("id:").append(id).append(",password:")
						.append(password);
				event.setParam(builder.toString());
				super.save(event);
				//创建到短信平台的事件
				this.createEventToSm(event);
			}
		}
	}

	@Override
	public ChannelUserEntity checkUserExits(ChannelUserEntity channelUser) {
		String password = DigestUtils.md5Hex(channelUser.getPassword());
		List<ChannelUserEntity> userList = super.findHql(GET_CHANNEL_USER, channelUser.getUsername(), password);
		if (!userList.isEmpty()) {
			return userList.get(0);
		}
		return null;
	}
	
	private void createEventToSm(EventEntity event) {
		DynamicDBUtil.update("SM_DB", INSERT_EVENT, event.getId(), event.getEventId(), event.getCreateDate(), event.getParam());
	}

	@Override
	public void deleteByChannelId(String channelId) {
		List<ChannelUserEntity> list = super.findByProperty(ChannelUserEntity.class, "channelId", channelId);
		for (ChannelUserEntity entity : list) {
			super.delete(entity);
			this.createDeleteEvent(entity);
		}
		
	}
	
	private void createDeleteEvent(ChannelUserEntity entity) {
		EventEntity event = new EventEntity();
		event.setEventId("DeleteChannelUserEvent");
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(entity.getId());
		event.setParam(builder.toString());
		super.save(event);
		//创建到短信平台的事件
		this.createEventToSm(event);
	}

}