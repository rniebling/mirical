/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: Constants.java 93087 2016-02-15 14:35:30Z gerrit $
 */
package de.nieblings.webapp.core;

import java.util.Calendar;

/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 93087 $
 */
public final class Constants {

	private Constants() {
		super();
	}

	public static final String FILTER_GUELTIG = "gueltig";

	public static final String FILTER_GUELTIG_COND = "GUELTIG_BIS > :datum ";

	public static final String FILTER_RELEVANT = "relevant";

	public static final String FILTER_AZ_RELEVANT_COND = "RELEVANT = 1";

	public static final Calendar MAX_DATE;

	static {
		MAX_DATE = Calendar.getInstance();
		MAX_DATE.set(9999, Calendar.DECEMBER, 31, 23, 59, 59);
		MAX_DATE.set(Calendar.MILLISECOND,0);
	}

	public static final String TYPE_MONETARY = "monetaryAmount";

	public static final String TYPE_DURATION = "duration";

}
