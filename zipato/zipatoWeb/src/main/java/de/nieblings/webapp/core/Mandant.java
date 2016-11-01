/*
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: Mandant.java 80096 2015-05-19 15:49:43Z norman $
 */
package de.nieblings.webapp.core;

import java.util.ArrayList;
import java.util.List;



/**
 * 
 * @author $Author: norman $
 * @version $Revision: 80096 $
 */
public class Mandant extends EntityBean {

	private static final long serialVersionUID = 6452851696950699371L;


	private String name;


	private String kurzname;


//	private Adresse adresse = new Adresse();


	private String plzPostfach;


	private String postfach;


	private String telefon;


	private String telefax;


	private String email;




	private final List<User> users = new ArrayList<User>();



	public Mandant() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getKurzname() {
		return kurzname;
	}

	public void setKurzname(final String kurzname) {
		this.kurzname = kurzname;
	}

	public List<User> getUsers() {
		return users;
	}

	// CHECKSTYLE:OFF
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((kurzname == null) ? 0 : kurzname.hashCode());
		return result;
	}

	// CHECKSTYLE:ON

	// CHECKSTYLE:OFF
	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Mandant)) {
			return false;
		}
		final Mandant other = (Mandant) obj;
		if (kurzname == null) {
			if (other.getKurzname() != null) {
				return false;
			}
		} else if (!kurzname.equals(other.getKurzname())) {
			return false;
		}
		return true;
	}

}
