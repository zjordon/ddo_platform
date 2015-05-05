package com.jason.ddoWeb.service.impl.msg;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.msg.NeedRepeatReportServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("needRepeatReportService")
@Transactional
public class NeedRepeatReportServiceImpl extends CommonServiceImpl implements NeedRepeatReportServiceI {
	
}