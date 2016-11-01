/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: UnlockFailedException.java 26078 2011-03-01 10:17:50Z gerrit $
 */
package de.nieblings.webapp.core.lock;

/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 26078 $
 */
public class UnlockFailedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UnlockFailedException(final Throwable cause) {
		super(cause);
	}

}
