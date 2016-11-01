/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: TransactionalRequestService.java 52540 2013-08-01 16:24:24Z gerrit $
 */
package de.nieblings.webapp.spring;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author $Author: gerrit $ 
 * @version $Revision: 52540 $
 */
public interface TransactionalRequestService {

	void superDoFilterInternal(OpenSessionInViewFilter openSessionInViewFilter, HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException;

}
