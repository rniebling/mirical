/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: RequestControlFilter.java 84994 2015-09-05 12:37:37Z helmutw $
 */
package de.nieblings.webapp.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author $Author: helmutw $
 * @version $Revision: 84994 $
 */
public class RequestControlFilter implements Filter {

	/**
	 * {@inheritDoc}
	 */
	public void destroy() {
		//
	}

	/**
	 * {@inheritDoc}
	 */
	public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) resp;

		if (request.getServletPath().contains("faces.resource")) {
			chain.doFilter(request, response);
		} else {
			RequestControlUtils.getController(request).control(request, response, chain);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void init(final FilterConfig config) throws ServletException {
		//
	}

}
