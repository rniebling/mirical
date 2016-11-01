/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: Messages.java 75289 2015-01-29 15:15:46Z gerrit $
 */
package de.nieblings.webapp.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import de.nieblings.webapp.exceptions.ExceptionService;


/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 75289 $
 */
public final class Messages {

	private static final Log LOG = LogFactory.getLog(Messages.class);

	public static final String ERROR_SAVE = "error.save.failed";

	public static final String ERROR_CREATE = "error.create.failed";

	public static final String ERROR_PRINT = "error.print.failed";

	public static final String ERROR_EMAIL = "error.email.failed";

	public static final String ERROR_LOAD = "error.load.failed";

	public static final String ERROR_REMOVE = "error.remove.failed";

	public static final String ERROR_COMPUTE = "error.compute.failed";

	/**
	 * 
	 */
	private Messages() {
		super();
	}

	private static MessageSource messageSource() {
		final WebApplicationContext context = context();
		if (context == null) {
			return null;
		}
		return context.getBean("messageSource", MessageSource.class);
	}

	private static ExceptionService exceptionService() {
		final WebApplicationContext context = context();
		if (context == null) {
			return null;
		}
		return context.getBean("exceptionService", ExceptionService.class);
	}

	private static WebApplicationContext context() {
		final ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		return WebApplicationContextUtils.getWebApplicationContext(sc);
	}

	public static void record(final Throwable e) {
		String message = null;
		try {
			message = exceptionService().getMessage(e);
		} finally {
			if (message == null) {
				message = e.getMessage();
			}
			LOG.error(message, e);
		}

		final FacesMessage msg = new FacesMessage(message);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		final FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, msg);
	}

	public static void record(final String errorKey, final Throwable e) {
		String text = null;
		try {
			final MessageSource messageSource = messageSource();
			if (messageSource != null) {
				final MessageSourceAccessor msa = new MessageSourceAccessor(messageSource);
				text = text(msa, errorKey, exceptionService().getMessage(e));
			}
		} finally {
			if (text == null) {
				text = e.getMessage();
			}
			LOG.error(text, e);
		}
		final FacesMessage msg = new FacesMessage(text);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		final FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, msg);
	}

	public static void record(final String errorKey) {
		record(errorKey, null);
	}

	public static void record(final Throwable e, final String errorKey, final Object arg) {
		LOG.error(e.getMessage(), e);
		final MessageSourceAccessor msa = new MessageSourceAccessor(messageSource());
		final FacesMessage msg = new FacesMessage(text(msa, errorKey, arg));
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		final FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, msg);
	}

	private static String text(final MessageSourceAccessor msa, final String key, final Object arg) {
		Object[] args;
		if (arg instanceof Object[]) {
			args = (Object[]) arg;
		} else {
			args = new Object[] { arg };
		}
		return msa.getMessage(key, args, "[" + key + "]");
	}

	public static String text(final String key) {
		final MessageSource messageSource = messageSource();
		if (messageSource == null) {
			return key;
		}
		return messageSource.getMessage(key, null, "[" + key + "]", LocaleContextHolder.getLocale());
	}

	public static String text(final String key, final Object[] args) {
		final MessageSource messageSource = messageSource();
		if (messageSource == null) {
			return key;
		}
		return messageSource.getMessage(key, args, "[" + key + "]", LocaleContextHolder.getLocale());
	}

}
