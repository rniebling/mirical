/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: LocalizedException.java 26078 2011-03-01 10:17:50Z gerrit $
 */
package de.nieblings.webapp.exceptions;

/**
 * 
 * @author $Author: gerrit $ 
 * @version $Revision: 26078 $
 */
public abstract class LocalizedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public LocalizedException() {
		super();
	}

	/**
	 * @param message
	 */
	public LocalizedException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public LocalizedException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LocalizedException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
