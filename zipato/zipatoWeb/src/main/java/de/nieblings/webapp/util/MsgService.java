/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: MsgService.java 26078 2011-03-01 10:17:50Z gerrit $
 */
package de.nieblings.webapp.util;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 26078 $
 */
@Service
public class MsgService {

	@Resource
	private MessageSource messageSource;

	public String getText(final String key) {
		return messageSource.getMessage(key, null, "[" + key + "]", LocaleContextHolder.getLocale());
	}

	public String getText(final String key, final Object arg) {
		return messageSource.getMessage(key, new Object[] { arg }, "[" + key + "]", LocaleContextHolder.getLocale());
	}

	public String getText(final String key, final Object... arg) {
		return messageSource.getMessage(key, arg, "[" + key + "]", LocaleContextHolder.getLocale());
	}

}
