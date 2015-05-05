package com.jason.ddoWeb.service.impl.statistics;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.service.statistics.BillResultRecordServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("billResultRecordService")
@Transactional
public class BillResultRecordServiceImpl extends CommonServiceImpl implements BillResultRecordServiceI {
	
}