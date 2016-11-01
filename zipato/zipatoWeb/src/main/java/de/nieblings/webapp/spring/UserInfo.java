/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: UserInfo.java 26083 2011-03-01 10:27:24Z gerrit $
 */
package de.nieblings.webapp.spring;

import java.io.Serializable;
import java.util.Date;

import de.nieblings.webapp.core.User;



/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 26083 $
 */
public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String username;

	private final Date login;

	private final String realName;

	private final String schema;

	private final String ip;

	public UserInfo(final User user, final String schema, final String ip) {
		username = user.getUsername();
		login = new Date();
		realName = user.getUsername();
		this.schema = schema;
		this.ip = ip;
	}

	public String getUsername() {
		return username;
	}

	public Date getLogin() {
		return login;
	}

	public String getRealName() {
		return realName;
	}

	public String getSchema() {
		return schema;
	}

	public String getIp() {
		return ip;
	}

}
