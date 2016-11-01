/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: SunriseRequestContextListener.java 93120 2016-02-16 08:23:19Z gerrit $
 */
package de.nieblings.webapp.servlet;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.LocaleResolver;


/**
 * Kopie der Klasse <tt>RequestContextListener</tt> von Spring, nur wird die Locale nicht mit jedem
 * Request gesetzt.<br>
 * Sorgt zusätzlich für das setzen des DB-Schemas.
 * 
 * @author $Author: gerrit $
 * @version $Revision: 93120 $
 */
public class SunriseRequestContextListener implements ServletRequestListener {

	private static final Log LOG = LogFactory.getLog(SunriseRequestContextListener.class);

	private static final String REQUEST_ATTRIBUTES_ATTRIBUTE = RequestContextListener.class.getName()
			+ ".REQUEST_ATTRIBUTES";

	public void requestInitialized(final ServletRequestEvent requestEvent) {
		if (!(requestEvent.getServletRequest() instanceof HttpServletRequest)) {
			throw new IllegalArgumentException("Request is not an HttpServletRequest: "
					+ requestEvent.getServletRequest());
		}
		final HttpServletRequest request = (HttpServletRequest) requestEvent.getServletRequest();
		final ServletRequestAttributes attributes = new ServletRequestAttributes(request);
		request.setAttribute(REQUEST_ATTRIBUTES_ATTRIBUTE, attributes);

		RequestContextHolder.setRequestAttributes(attributes);
		LocaleContextHolder.setLocale(locale(request));

		final String schema = schema(request);
		if (schema != null) {
		//ToDo ???	SchemaContextHolder.setSchema(schema);
		}

	}

	private Locale locale(final HttpServletRequest request) {
		try {
			return determineLocale(request);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return Locale.GERMANY;
	}

	private Locale determineLocale(final HttpServletRequest request) {
		final RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			final ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
			final ServletContext servletContext = attributes.getRequest().getSession().getServletContext();
			final WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			Locale locale = ((LocaleResolver) context.getBean("localeResolver")).resolveLocale(request);
			if (locale.equals(Locale.GERMAN)) {
				locale = Locale.GERMANY;
			} else if (locale.equals(Locale.ENGLISH)) {
				locale = Locale.UK;
			} else if (locale.equals(Locale.FRENCH)) {
				locale = Locale.FRANCE;
			}
			return locale;
		}
		return Locale.GERMANY;
	}

	private String schema(final HttpServletRequest request) {
	/*ToDo	final String schema = (String) request.getSession().getAttribute(DataSourceController.SUNRISE_DBSCHEMA);
		if (schema == null) {
			try {
				return determineSchema();
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
		return schema;
		*/
		return "";
	}

	private String determineSchema() {
		final RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			final ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
			final ServletContext servletContext = attributes.getRequest().getSession().getServletContext();
			final WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			//ToDo ???return ((DataSourceController) context.getBean("dataSourceController")).getDataSource();
			return "";
		}
		return null;
	}

	public void requestDestroyed(final ServletRequestEvent requestEvent) {
		ServletRequestAttributes attributes = null;
		final Object reqAttr = requestEvent.getServletRequest().getAttribute(REQUEST_ATTRIBUTES_ATTRIBUTE);
		if (reqAttr instanceof ServletRequestAttributes) {
			attributes = (ServletRequestAttributes) reqAttr;
		}
		final RequestAttributes threadAttributes = RequestContextHolder.getRequestAttributes();
		if (threadAttributes != null) {
			// We're assumably within the original request thread...
			LocaleContextHolder.resetLocaleContext();
			RequestContextHolder.resetRequestAttributes();
			if (attributes == null && threadAttributes instanceof ServletRequestAttributes) {
				attributes = (ServletRequestAttributes) threadAttributes;
			}
		}
		if (attributes != null) {
			attributes.requestCompleted();
		}
	}
}
