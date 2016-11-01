/* 
 * 
 * Copyright (C) SUBITO AG, all rights reserved.
 * $Id: DefaultCounter.java 78227 2015-04-02 11:43:51Z oliver $
 */
package de.nieblings.webapp.core;




/**
 * 
 * @author $Author: oliver $
 * @version $Revision: 78227 $
 */
public class DefaultCounter implements LicenseCounter {

	private volatile int userCount;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void loginEvent(final String id, final User user, final String remoteAddr) {
		userCount++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void logoutEvent(final String id) {
		userCount--;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getUserCount() {
		return userCount;
	}

}
