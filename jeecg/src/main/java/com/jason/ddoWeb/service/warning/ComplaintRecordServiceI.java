package com.jason.ddoWeb.service.warning;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import com.jason.ddoWeb.entity.warning.ComplaintRecordEntity;

public interface ComplaintRecordServiceI extends CommonService{

	void saveList(List<ComplaintRecordEntity> list);
}
