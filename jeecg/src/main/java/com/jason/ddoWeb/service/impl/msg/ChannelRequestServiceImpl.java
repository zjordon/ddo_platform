package com.jason.ddoWeb.service.impl.msg;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.msg.ChannelRequestServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("channelRequestService")
@Transactional
public class ChannelRequestServiceImpl extends CommonServiceImpl implements ChannelRequestServiceI {
	
}