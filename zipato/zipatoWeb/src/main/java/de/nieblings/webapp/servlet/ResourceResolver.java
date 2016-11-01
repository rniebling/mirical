/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: ResourceResolver.java 84994 2015-09-05 12:37:37Z helmutw $
 */
package de.nieblings.webapp.servlet;

import java.io.IOException;
import java.net.URL;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.view.facelets.util.Resource;

/**
 * 
 * @author $Author: helmutw $
 * @version $Revision: 84994 $
 */
public class ResourceResolver extends javax.faces.view.facelets.ResourceResolver {

	private static final Log LOG = LogFactory.getLog(ResourceResolver.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public URL resolveUrl(final String path) {
		final FacesContext context = FacesContext.getCurrentInstance();
		if (path != null && path.endsWith(".xhtml")) {
			final String resourceName = "META-INF" + path;
			URL resource = getCurrentLoader(this.getClass()).getResource(resourceName);
			if (resource == null) {
				resource = this.getClass().getClassLoader().getResource(resourceName);
			}
			if (resource != null) {
				return resource;
			}
		}
		URL url = null;
		try {
			url = Resource.getResourceUrl(context, path);
		} catch (final IOException e) {
			throw new FacesException(e);
		}
		if (url == null) {
			LOG.error("resource '" + path + "' not found");
		}
		return url;
	}

	public static ClassLoader getCurrentLoader(final Class<?> clazz) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = clazz.getClassLoader();
		}
		return loader;
	}
}
