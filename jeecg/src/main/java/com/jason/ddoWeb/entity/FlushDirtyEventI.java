/**
 * 
 */
package com.jason.ddoWeb.entity;

import java.io.Serializable;

import org.hibernate.type.Type;

/**
 * 需要监听hibernate的flushDirty事件（主要是更新操作时发生）并做出自应处理的实体类都需要实现该接口
 * @author jasonzhang
 *
 */
public interface FlushDirtyEventI {

	void onFlushDirty(Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types);
}
