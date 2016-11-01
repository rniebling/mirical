/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: LocalizableException.java 48152 2013-04-05 06:58:44Z gerrit $
 */
package de.nieblings.webapp.exceptions;

/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 48152 $
 */
public abstract class LocalizableException extends Exception implements Localizable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocalizableException(final String message) {
		super(message);
	}

	public LocalizableException(final Throwable cause) {
		super(cause);
	}

	public LocalizableException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] getArgs() {
		final Throwable cause = getCause();
		if (cause != null) {
			return new Object[] { cause.getMessage() };
		}
		return null;
	}
}
