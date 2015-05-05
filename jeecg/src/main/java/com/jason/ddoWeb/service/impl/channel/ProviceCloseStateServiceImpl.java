package com.jason.ddoWeb.service.impl.channel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.entity.channel.ProviceCloseStateEntity;
import com.jason.ddoWeb.entity.event.EventEntity;
import com.jason.ddoWeb.service.channel.ProviceCloseStateServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("proviceCloseStateService")
@Transactional
public class ProviceCloseStateServiceImpl extends CommonServiceImpl implements ProviceCloseStateServiceI {

	@Override
	public <T> void updateEntitie(T pojo) {
		ProviceCloseStateEntity entity = (ProviceCloseStateEntity)pojo;
		//创建事件
		EventEntity event = new EventEntity();
		event.setEventId("UpdateProviceCloseStateEvent");
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(entity.getId()).append(",closeState:")
				.append(entity.getCloseState());
		event.setParam(builder.toString());
		super.save(event);
		super.updateEntitie(pojo);
	}
	
}