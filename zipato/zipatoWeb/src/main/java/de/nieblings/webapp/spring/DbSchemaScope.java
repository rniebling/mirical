/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: DbSchemaScope.java 26083 2011-03-01 10:27:24Z gerrit $
 */
package de.nieblings.webapp.spring;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;



/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 26083 $
 */
public class DbSchemaScope implements Scope {

	private final Map<String, Map<String, Object>> beans = new Hashtable<String, Map<String, Object>>();

	private final Map<String, Map<String, Runnable>> destructors = new Hashtable<String, Map<String, Runnable>>();

	/**
	 * {@inheritDoc}
	 */
	public Object get(final String name, final ObjectFactory<?> objectFactory) {
		final String conversationId = getConversationId();
		if (conversationId == null) {
			return null;
		}

		Object bean = getBean(name, conversationId);
		if (bean == null) {
			bean = objectFactory.getObject();
			putBean(name, conversationId, bean);
		}
		return bean;
	}

	private Object putBean(final String name, final String conversationId, final Object bean) {
		return getScopedMap(conversationId).put(name, bean);
	}

	private Map<String, Object> getScopedMap(final String conversationId) {
		Map<String, Object> scopedMap = beans.get(conversationId);
		if (scopedMap == null) {
			scopedMap = new Hashtable<String, Object>();
			beans.put(conversationId, scopedMap);
		}
		return scopedMap;
	}

	private Map<String, Runnable> getScopedDestructorMap(final String conversationId) {
		Map<String, Runnable> scopedMap = destructors.get(conversationId);
		if (scopedMap == null) {
			scopedMap = new Hashtable<String, Runnable>();
			destructors.put(conversationId, scopedMap);
		}
		return scopedMap;
	}

	private Object getBean(final String name, final String conversationId) {
		return getScopedMap(conversationId).get(name);
	}

	private Object removeBean(final String name, final String conversationId) {
		return getScopedMap(conversationId).remove(name);
	}

	private Runnable removeDestructor(final String name, final String conversationId) {
		return getScopedDestructorMap(conversationId).remove(name);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getConversationId() {
		return "";
	}

	/**
	 * {@inheritDoc}
	 */
	public void registerDestructionCallback(final String name, final Runnable callback) {
		final String conversationId = getConversationId();
		if (conversationId == null) {
			return;
		}
		getScopedDestructorMap(conversationId).put(name, callback);
	}

	/**
	 * {@inheritDoc}
	 */
	public Object remove(final String name) {
		final String conversationId = getConversationId();
		if (conversationId == null) {
			return null;
		}
		final Runnable destructor = removeDestructor(name, conversationId);
		if (destructor != null) {
			destructor.run();
		}
		return removeBean(name, conversationId);
	}

	/**
	 * {@inheritDoc}
	 */
	public Object resolveContextualObject(final String key) {
		return null;
	}


}
