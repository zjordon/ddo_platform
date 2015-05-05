package com.jason.ddoWeb.service.impl.warning;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.warning.ChannelComplaintServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("channelComplaintService")
@Transactional
public class ChannelComplaintServiceImpl extends CommonServiceImpl implements ChannelComplaintServiceI {
	
}