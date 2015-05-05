package com.jason.ddoWeb.service.impl.statistics;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.statistics.ChannelStatisticsDayServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("channelStatisticsDayService")
@Transactional
public class ChannelStatisticsDayServiceImpl extends CommonServiceImpl implements ChannelStatisticsDayServiceI {
	
}