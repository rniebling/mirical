/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: ApplicationConfiguration.java 42278 2012-10-22 07:59:03Z gerrit $
 */
package de.nieblings.webapp.servlet;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/**
 * Konfiguration für der Spring IoC-Container.
 * 
 * @author $Author: gerrit $
 * @version $Revision: 42278 $
 */
@Configuration
@Lazy
public class ApplicationConfiguration {

	@Bean
	public LocaleResolver localeResolver() {
		final CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setCookieMaxAge(Integer.MAX_VALUE);
		cookieLocaleResolver.setCookieName("subito-sunrise-locale");
		cookieLocaleResolver.setDefaultLocale(Locale.GERMANY);
		return cookieLocaleResolver;
	}

}
