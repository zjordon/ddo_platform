/**
 * 
 */
package com.jason.ddoWeb.common.hibernate;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.PersistenceContext;
import org.hibernate.persister.entity.EntityPersister;

/**
 * @author jasonzhang
 *
 */
public final class HibernateUtil {

	/**
	 * 获取实体的某个字段被更新前的值
	 * @param session hibernate的会话
	 * @param entityName 实体的class名全称
	 * @param propertyName 需要获取的属性的名称
	 * @return
	 */
	public final static Object getOldValue(Session session, String entityName, String propertyName) {
		org.hibernate.internal.SessionImpl actualSession = (org.hibernate.internal.SessionImpl)session;
		final PersistenceContext persistenceContext = actualSession.getPersistenceContext();
		Object oldValue = null;
		for ( Map.Entry<Object,EntityEntry> me : persistenceContext.reentrantSafeEntityEntries() ) {
			EntityEntry entry = (EntityEntry) me.getValue();
			if (entry.getEntityName().equals(entityName)) {
				Object[] loadedStates = entry.getLoadedState();
				EntityPersister persister = entry.getPersister();
				String[] propertyNames = persister.getPropertyNames();
				for (int i=0; i<propertyNames.length; i++) {
					if (propertyNames[i].equals(propertyName)) {
						oldValue = loadedStates[i];
					}
				}
			}
		}
		return oldValue;
	}
}
