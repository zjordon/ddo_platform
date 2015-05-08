package com.jason.ddoWeb.service.impl.warning;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.warning.FullComplaintMonthServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("fullComplaintMonthService")
@Transactional
public class FullComplaintMonthServiceImpl extends CommonServiceImpl implements FullComplaintMonthServiceI {
	
}