package com.jason.ddoWeb.service.impl.statistics;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.statistics.FullStatisticsMonthServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("fullStatisticsMonthService")
@Transactional
public class FullStatisticsMonthServiceImpl extends CommonServiceImpl implements FullStatisticsMonthServiceI {
	
}