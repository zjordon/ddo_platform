package com.jason.ddoWeb.service.impl.statistics;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.statistics.SendRecordServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("sendRecordService")
@Transactional
public class SendRecordServiceImpl extends CommonServiceImpl implements SendRecordServiceI {
	
}