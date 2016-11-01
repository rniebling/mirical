/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: Zeitrhythmus.java 84462 2015-08-24 10:55:07Z peterl $
 */
package de.nieblings.webapp.core;

import java.util.Arrays;
import java.util.List;

import de.nieblings.webapp.core.schluessel.Defaults;
import de.nieblings.webapp.core.schluessel.Schluessel;



/**
 * 
 * @author $Author: peterl $
 * @version $Revision: 84462 $
 */
public enum Zeitrhythmus implements Schluessel {


	@Defaults(shortcut = "tag.", text = "täglich")
	TAEGLICH,

	@Defaults(shortcut = "wöch.", text = "wöchentlich")
	WOECHENTLICH,

	@Defaults(shortcut = "monatl.", text = "monatlich")
	MONATLICH,

	@Defaults(shortcut = "viertelj.", text = "vierteljährlich")
	VIERTELJAEHRLICH,

	@Defaults(shortcut = "halbj", text = "halbjährlich")
	HALBJAEHRLICH,

	@Defaults(shortcut = "jährl.", text = "jährlich")
	JAEHRLICH;

	public final static List<Enum<?>> KREDIT_ZEITRHYTHMUS = Arrays.asList(new Enum<?>[] {
			MONATLICH,
			VIERTELJAEHRLICH,
			HALBJAEHRLICH,
			JAEHRLICH });

	public final static List<Enum<?>> KREIDT_TILGUNGSVERRECHNUNG = Arrays.asList(new Enum<?>[] {
			TAEGLICH,
			MONATLICH,
			VIERTELJAEHRLICH,
			HALBJAEHRLICH,
			JAEHRLICH });
}
