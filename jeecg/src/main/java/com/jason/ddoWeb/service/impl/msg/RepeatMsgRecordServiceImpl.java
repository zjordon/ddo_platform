package com.jason.ddoWeb.service.impl.msg;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.msg.RepeatMsgRecordServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("repeatMsgRecordService")
@Transactional
public class RepeatMsgRecordServiceImpl extends CommonServiceImpl implements RepeatMsgRecordServiceI {
	
}