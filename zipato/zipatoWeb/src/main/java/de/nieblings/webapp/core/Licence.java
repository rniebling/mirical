package de.nieblings.webapp.core;

import java.util.Date;

public class Licence {

	private String name;

	private Integer users = 1;

	private Date validTo;
	
	private String[] modules;

	public Integer getUsers() {
		return users;
	}

	public void setUsers(final Integer user) {
		this.users = user;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(final Date validTo) {
		this.validTo = validTo;
	}

	public String[] getModules() {
		return modules;
	}

	public void setModules(final String[] modules) {
		this.modules = modules;
	}
	
	public boolean isEmpty() {
		return modules == null || modules.length == 0;
	}
}
