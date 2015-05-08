package com.jason.ddoWeb.service.impl.warning;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.warning.CComplaintMonthViewServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("cComplaintMonthViewService")
@Transactional
public class CComplaintMonthViewServiceImpl extends CommonServiceImpl implements CComplaintMonthViewServiceI {
	
}