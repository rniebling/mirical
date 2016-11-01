/* 
 * Copyright (c) 2007-2009, SUBITO AG, All rights reserved.
 * 
 * $Id: WebServiceServlet.java 29911 2011-08-29 10:50:06Z gerrit $
 */
package de.subito.sunrise.webapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.transport.servlet.CXFServlet;

import de.subito.sunrise.logic.spring.SchemaContextHolder;

/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 29911 $
 */
public class WebServiceServlet extends CXFServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void invoke(final HttpServletRequest request, final HttpServletResponse response) throws ServletException {
		SchemaContextHolder.setSchema("Default");
		super.invoke(request, response);
	}

}
