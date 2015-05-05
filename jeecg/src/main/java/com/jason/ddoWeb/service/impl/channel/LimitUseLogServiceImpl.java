package com.jason.ddoWeb.service.impl.channel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.channel.LimitUseLogServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("limitUseLogService")
@Transactional
public class LimitUseLogServiceImpl extends CommonServiceImpl implements LimitUseLogServiceI {
	
}