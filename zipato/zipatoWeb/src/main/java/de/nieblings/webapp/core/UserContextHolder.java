/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: UserContextHolder.java 35214 2012-03-15 08:08:20Z gerrit $
 */
package de.nieblings.webapp.core;




/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 35214 $
 */
public final class UserContextHolder {

	private UserContextHolder() {
		// no instance
	}

	private static final ThreadLocal<User> USER = new ThreadLocal<User>();

	public static User getUser() {
		return USER.get();
	}

	public static void setUser(final User user) {
		USER.set(user);
	}
}
