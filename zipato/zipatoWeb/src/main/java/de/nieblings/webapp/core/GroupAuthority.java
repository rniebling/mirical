/*
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: GroupAuthority.java 80120 2015-05-20 10:51:56Z norman $
 */
package de.nieblings.webapp.core;

import org.springframework.security.core.GrantedAuthority;


public class GroupAuthority extends EntityBean implements GrantedAuthority {

	private static final long serialVersionUID = -3120439080369321750L;


	private UserGroup group;


	private String authorityType;

	public GroupAuthority() {
		super();
	}

	public GroupAuthority(final String type) {
		super();
		this.authorityType = type;
	}

	public String getAuthority() {
		return authorityType;
	}

	public String getAuthorityType() {
		return authorityType;
	}

	public UserGroup getGroup() {
		return group;
	}

	public void setGroup(final UserGroup group) {
		this.group = group;
	}

	public void setAuthority(final String authority) {
		this.authorityType = authority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((authorityType == null) ? 0 : authorityType.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GroupAuthority)) {
			return false;
		}
		final GroupAuthority other = (GroupAuthority) obj;
		if (authorityType == null) {
			if (other.getAuthorityType() != null) {
				return false;
			}
		} else if (!authorityType.equals(other.getAuthorityType())) {
			return false;
		}
		return true;
	}

	public int compareTo(final Object o) {
		return 0;
	}

	@Override
	public String toString() {
		return this.authorityType;
	}

}
