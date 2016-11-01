/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: LocaleViewHandler.java 26083 2011-03-01 10:27:24Z gerrit $
 */
package de.nieblings.webapp.servlet;

import java.io.IOException;
import java.util.Locale;

import javax.faces.FacesException;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.LocaleResolver;

/**
 * <code>ViewHandler</code> der das setzen der Benutzer <code>Locale</code> übernimmt.
 * 
 * @author $Author: gerrit $
 * @version $Revision: 26083 $
 */
public class LocaleViewHandler extends ViewHandler {

	private final ViewHandler baseViewHandler;

	/**
	 * 
	 */
	public LocaleViewHandler(final ViewHandler base) {
		baseViewHandler = base;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Locale calculateLocale(final FacesContext context) {
		final ExternalContext externalContext = context.getExternalContext();
		if (externalContext.getContext() instanceof ServletContext) {
			final ServletContext servletContext = (ServletContext) externalContext.getContext();
			final ApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			final LocaleResolver localeResolver = (LocaleResolver) applicationContext.getBean("localeResolver");
			final Locale locale = localeResolver.resolveLocale((HttpServletRequest) externalContext.getRequest());
			LocaleContextHolder.setLocale(locale);
			return locale;
		}
		return baseViewHandler.calculateLocale(context);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String calculateRenderKitId(final FacesContext context) {
		return baseViewHandler.calculateRenderKitId(context);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UIViewRoot createView(final FacesContext context, final String viewId) {
		return baseViewHandler.createView(context, viewId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getActionURL(final FacesContext context, final String viewId) {
		return baseViewHandler.getActionURL(context, viewId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getResourceURL(final FacesContext context, final String path) {
		return baseViewHandler.getResourceURL(context, path);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderView(final FacesContext context, final UIViewRoot viewToRender) throws IOException,
			FacesException {
		baseViewHandler.renderView(context, viewToRender);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UIViewRoot restoreView(final FacesContext context, final String viewId) {
		return baseViewHandler.restoreView(context, viewId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeState(final FacesContext context) throws IOException {
		baseViewHandler.writeState(context);
	}

}
