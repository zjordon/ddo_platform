package com.jason.ddoWeb.service.impl.msg;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.msg.NeedUpChannelServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("needUpChannelService")
@Transactional
public class NeedUpChannelServiceImpl extends CommonServiceImpl implements NeedUpChannelServiceI {
	
}