/**
 * 
 */
package com.jason.ddoWeb.entity;

import java.io.Serializable;

import org.hibernate.type.Type;

/**
 *  需要监听hibernate的sav事件并做出自应处理的实体类都需要实现该接口
 * @author jasonzhang
 *
 */
public interface SaveEventI {

	void onSave(Serializable id, Object[] state,
			String[] propertyNames, Type[] types);
}
