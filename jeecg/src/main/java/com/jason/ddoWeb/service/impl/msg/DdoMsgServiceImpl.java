package com.jason.ddoWeb.service.impl.msg;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.msg.DdoMsgServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("ddoMsgService")
@Transactional
public class DdoMsgServiceImpl extends CommonServiceImpl implements DdoMsgServiceI {
	
}