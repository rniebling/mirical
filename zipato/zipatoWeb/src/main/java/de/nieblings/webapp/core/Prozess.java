/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: Prozess.java 80120 2015-05-20 10:51:56Z norman $
 */
package de.nieblings.webapp.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.security.core.GrantedAuthority;


/**
 * 
 * @author $Author: norman $
 * @version $Revision: 80120 $
 */
public class Prozess extends EntityBean  {

	private static final long serialVersionUID = 6973747915800026502L;

	private String name;


	private String prozessExtern;


	private String phaseId;


	private Integer bearbeitungszeit;


	private Rolle startRolle;


	private Boolean teilprozess;


	private Boolean aktiv;




	private String hinweis;


	private String prozessSchablone;






	public String getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(final String phaseId) {
		this.phaseId = phaseId;
	}

	public String getProzessExtern() {
		return prozessExtern;
	}

	public void setProzessExtern(final String prozessExtern) {
		this.prozessExtern = prozessExtern;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((phaseId == null) ? 0 : phaseId.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Prozess)) {
			return false;
		}
		final Prozess other = (Prozess) obj;
		if (phaseId == null) {
			if (other.getPhaseId() != null) {
				return false;
			}
		} else if (!phaseId.equals(other.getPhaseId())) {
			return false;
		}
		return true;
	}



	public Integer getBearbeitungszeit() {
		return bearbeitungszeit;
	}

	public void setBearbeitungszeit(final Integer bearbeitungszeit) {
		this.bearbeitungszeit = bearbeitungszeit;
	}

	public Rolle getStartRolle() {
		return startRolle;
	}

	public void setStartRolle(final Rolle startRolle) {
		this.startRolle = startRolle;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}


	public boolean isTeilprozess() {
		return BooleanUtils.isTrue(teilprozess);
	}

	public void setTeilprozess(final boolean teilprozess) {
		this.teilprozess = teilprozess;
	}

	public boolean isAktiv() {
		return BooleanUtils.isTrue(aktiv);
	}

	public void setAktiv(final boolean aktiv) {
		this.aktiv = aktiv;
	}





	@Override
	public Prozess clone() throws CloneNotSupportedException {
		return (Prozess) super.clone();
	}

	public String getHinweis() {
		return hinweis;
	}

	public void setHinweis(final String hinweis) {
		this.hinweis = hinweis;
	}

	public String getProzessSchablone() {
		return prozessSchablone;
	}

	public void setProzessSchablone(final String prozessSchablone) {
		this.prozessSchablone = prozessSchablone;
	}

}
