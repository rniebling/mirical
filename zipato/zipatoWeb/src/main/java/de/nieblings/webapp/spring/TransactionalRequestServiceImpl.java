/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: TransactionalRequestServiceImpl.java 52540 2013-08-01 16:24:24Z gerrit $
 */
package de.nieblings.webapp.spring;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 52540 $
 */
@Service
public class TransactionalRequestServiceImpl implements TransactionalRequestService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void superDoFilterInternal(final OpenSessionInViewFilter openSessionInViewFilter,
			final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
//		openSessionInViewFilter.superDoFilterInternal(request, response, filterChain);
	}

}
