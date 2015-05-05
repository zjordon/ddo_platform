package com.jason.ddoWeb.service.impl.msg;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.msg.BillReportServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("billReportService")
@Transactional
public class BillReportServiceImpl extends CommonServiceImpl implements BillReportServiceI {
	
}