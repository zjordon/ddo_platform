package com.jason.ddoWeb.service.impl.event;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.event.EventServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("eventService")
@Transactional
public class EventServiceImpl extends CommonServiceImpl implements EventServiceI {
	
}