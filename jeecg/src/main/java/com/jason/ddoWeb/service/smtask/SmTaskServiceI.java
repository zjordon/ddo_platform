package com.jason.ddoWeb.service.smtask;

import org.jeecgframework.core.common.service.CommonService;

import com.jason.ddoWeb.entity.smtask.SmTaskEntity;

import java.io.InputStream;

public interface SmTaskServiceI extends CommonService{

	String save(SmTaskEntity smTask, String msisdnContent);
	
	String save(SmTaskEntity smTask, InputStream in);
	
	String saveFile(String taskId, InputStream in);
}
