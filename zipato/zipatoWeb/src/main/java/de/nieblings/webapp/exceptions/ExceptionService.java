/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: ExceptionService.java 38805 2012-07-12 13:58:02Z gerrit $
 */
package de.nieblings.webapp.exceptions;

/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 38805 $
 */
public interface ExceptionService {

	String getMessage(Throwable throwable);

}
