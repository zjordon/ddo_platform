package com.jason.ddoWeb.service.impl.channel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.channel.ChannelDayLimitServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("channelDayLimitService")
@Transactional
public class ChannelDayLimitServiceImpl extends CommonServiceImpl implements ChannelDayLimitServiceI {
	
}