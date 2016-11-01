/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: SunriseScopeMetadataResolver.java 89060 2015-11-23 11:55:23Z oliver $
 */
package de.nieblings.webapp.spring;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;

/**
 * 
 * @author $Author: oliver $
 * @version $Revision: 89060 $
 */
public class SunriseScopeMetadataResolver extends AnnotationScopeMetadataResolver implements ScopeMetadataResolver {

	@Override
	public ScopeMetadata resolveScopeMetadata(final BeanDefinition definition) {

		final String scopeName = resolveScopeName(definition);
		if (StringUtils.isNotBlank(scopeName)) {
			return scopeMetadata(scopeName);
		}

		Class<?> clazz;
		try {
			String beanClassName = definition.getBeanClassName();
			clazz = Class.forName(beanClassName);
		} catch (ClassNotFoundException e) {
			return super.resolveScopeMetadata(definition);
		} catch(Throwable t) {
			System.out.println(t);
			throw t;
		}
		if (ViewController.class.isAssignableFrom(clazz)) {
			return scopeMetadata(SunriseScopes.CONVERSATION);
		}
/*		if (DataModel.class.isAssignableFrom(clazz)) {
			return scopeMetadata(BeanDefinition.SCOPE_PROTOTYPE);
		}
		if (SelectItemGenerator.class.isAssignableFrom(clazz)) {
			return scopeMetadata(SunriseScopes.CONVERSATION);
		}*/
		return super.resolveScopeMetadata(definition);
	}

	private ScopeMetadata scopeMetadata(final String scopeName) {
		final ScopeMetadata metadata = new ScopeMetadata();
		metadata.setScopeName(scopeName);
		return metadata;
	}

	private String resolveScopeName(final BeanDefinition definition) {
		if (definition instanceof AnnotatedBeanDefinition) {
			final AnnotatedBeanDefinition annDef = (AnnotatedBeanDefinition) definition;
			final Map<String, Object> attributes = annDef.getMetadata().getAnnotationAttributes(Scope.class.getName());
			if (attributes != null) {
				return (String) attributes.get("value");
			}
		}
		return null;
	}

}
