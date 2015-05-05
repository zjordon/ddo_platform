package com.jason.ddoWeb.service.impl.channel;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.entity.channel.ChannelUserEntity;
import com.jason.ddoWeb.entity.event.EventEntity;
import com.jason.ddoWeb.service.channel.ChannelUserServiceI;

import org.apache.commons.codec.digest.DigestUtils;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.StringUtil;

@Service("channelUserService")
@Transactional
public class ChannelUserServiceImpl extends CommonServiceImpl implements
		ChannelUserServiceI {
	private final static String GET_CHANNEL_USER = "from ChannelUserEntity obj where obj.username = ? and obj.password = ? and obj.state = 1";

	@Override
	public <T> Serializable save(T entity) {
		super.save(entity);
		// 创建新增渠道用户事件
		ChannelUserEntity channelUser = (ChannelUserEntity) entity;
		EventEntity event = new EventEntity();
		event.setEventId("AddChannelUserEvent");
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(channelUser.getId()).append(",username:")
				.append(channelUser.getPassword());
		builder.append(",password:").append(channelUser.getPassword())
				.append(",msisdn:").append(channelUser.getMsisdn());
		builder.append(",state:").append(channelUser.getState())
				.append(",channelId:").append(channelUser.getChannelId());
		event.setParam(builder.toString());
		super.save(event);
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
		EventEntity event = new EventEntity();
		event.setEventId("DeleteChannelUserEvent");
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(((ChannelUserEntity) entity).getId());
		event.setParam(builder.toString());
		super.save(event);
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

}