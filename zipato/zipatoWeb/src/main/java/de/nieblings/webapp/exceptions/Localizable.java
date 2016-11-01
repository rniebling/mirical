/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: Localizable.java 34531 2012-02-24 14:04:38Z gerrit $
 */
package de.nieblings.webapp.exceptions;

/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 34531 $
 */
public interface Localizable {

	String getMessageKey();

	Object[] getArgs();

}
