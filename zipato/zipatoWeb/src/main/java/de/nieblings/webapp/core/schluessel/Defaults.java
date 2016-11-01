/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: Defaults.java 57338 2013-12-19 10:51:31Z norman $
 */
package de.nieblings.webapp.core.schluessel;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 * @author $Author: norman $
 * @version $Revision: 57338 $
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Defaults {

	String text() default "";

	String shortcut() default "";

	String alt() default "";
}
