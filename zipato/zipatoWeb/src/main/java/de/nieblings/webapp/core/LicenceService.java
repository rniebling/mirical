/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: LicenceService.java 73968 2014-12-19 10:12:09Z norman $
 */
package de.nieblings.webapp.core;




/**
 * 
 * @author $Author: norman $
 * @version $Revision: 73968 $
 */
public interface LicenceService {

	String LICENCE_PK = "licence.pk";

	String LICENCE_LIC = "licence.lic";

	Licence loadLicence() throws BusinessServiceException;

	void updateLicence(byte[] bytes) throws BusinessServiceException;

	void loginEvent(String id, User user, String remoteAddr);

	void logoutEvent(String id);

	int getUserCount();

	boolean isLoginSlotFree();

	Licence getLicence() throws BusinessServiceException;

	void setCounter(LicenseCounter counter);
}
