package com.jason.ddoWeb.service.impl.statistics;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.statistics.ChannelStatisticsMonthServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("channelStatisticsMonthService")
@Transactional
public class ChannelStatisticsMonthServiceImpl extends CommonServiceImpl implements ChannelStatisticsMonthServiceI {
	
}