/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: BeanUtil.java 26083 2011-03-01 10:27:24Z gerrit $
 */
package de.nieblings.webapp.util;

import javax.el.ELException;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author $Author: gerrit $ 
 * @version $Revision: 26083 $
 */
public final class BeanUtil {

	private static final Log LOG = LogFactory.getLog(BeanUtil.class);

	private BeanUtil() {
		super();
	}

	public static Object getBean(final String expression) {
		return evaluate(expression, FacesContext.getCurrentInstance());
	}

	public static Object evaluate(final String expression, final FacesContext context) {
		if (context == null) {
			LOG.error("FacesContext not found");
			return null;
		}
		final Application application = context.getApplication();

		Object value;
		try {
			value = application.evaluateExpressionGet(context, expression, Object.class);
		} catch (final ELException e) {
			LOG.warn(e.getMessage(), e);
			return null;
		}
		return value;
	}
}
