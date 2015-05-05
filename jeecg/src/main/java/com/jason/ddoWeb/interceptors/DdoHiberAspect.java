package com.jason.ddoWeb.interceptors;

import java.io.Serializable;

import org.hibernate.type.Type;
import org.jeecgframework.core.aop.HiberAspect;
import org.springframework.stereotype.Component;

import com.jason.ddoWeb.entity.FlushDirtyEventI;
import com.jason.ddoWeb.entity.LoadEventI;
import com.jason.ddoWeb.entity.SaveEventI;
import com.jason.ddoWeb.entity.SaveOrUpdateEventI;
import com.jason.ddoWeb.entity.channel.ChannelEntity;
/**
 * 实现对自定义onLoad方法的调用
 * @author jasonzhang
 *
 */
@Component
public class DdoHiberAspect extends HiberAspect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if (entity instanceof SaveEventI) {
			((SaveEventI)entity).onSave(id, state, propertyNames, types);
		}
		return super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if (entity instanceof FlushDirtyEventI) {
			((FlushDirtyEventI)entity).onFlushDirty(id, currentState, previousState, propertyNames, types);
		}
		return super.onFlushDirty(entity, id, currentState, previousState,
				propertyNames, types);
	}

	

}
