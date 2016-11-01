/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: FacesRedirectFilter.java 26083 2011-03-01 10:27:24Z gerrit $
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
 * @author $Author: gerrit $ 
 * @version $Revision: 26083 $
 */
public class FacesRedirectFilter implements Filter {

	public void destroy() {
		// nix zu tun!
	}

	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		if (response instanceof HttpServletResponse && request instanceof HttpServletRequest) {
			final HttpServletResponse res = (HttpServletResponse) response;

			final String uri = ((HttpServletRequest) request).getRequestURI();

			if (uri != null && uri.endsWith(".xhtml")) {
				final int length = uri.length();
				final String newAddress = uri.substring(0, length - 6) + ".jsf";

				res.sendRedirect(newAddress);

			} else {
				res.sendRedirect("index.jsf");
			}

		}

	}

	public void init(final FilterConfig filterConfig) throws ServletException {
		// nix zu tun!
	}

}
