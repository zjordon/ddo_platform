package com.jason.ddoWeb.service.impl.smtask;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.entity.smtask.SmMsisdnListEntity;
import com.jason.ddoWeb.service.smtask.SmMsisdnListServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("smMsisdnListService")
@Transactional
public class SmMsisdnListServiceImpl extends CommonServiceImpl implements SmMsisdnListServiceI {

	@Override
	public void deleteByTaskId(String taskId) {
		List<SmMsisdnListEntity> list = super.findByProperty(SmMsisdnListEntity.class, "smTaskId", taskId);
		for (SmMsisdnListEntity entity : list) {
			super.delete(entity);
		}
	}
	
}