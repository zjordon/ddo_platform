package com.jason.ddoWeb.service.impl.msg;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.msg.RepeatUpRecordServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("repeatUpRecordService")
@Transactional
public class RepeatUpRecordServiceImpl extends CommonServiceImpl implements RepeatUpRecordServiceI {
	
}