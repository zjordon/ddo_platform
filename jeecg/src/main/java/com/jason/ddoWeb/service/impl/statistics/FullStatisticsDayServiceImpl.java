package com.jason.ddoWeb.service.impl.statistics;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.statistics.FullStatisticsDayServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("fullStatisticsDayService")
@Transactional
public class FullStatisticsDayServiceImpl extends CommonServiceImpl implements FullStatisticsDayServiceI {
	
}