/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: UserGroup.java 80120 2015-05-20 10:51:56Z norman $
 */
package de.nieblings.webapp.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;


/**
 * 
 * @author $Author: norman $
 * @version $Revision: 80120 $
 */
public class UserGroup extends EntityBean {

	private static final long serialVersionUID = 1L;

	private String name;


	private String beschreibung;

	private final Set<User> users = new HashSet<User>();

	private final Set<GroupAuthority> authorities = new HashSet<GroupAuthority>();

	private final List<Rolle> rollen = new ArrayList<Rolle>();

	public Set<User> getUsers() {
		return users;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(final String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Set<GroupAuthority> getAuthorities() {
		return authorities;
	}

	public void addAuthority(final GroupAuthority groupAuthority) {
		authorities.add(groupAuthority);
		groupAuthority.setGroup(this);
	}

	public void removeAuthority(final GrantedAuthority authority) {
		authorities.remove(authority);
	}

	public List<Rolle> getRollen() {
		return rollen;
	}

}
