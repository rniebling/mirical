/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: SessionListener.java 62420 2014-05-14 06:28:36Z norman $
 */
package de.subito.sunrise.webapp.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import de.subito.sunrise.data.hibernate.HibernatePersistenceProviderResolver;
import de.subito.sunrise.webapp.SchedulerInitializer;
import de.subito.sunrise.webapp.spring.ApplicationSessionListener;

/**
 * 
 * @author $Author: norman $
 * @version $Revision: 62420 $
 */
public class SessionListener implements HttpSessionListener, ServletContextListener {

	/**
	 * Logger für diese Klasse
	 */
	private static final Log LOG = LogFactory.getLog(SessionListener.class);

	public void sessionCreated(final HttpSessionEvent se) {
		LOG.info("Create Session  : " + se.getSession().getId());

		final ApplicationSessionListener info = getApplicationState(se.getSession().getServletContext());
		if (info != null) {
			info.sessionCreated(se.getSession());
		}
	}

	public void sessionDestroyed(final HttpSessionEvent se) {
		LOG.info("Destroy Session : " + se.getSession().getId());
		final ApplicationSessionListener info = getApplicationState(se.getSession().getServletContext());
		if (info != null) {
			info.sessionDestroyed(se.getSession());
		}
	}

	private ApplicationSessionListener getApplicationState(final ServletContext servletContext) {
		final WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		return (ApplicationSessionListener) context.getBean("applicationStateInfo");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void contextInitialized(final ServletContextEvent event) {
		HibernatePersistenceProviderResolver.register();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void contextDestroyed(final ServletContextEvent event) {
		final WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event
				.getServletContext());
		final SchedulerInitializer initializer = context.getBean(SchedulerInitializer.class);
		initializer.shutdown();
	}

}
