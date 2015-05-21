package com.jason.ddoWeb.service.impl.channel;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.entity.channel.ChannelDayLimitEntity;
import com.jason.ddoWeb.service.channel.ChannelDayLimitServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("channelDayLimitService")
@Transactional
public class ChannelDayLimitServiceImpl extends CommonServiceImpl implements ChannelDayLimitServiceI {

	@Override
	public void deleteByChannelId(String channelId) {
		List<ChannelDayLimitEntity> list = super.findByProperty(ChannelDayLimitEntity.class, "channelId", channelId);
		for (ChannelDayLimitEntity entity : list) {
			super.delete(entity);
		}
		
	}
	
}