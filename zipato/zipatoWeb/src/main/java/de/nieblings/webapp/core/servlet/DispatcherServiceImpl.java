/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: DispatcherServiceImpl.java 26083 2011-03-01 10:27:24Z gerrit $
 */
package de.nieblings.webapp.core.servlet;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import de.subito.sunrise.logic.services.DispatcherService;

/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 26083 $
 */
@Service
public class DispatcherServiceImpl implements DispatcherService {

	/**
	 * {@inheritDoc}
	 */
	public String dispatcherUrl(final String phaseId, final Long valueId) {
		final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		return request.getScheme() + "://" + request.getServerName()
				+ (request.getServerPort() != 80 ? ":" + request.getServerPort() : "") + request.getContextPath()
				+ "/dispatch.jsf?phaseId=" + phaseId + "&valueId=" + valueId;
	}
}
