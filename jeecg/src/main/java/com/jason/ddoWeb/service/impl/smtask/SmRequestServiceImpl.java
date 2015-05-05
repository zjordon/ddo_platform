package com.jason.ddoWeb.service.impl.smtask;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.smtask.SmRequestServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("smRequestService")
@Transactional
public class SmRequestServiceImpl extends CommonServiceImpl implements SmRequestServiceI {
	
}