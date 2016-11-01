/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: EntityComparator.java 29793 2011-08-23 11:35:15Z norman $
 */
package de.nieblings.webapp.core;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

/**
 * 
 * @author $Author: norman $
 * @version $Revision: 29793 $
 */
public class EntityComparator<T extends EntityBean> implements Serializable, Comparator<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5223519028889763435L;

	/**
	 * {@inheritDoc}
	 */
	public int compare(final T o1, final T o2) {
		if (o1 == o2) { // NOPMD
			return 0;
		}

		final Date s1 = o1.getAngelegtAm();
		final Date s2 = o2.getAngelegtAm();

		final int cs = s1.compareTo(s2);
		if (cs != 0) {
			return -cs;
		}

		final Long i1 = o1.getId();
		final Long i2 = o2.getId();
		if (i1 == null || i2 == null) {
			return 0;
		}
		return i1.compareTo(i2);
	}
}
