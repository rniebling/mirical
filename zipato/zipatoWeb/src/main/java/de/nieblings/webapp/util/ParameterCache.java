/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: ParameterCache.java 79917 2015-05-15 13:35:15Z helmutw $
 */
package de.nieblings.webapp.util;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import de.nieblings.webapp.core.Mandant;

/**
 * 
 * @author $Author: helmutw $
 * @version $Revision: 79917 $
 */
public interface ParameterCache {


	void clear();

	Currency getSystemCurrency();

	/**
	 * @param name der gesuchte Parameter als <code>String</code>
	 * @return der Paramter als <code>BigDecimal</code>
	 */
	BigDecimal getBigDecimal(String name);

	BigDecimal getBigDecimal(String name, Mandant mandant);
	
		boolean isTrue(String name);

	boolean isTrue(String name, Mandant mandant);

	String getString(String name);

	String getString(String name, Mandant mandant);

	String getString(String name, Mandant mandant, String defaultString);
	
	int getInt(String name);

	int getInt(String name, Mandant mandant);

	int getInt(String name, Mandant mandant, int defaultValue);
	
	Date getDate(String name);
	
	Date getDate(String name, Mandant mandant);

	Long getLong(String name);

	Long getLong(String name, Mandant mandant);






}
