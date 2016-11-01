/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: LockFailedException.java 26078 2011-03-01 10:17:50Z gerrit $
 */
package de.nieblings.webapp.core.lock;

/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 26078 $
 */
public class LockFailedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LockFailedException(final Throwable cause) {
		super(cause);
	}

}
