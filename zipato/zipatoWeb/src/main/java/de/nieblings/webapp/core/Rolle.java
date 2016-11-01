/*
 * Copyright (C) SUBITO AG, all rights reserved.
 *
 * Created by Sunrise CodeGenerator based on ArgoUML and openArchitectureWare.
 * Created on 26.02.09 12:40
 *
 * $Id: Rolle.java 89558 2015-12-02 09:03:35Z lutzf $ 
 *
 */
package de.nieblings.webapp.core;

import java.io.Serializable;	

import org.apache.commons.lang.BooleanUtils;

/**
 * Entity Rolle.
 * 
 * @author $Author: lutzf $
 * @version $Revision: 89558 $
 */
public class Rolle extends EntityBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5547970693161683290L;

	private String name;
	
	private Boolean zusaetzlKompetenzstufe = false;

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public boolean isZusaetzlKompetenzstufe() {
		return BooleanUtils.isTrue(zusaetzlKompetenzstufe);
	}

	public void setZusaetzlKompetenzstufe(final boolean zusaetzlKompetenzstufe) {
		this.zusaetzlKompetenzstufe = zusaetzlKompetenzstufe;
	}

	@Override
	public String toString() {
		return "Rolle [name=" + name + "]";
	}

}
