package com.jason.ddoWeb.service.impl.statistics;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.statistics.SendResultRecordServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("sendResultRecordService")
@Transactional
public class SendResultRecordServiceImpl extends CommonServiceImpl implements SendResultRecordServiceI {
	
}