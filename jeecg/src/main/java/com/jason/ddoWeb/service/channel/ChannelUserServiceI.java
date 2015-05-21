package com.jason.ddoWeb.service.channel;

import org.jeecgframework.core.common.service.CommonService;

import com.jason.ddoWeb.entity.channel.ChannelUserEntity;

public interface ChannelUserServiceI extends CommonService{

	public void updatePassword(String id, String password);
	
	public ChannelUserEntity checkUserExits(ChannelUserEntity channelUser);
	
	public void deleteByChannelId(String channelId);
}
