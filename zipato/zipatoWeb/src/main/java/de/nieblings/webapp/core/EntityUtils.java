/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: $
 */
package de.nieblings.webapp.core;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 
 * @author $Author: $
 * @version $Revision: $
 */
public class EntityUtils {

	private static final Log LOG = LogFactory.getLog(EntityUtils.class);
	
	public static void substringToMaxLength(final Object entity) {
		if (entity == null){
			return;
		}
		Object tmpEntity = entity;
		
	/*	if (tmpEntity instanceof HibernateProxy) {
			tmpEntity = ((HibernateProxy) tmpEntity).getHibernateLazyInitializer().getImplementation();
		}
		*/
		Field[] declaredFields = entity.getClass().getDeclaredFields();
		if (declaredFields == null) {
			return;
		}
		
		for (Field field : declaredFields) {
			if (!String.class.equals(field.getType())) {
				continue;
			}
			/*
			javax.persistence.Column col = field.getAnnotation(javax.persistence.Column.class);
			if (col == null) {
				continue;
			}
			*/
			field.setAccessible(true);
			String str = null;
			try {
				str = (String) field.get(tmpEntity);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				LOG.error("Fehler beim Laden des Feldwertes.", e);
				continue;
			}
			if (str == null) {
				continue;
			}
			
			int maxlength = 0;
	//		int maxlength = col.length();
			int currentlength = str.length();
			if (currentlength > maxlength) {
				String substring = str.substring(0, maxlength);
				try {
					field.set(tmpEntity, substring);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					LOG.error("Fehler beim Setzen des Feldwertes.",e);
					continue;
				}
			}

		}
	}

	public static boolean isValid(UserGroup userGroup) {
	//TODO RN
		return true;
	}
}
