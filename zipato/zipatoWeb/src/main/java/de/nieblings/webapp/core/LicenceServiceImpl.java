/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: LicenceServiceImpl.java 73968 2014-12-19 10:12:09Z norman $
 */
package de.nieblings.webapp.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.dao.DataAccessException;


/**
 * 
 * @author $Author: norman $
 * @version $Revision: 73968 $
 */
public class LicenceServiceImpl implements LicenceService {

	private static final Log LOG = LogFactory.getLog(LicenceServiceImpl.class);

	private static final String ERROR_LICENCE_INVALID = "error.licence.invalid";

	
	@Resource
	private ListableBeanFactory listableBeanFactory;

	private LicenseCounter counter = new DefaultCounter();

	private Licence licence;

	@PostConstruct
	public void init() {
		final Map<String, LicenseCounter> beansOfType = listableBeanFactory.getBeansOfType(LicenseCounter.class, true,
				true);
		for (Entry<String, LicenseCounter> entry : beansOfType.entrySet()) {
			this.counter = entry.getValue();
			return;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Licence loadLicence() throws BusinessServiceException {
	
		return licence;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateLicence(final byte[] bytes) throws BusinessServiceException {

		Properties properties;
		try {
			properties = loadProps(bytes);
		} catch (IOException e) {
			throw new BusinessServiceException(e);
		}

		final String key = properties.getProperty("key");
		final String lic = properties.getProperty("lic");

		loadLic(key, lic);

		
	}

	private Licence loadLic(final String key, final String lic) throws BusinessServiceException {
		return licence;
	}

	private Properties loadProps(final byte[] bytes) throws IOException {
		final Properties properties = new Properties();
		final ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		try {
			properties.load(in);
		} finally {
			in.close();
		}
		return properties;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void loginEvent(final String id, final User user, final String remoteAddr) {
		counter.loginEvent(id, user, remoteAddr);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void logoutEvent(final String id) {
		counter.logoutEvent(id);
	}

	public int getUserCount() {
		return counter.getUserCount();
	}

	public Licence getLicence() throws BusinessServiceException {
		if (licence == null) {
			loadLicence();
		}
		return licence;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isLoginSlotFree() {
		if (counter.getUserCount() == 0) {
			return true;
		}

		Licence licence;
		try {
			licence = getLicence();
		} catch (BusinessServiceException e) {
			LOG.error("", e);
			return false;
		}

		final Integer users = licence.getUsers();
		if (users == null) {
			return true;
		}
		return users.intValue() > counter.getUserCount();
	}

	public void setCounter(final LicenseCounter counter) {
		this.counter = counter;
	}

}
