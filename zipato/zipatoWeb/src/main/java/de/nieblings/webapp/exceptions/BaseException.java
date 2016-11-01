/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: BaseException.java 26078 2011-03-01 10:17:50Z gerrit $
 */
package de.nieblings.webapp.exceptions;

/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 26078 $
 */
public abstract class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public BaseException(final String message) {
		super(message);
	}

	public BaseException(final Throwable cause) {
		super(cause);
	}

	public Throwable getRootCause() {
		return getRootCause(this);
	}

	private Throwable getRootCause(final Throwable throwable) {
		final Throwable cause = throwable.getCause();
		if (cause == null) {
			return throwable;
		}
		return cause;
	}

	public String getRootCauseMessage() {
		return getRootCause().getMessage();
	}

}
