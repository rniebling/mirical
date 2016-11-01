/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: RequestControlUtils.java 84994 2015-09-05 12:37:37Z helmutw $
 */
package de.nieblings.webapp.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author $Author: helmutw $
 * @version $Revision: 84994 $
 */
public final class RequestControlUtils {

	private RequestControlUtils() {
		// Utils Class
	}

	public static boolean isAjaxRequest(final HttpServletRequest request) {
		return request.getParameter("javax.faces.partial.ajax") != null;
	}

	public static RequestController getController(final HttpServletRequest request) {
		final HttpSession session = request.getSession();
		RequestController rc = (RequestController) session.getAttribute("requestController");

		if (rc == null) {
			rc = new RequestController();
			session.setAttribute("requestController", rc);
		}
		return rc;
	}
	
	public static void setDontSkipAjax(final HttpServletRequest request) {
		getController(request).setDontSkipAjax(request);
	}

	public static void setIgnore(final HttpServletRequest request) {
		getController(request).setIgnore(request);
	}

}
