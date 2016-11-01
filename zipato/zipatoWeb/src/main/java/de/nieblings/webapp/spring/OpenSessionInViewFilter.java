/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: OpenSessionInViewFilter.java 79917 2015-05-15 13:35:15Z helmutw $
 */
package de.nieblings.webapp.spring;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.nieblings.webapp.core.SecurityUtils;



/**
 * 
 * @author $Author: helmutw $
 * @version $Revision: 79917 $
 */
public class OpenSessionInViewFilter extends org.springframework.orm.hibernate4.support.OpenSessionInViewFilter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain filterChain) throws ServletException, IOException {

		if (SecurityUtils.getCurrentUser() == null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		super.doFilterInternal(request, response, filterChain);
	}

}
