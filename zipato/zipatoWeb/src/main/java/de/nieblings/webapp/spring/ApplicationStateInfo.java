/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: ApplicationStateInfo.java 53054 2013-08-14 13:24:47Z gerrit $
 */
package de.nieblings.webapp.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import de.nieblings.webapp.core.User;



/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 53054 $
 */
public interface ApplicationStateInfo extends ApplicationSessionListener {

	int getSessionCount();

	void login(HttpServletRequest request, User user, String schema);

	UserInfo getUserInfo(String sessionId);

	List<UserInfo> getUsersLoggedIn();

	List<UserInfo> getUsersLoggedIn(String schema);

	void killSession(UserInfo userInfo);
	
	List<HttpSession> getSessions();

}
