package com.jason.ddoWeb.service.impl.warning;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.warning.FullComplaintDayServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("fullComplaintDayService")
@Transactional
public class FullComplaintDayServiceImpl extends CommonServiceImpl implements FullComplaintDayServiceI {
	
}