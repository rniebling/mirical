/*
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: EntityBean.java 80096 2015-05-19 15:49:43Z norman $
 */
package de.nieblings.webapp.core;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;




import org.apache.commons.lang.StringUtils;

/**
 * Basis Klasse für alle Hibernate Beans. Sie bietet bereits eine Implementierung für einen
 * PrimaryKey.
 * 
 * @author $Author: norman $
 * @version $Revision: 80096 $
 * 
 */
public abstract class EntityBean implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3506124271623631506L;

	protected static final Comparator<EntityBean> COMPARATOR = new EntityComparator<EntityBean>();

	/**
	 * Das PrimaryKey Feld. Die Generierung erfolgt per Hibernate. Nur lesend auf diesen Wert
	 * zugreifen!
	 */
	private Long id;

	private Date angelegtAm = new Date();

	private Date gueltigBis = Constants.MAX_DATE.getTime();

	private Date letzteAenderungAm;

	private User letzterSachbearbeiter;

	/**
	 * Das Transient Feld foreignId wird ausschließlich bei Importierten Entities verwendet, um
	 * diese vor dem Speichern auf einer Sunrise Datenbank vergleichbar zu machen. Es ist mit der Id
	 * des importierten, fremden Objects zubefüllen
	 */
	private String tempId;

	public EntityBean() {
		super();
	}

	public boolean isTransient() {
		return id == null;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		final EntityBean clone = (EntityBean) super.clone();
		clone.id = null;
		clone.angelegtAm = new Date();
		clone.letzteAenderungAm = null;
		return clone;
	}

	public Long getId() {
		return id;
	}

	// CHECKSTYLE:OFF

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (id == null) {
			result = prime * result + ((tempId == null) ? 0 : tempId.hashCode());
		}
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		final Class<?> class1 = this.getClass();
		final Class<?> class2 = obj.getClass();
	
		if (!class1.equals(class2)) {
			return false;
		}

		final EntityBean other = (EntityBean) obj;

		if (id == null) {
			if (other.getId() != null) {
				return false;
			}
			// wenn beide ids null sind, wird anhand der tempId ermittelt ob equals vorliegt
			return StringUtils.equals(tempId, other.tempId);

		}

		return id.equals(other.getId());
	}

	// CHECKSTYLE:ON

	public Date getAngelegtAm() {
		if (angelegtAm == null) {
			return null;
		}
		return (Date) angelegtAm.clone();
	}

	public Date getLetzteAenderungAm() {
		if (letzteAenderungAm == null) {
			return null;
		}
		return (Date) letzteAenderungAm.clone();
	}

	public Date getGueltigBis() {
		return DateUtils.copy(gueltigBis);
	}

	public void setGueltigBis(final Date gueltigBis) {
		this.gueltigBis = DateUtils.copy(gueltigBis);
	}

	public void setLetzterSachbearbeiter(final User letzterSachbearbeiter) {
		this.letzterSachbearbeiter = letzterSachbearbeiter;
	}

	public User getLetzterSachbearbeiter() {
		return letzterSachbearbeiter;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(final String tempId) {
		this.tempId = tempId;
	}

	public boolean isGueltig() {
		return getGueltigBis().after(new Date());
	}
}
