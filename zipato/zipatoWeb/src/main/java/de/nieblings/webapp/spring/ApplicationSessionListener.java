/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: ApplicationSessionListener.java 26083 2011-03-01 10:27:24Z gerrit $
 */
package de.nieblings.webapp.spring;

import javax.servlet.http.HttpSession;

public interface ApplicationSessionListener {

	/**
	 * @param session
	 */
	void sessionCreated(final HttpSession session);

	/**
	 * @param session
	 */
	void sessionDestroyed(final HttpSession session);

}
