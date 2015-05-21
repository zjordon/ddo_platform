package com.jason.ddoWeb.service.impl.channel;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.entity.channel.ChannelMonthLimitEntity;
import com.jason.ddoWeb.service.channel.ChannelMonthLimitServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("channelMonthLimitService")
@Transactional
public class ChannelMonthLimitServiceImpl extends CommonServiceImpl implements ChannelMonthLimitServiceI {

	@Override
	public void deleteByChannelId(String channelId) {
		List<ChannelMonthLimitEntity> list = super.findByProperty(ChannelMonthLimitEntity.class, "channelId", channelId);
		for (ChannelMonthLimitEntity entity : list) {
			super.delete(entity);
		}
		
	}
	
}