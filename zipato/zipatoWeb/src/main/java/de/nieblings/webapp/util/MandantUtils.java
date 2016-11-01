/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: MandantUtils.java 52540 2013-08-01 16:24:24Z gerrit $
 */
package de.nieblings.webapp.util;

import de.nieblings.webapp.core.Mandant;
import de.nieblings.webapp.core.SecurityUtils;



/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 52540 $
 */
public final class MandantUtils {

	private MandantUtils() {
		//
	}

	/**
	 * Liefert den derzeit ausgewählten Mandanten oder <code>null</code> wenn keiner ausgewählt
	 * wurde. Beim Neuanlegen von Datenensätzen sollte diese Methode <b>nicht</b> verwendet werden.
	 * 
	 * @return den eingestellten Mandanten oder <code>null</code>
	 */
	public static Mandant getCurrentMandant() {
		return (Mandant) BeanUtil.getBean("#{mandantenController.selectedMandant}");
	}

	/**
	 * Liefert den derzeit ausgewählten Mandanten oder den default Mandaten des aktuellen Benutzers.
	 * <b>Beim Neuanlegen von Datenensätzen sollte diese Methode verwendet werden.</b>
	 * 
	 * @return den einen Mandanten
	 */
	public static Mandant getMandant() {
		Mandant mandant = getCurrentMandant();
		if (mandant == null) {
			mandant = SecurityUtils.getCurrentUser().getDefaultMandant();
		}
		return mandant;
	}
}
