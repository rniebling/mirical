/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: LicenseCounter.java 78227 2015-04-02 11:43:51Z oliver $
 */
package de.nieblings.webapp.core;




/**
 * 
 * @author $Author: oliver $
 * @version $Revision: 78227 $
 */
public interface LicenseCounter {

	void loginEvent(String id, User user, String remoteAddr);

	void logoutEvent(String id);

	int getUserCount();

}
