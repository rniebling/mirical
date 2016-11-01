/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: Sperre.java 80120 2015-05-20 10:51:56Z norman $
 */
package de.nieblings.webapp.core.lock;

import org.apache.commons.lang.StringUtils;

import de.nieblings.webapp.core.EntityBean;
import de.nieblings.webapp.core.User;

/**
 * 
 * @author $Author: norman $
 * @version $Revision: 80120 $
 */
public class Sperre extends EntityBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7086996433244758695L;

	private Long datensatzId;

	private String entity;

	private String sessionId;

	private User benutzer;

	/**
	 * @return the datensatzId
	 */
	public Long getDatensatzId() {
		return datensatzId;
	}

	/**
	 * @param datensatzId the datensatzId to set
	 */
	public void setDatensatzId(final Long datensatzId) {
		this.datensatzId = datensatzId;
	}

	/**
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}

	public String getShortName() {
		return StringUtils.substringAfterLast(entity, ".");
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(final String entity) {
		this.entity = entity;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(final String sessionId) {
		this.sessionId = sessionId;
	}

	public User getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(final User benutzer) {
		this.benutzer = benutzer;
	}

}
