package com.jason.ddoWeb.service.impl.warning;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.warning.FullComplaintServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("fullComplaintService")
@Transactional
public class FullComplaintServiceImpl extends CommonServiceImpl implements FullComplaintServiceI {
	
}