/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: BusinessServiceException.java 80644 2015-06-01 14:49:25Z oliver $
 */
package de.nieblings.webapp.core;

import de.nieblings.webapp.exceptions.Localizable;



/**
 * 
 * @author $Author: oliver $
 * @version $Revision: 80644 $
 */
public class BusinessServiceException extends Exception implements Localizable {

	private static final long serialVersionUID = 1L;

	private Object[] args;

	public BusinessServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public BusinessServiceException(final String message) {
		super(message);
	}

	public BusinessServiceException(final String key, final Object... args) {
		super(key);
		this.args = args;
	}

	public BusinessServiceException(final Throwable cause) {
		super(key(cause), cause); // NOPMD
	}

	private final static String key(final Throwable cause) {
		String key = cause.getMessage();
		if (cause instanceof Localizable) {
			key = ((Localizable) cause).getMessageKey();
		}
		return key;
	}

	public Throwable getRootCause() {
		return getRootCause(this);
	}

	private Throwable getRootCause(final Throwable throwable) {
		final Throwable cause = throwable.getCause();
		if (cause == null) {
			return throwable;
		}
		return getRootCause(cause);
	}

	public String getRootCauseMessage() {
		final Throwable rootCause = getRootCause();
		if (rootCause != null) {
			return rootCause.getMessage();
		}
		return getMessage();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessageKey() {
		return getMessage();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] getArgs() {
		final Throwable cause = getCause();
		if (cause instanceof Localizable) {
			return ((Localizable) cause).getArgs();
		}
		return args;
	}

}
