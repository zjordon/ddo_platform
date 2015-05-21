package com.jason.ddoWeb.service.channel;

import org.jeecgframework.core.common.service.CommonService;

public interface ChannelDayLimitServiceI extends CommonService{

	void deleteByChannelId(String channelId);
}
