package com.jason.ddoWeb.service.impl.channel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.channel.ChannelMonthLimitServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("channelMonthLimitService")
@Transactional
public class ChannelMonthLimitServiceImpl extends CommonServiceImpl implements ChannelMonthLimitServiceI {
	
}