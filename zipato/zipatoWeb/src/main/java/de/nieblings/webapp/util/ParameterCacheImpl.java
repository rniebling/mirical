/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: ParameterCacheImpl.java 80118 2015-05-20 10:47:05Z norman $
 */
package de.nieblings.webapp.util;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import de.nieblings.webapp.core.Mandant;


/**
 * 
 * @author $Author: norman $
 * @version $Revision: 80118 $
 */
public class ParameterCacheImpl implements ParameterCache {

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Currency getSystemCurrency() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getBigDecimal(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getBigDecimal(String name, Mandant mandant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTrue(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTrue(String name, Mandant mandant) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getString(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString(String name, Mandant mandant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString(String name, Mandant mandant, String defaultString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getInt(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInt(String name, Mandant mandant) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInt(String name, Mandant mandant, int defaultValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Date getDate(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate(String name, Mandant mandant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getLong(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getLong(String name, Mandant mandant) {
		// TODO Auto-generated method stub
		return null;
	}
}
