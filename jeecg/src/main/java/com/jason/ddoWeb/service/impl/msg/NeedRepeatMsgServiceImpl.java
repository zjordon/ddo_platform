package com.jason.ddoWeb.service.impl.msg;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.msg.NeedRepeatMsgServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("needRepeatMsgService")
@Transactional
public class NeedRepeatMsgServiceImpl extends CommonServiceImpl implements NeedRepeatMsgServiceI {
	
}