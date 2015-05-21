package com.jason.ddoWeb.service.impl.smtask;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.entity.smtask.SmRequestEntity;
import com.jason.ddoWeb.service.smtask.SmRequestServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("smRequestService")
@Transactional
public class SmRequestServiceImpl extends CommonServiceImpl implements SmRequestServiceI {

	@Override
	public void deleteByTaskId(String taskId) {
		List<SmRequestEntity> list = super.findByProperty(SmRequestEntity.class, "smTaskId", taskId);
		for (SmRequestEntity entity : list) {
			super.delete(entity);
		}
	}
	
}