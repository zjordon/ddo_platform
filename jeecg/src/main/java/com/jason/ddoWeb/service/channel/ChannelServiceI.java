package com.jason.ddoWeb.service.channel;

import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;

public interface ChannelServiceI extends CommonService{

	Map<String, String> getChannelNameMap();
}
