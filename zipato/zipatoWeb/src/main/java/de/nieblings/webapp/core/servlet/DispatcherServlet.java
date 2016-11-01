/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: DispatcherServlet.java 39016 2012-07-19 09:00:02Z gerrit $
 */
package de.nieblings.webapp.core.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 39016 $
 */
public class DispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
			IOException {
		FacesUtils.prepareFacesContext(req, resp);
		final WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(req
				.getSession().getServletContext());
		final SecurityService securityService = context.getBean("securityService", SecurityService.class);
		if (!securityService.isLoggedIn()) {
			securityService.setCallback(req.getRequestURL() + "?" + req.getQueryString());
			JsfUtils.redirect("/pages/login.xhtml");
			return;
		}
		try {
			dispatch(req, resp, context);
		} catch (NumberFormatException e) {
			ExceptionUtils.handleError(e, req, resp);
		} catch (BeansException e) {
			ExceptionUtils.handleError(e, req, resp);
		} catch (IllegalStateException e) {
			ExceptionUtils.handleError(e, req, resp);
		}
	}

	private void dispatch(final HttpServletRequest req, final HttpServletResponse resp,
			final WebApplicationContext context) {
		final String phaseId = req.getParameter("phaseId");
		final Long valueId = Long.parseLong(req.getParameter("valueId"));

		final JsfWorkflow workflow = context.getBean("workflow", JsfWorkflow.class);
		final Application application = context.getBean("application", Application.class);
		final WorkflowPhase phase = workflow.findPhaseById(phaseId, application.getPhases());
		workflow.open(phase);
		final String outcome = workflow.openPhase(phaseId, valueId);
		if (outcome == null) {
			JsfUtils.redirect("/pages/login.xhtml");
		} else {
			JsfUtils.redirect("/pages/" + outcome + ".xhtml");
		}
	}

}
