/*
 * Copyright (C) SUBITO AG, all rights reserved.
 *
 * $Id: DateUtils.java 94190 2016-03-02 09:23:11Z berndw $
 */
package de.nieblings.webapp.core;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;



/**
 * 
 * @author $Author: berndw $
 * @version $Revision: 94190 $
 */
public final class DateUtils {

	/**
	 * All minutes have this many milliseconds except the last minute of the day on a day defined
	 * with a leap second.
	 */
	private static final long MILLISECS_PER_MINUTE = 60 * 1000;

	/**
	 * Number of milliseconds per hour, except when a leap second is inserted.
	 */
	private static final long MILLISECS_PER_HOUR = 60 * MILLISECS_PER_MINUTE;

	/**
	 * Number of leap seconds per day expect on <BR/>
	 * 1. days when a leap second has been inserted, e.g. 1999 JAN 1. <BR/>
	 * 2. Daylight-savings "spring forward" or "fall back" days.
	 */
	private static final long MILLISECS_PER_DAY = 24 * MILLISECS_PER_HOUR;

	public static final String[] MONTHS = new DateFormatSymbols().getMonths();

	private static final int TWO = 2;

	private DateUtils() {
		super();
	}

	/**
	 * Erzeugt eine Kopie des übergebenen <code>Date</code> Objekts.<br/>
	 * Diese Funktion ist nullsafe.
	 * 
	 * @param date Das zu kopierene datum
	 * @return die Kopie
	 */
	public static Date copy(final Date date) {
		if (date == null) {
			return null;
		}
		return new Date(date.getTime());
	}

	/**
	 * Berechnet die Anzahl Tagesübergänge zwischen zwei Zeitpunkten.
	 * <p>
	 * 
	 * Falls bis vor von liegt, wird <tt>null<tt> zurückgegeben.
	 * 
	 * @param von der Startzeitpunkt
	 * @param bis der Endzeitpunkt
	 * @return die die Anzahl Tagesübergänge zwischen Startzeitpunkt und Endzeitpunkt
	 */
	public static Integer daysBetween(final Date von, final Date bis) {
		if (von.after(bis)) {
			return null;
		}
		final Calendar calvon = Calendar.getInstance();
		final Calendar calbis = Calendar.getInstance();
		calvon.setTime(von);
		setTimeTo0(calvon);

		calbis.setTime(bis);
		setTimeTo0(calbis);

		return daysBetween(calvon, calbis).intValue();
	}

	private static Long daysBetween(final Calendar von, final Calendar bis) {
		final long endL = bis.getTimeInMillis() + bis.getTimeZone().getOffset(bis.getTimeInMillis());
		final long startL = von.getTimeInMillis() + von.getTimeZone().getOffset(von.getTimeInMillis());
		return (endL - startL) / MILLISECS_PER_DAY;
	}

	/**
	 * Die Methode liefert die Differenz zwischen Datum "von" und Datum "bis", angebrochene Monate
	 * werden zu ganzen Monaten aufgerundet. Monatsultimo wird beachtet Beispiel: 1)
	 * von="05.07.2010" bis="15.08.2010" --> Methode liefert 2 zurück 2) von="20.07.2010"
	 * bis="15.08.2010" --> Methode liefert 1 zurück 3) von="31.03.2010" bis="30.04.2010" -->
	 * Methode liefert 1 zurück wegen Ultimo 4) von="30.04.2010" bis="31.05.2010" --> Methode
	 * liefert 1 zurück wegen Ultimo
	 * <p>
	 * 
	 * Falls bis vor von liegt, wird <tt>null<tt> zurückgegeben.
	 * 
	 * @param von Das kleinere Datum - Datum von
	 * @param bis Das größere Datum - Datum bis
	 * @return Differenz in vollen Monate.
	 */
	public static Integer monthsBetweenRounded(final Date von, final Date bis) {

		if (von == null || bis == null) {
			return null;
		}

		if (bis.before(von)) {
			return null;
		}

		boolean ultimo = false;
		if (isMonatsultimo(von) && isMonatsultimo(bis)) {
			ultimo = true;
		}

		final Calendar calStart = Calendar.getInstance();
		calStart.setTime(von);
		setTimeTo0(calStart);

		final Calendar calEnde = Calendar.getInstance();
		calEnde.setTime(bis);
		setTimeTo0(calEnde);

		final Calendar calTemp = Calendar.getInstance();

		// org.apache.commons.lang.time.DateUtils.truncate(calStart, Calendar.DATE);
		// org.apache.commons.lang.time.DateUtils.truncate(calEnde, Calendar.DATE);

		int anzMonthsBetween = 0;

		while (calStart.before(calEnde)) {

			calTemp.setTime(calStart.getTime());

			calStart.add(Calendar.MONTH, 1);
			if (!calStart.after(calEnde)) {
				anzMonthsBetween++;
			} else {
				if (calTemp.before(calEnde) && !ultimo) {
					anzMonthsBetween++;
				}
			}
		}

		return anzMonthsBetween;
	}

	/**
	 * Die Methode liefert die Differenz in vollen Monate zwischen Datum "von" und Datum "bis".
	 * Beispiel: 1) von="05.07.2010" bis="15.08.2010" --> Methode liefert 1 zurück 2)
	 * von="20.07.2010" bis="15.08.2010" --> Methode liefert 0 zurück
	 * <p>
	 * 
	 * Falls bis vor von liegt, wird <tt>null<tt> zurückgegeben.
	 * 
	 * @param von Das kleinere Datum - Datum von
	 * @param bis Das größere Datum - Datum bis
	 * @return Differenz in vollen Monate.
	 */
	public static Integer fullMonthsBetween(final Date von, final Date bis) {

		if (von == null || bis == null) {
			return null;
		}

		if (bis.before(von)) {
			return null;
		}

		final Calendar calStart = Calendar.getInstance();
		calStart.setTime(von);
		setTimeTo0(calStart);

		final Calendar calEnde = Calendar.getInstance();
		calEnde.setTime(bis);
		setTimeTo0(calEnde);

		// org.apache.commons.lang.time.DateUtils.truncate(calStart, Calendar.DATE);
		// org.apache.commons.lang.time.DateUtils.truncate(calEnde, Calendar.DATE);

		int anzMonthsBetween = 0;

		while (calStart.before(calEnde)) {

			calStart.add(Calendar.MONTH, 1);
			if (!calStart.after(calEnde)) {
				anzMonthsBetween++;
			}
		}

		return anzMonthsBetween;
	}

	/**
	 * Die Methode liefert die Differenz in Monaten zwischen Datum "von" und Datum "bis". Beispiel:
	 * 1) von="05.07.2010" bis="15.08.2010" --> Methode liefert 1 zurück 2) von="20.07.2010"
	 * bis="15.08.2010" --> Methode liefert 1 zurück
	 * <p>
	 * 
	 * Falls bis vor von liegt, wird <tt>null<tt> zurückgegeben.
	 * 
	 * @param von Das kleinere Datum - Datum von
	 * @param bis Das größere Datum - Datum bis
	 * @return Differenz in Monaten
	 */
	public static Integer monthsBetween(final Date von, final Date bis) {

		if (von == null || bis == null) {
			return null;
		}

		if (bis.before(von)) {
			return null;
		}

		final Calendar calStart = Calendar.getInstance();
		calStart.setTime(von);
		setTimeTo0(calStart);

		final Calendar calEnde = Calendar.getInstance();
		calEnde.setTime(bis);
		setTimeTo0(calEnde);

		int anzMonthsBetween = 0;

		while (!isSameMonth(calStart.getTime(), calEnde.getTime())) {
			calStart.add(Calendar.MONTH, 1);
			anzMonthsBetween++;
		}
		return anzMonthsBetween;
	}

	/**
	 * Berechnet den nächsten Monatsersten eines Datums.
	 * 
	 * @param date das Datum
	 * @return des Datum des nächsten Monatsersten
	 */
	public static Date nextMonatserster(final Date date) {
		if (date == null) {
			return null;
		}
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		setTimeTo0(cal);
		return cal.getTime();
	}

	/**
	 * Liefert den Monatsersten des aktuellen Monats. Die Zeit wird auf 00:00:00 gesetzt.
	 * 
	 * @return Datum des aktuellen Monatsersten. Die Zeit wird auf 00:00:00 gesetzt.
	 * @deprecated use {@link DateUtils#getMonatserster()}
	 */
	@Deprecated
	public static Date monatsersterAktMonat() {

		final Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		setTimeTo0(cal);
		return cal.getTime();
	}

	/**
	 * Liefert den 01.01. des laufenden Jahres. Die Zeit wird auf 00:00:00 gesetzt.
	 * 
	 * @return Neujahr des laufenden Jahres. Die Zeit wird auf 00:00:00 gesetzt.
	 */
	public static Date neujahrAktJahr() {
		final Calendar cal = Calendar.getInstance();
		return neujahr(cal);
	}

	/**
	 * Liefert den 01.01. des Jahres zum angegebenen Zeitpunkt. Die Zeit wird auf 00:00:00 gesetzt.
	 * 
	 * @return Neujahr des Jahres zum angegebenen Zeitpunkt. Die Zeit wird auf 00:00:00 gesetzt.
	 */
	public static Date neujahr(final Date date) {
		if (date == null) {
			return null;
		}

		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return neujahr(cal);
	}

	private static Date neujahr(final Calendar cal) {
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, Calendar.JANUARY);

		setTimeTo0(cal);
		return cal.getTime();
	}

	/**
	 * Liefert den Monatsletzten des aktuellen Monats. Die Zeit wird auf 00:00:00 gesetzt.
	 * 
	 * @return Datum des aktuellen Monatsletztten. Die Zeit wird auf 00:00:00 gesetzt.
	 * @deprecated use {@link DateUtils#getMonatsultimo()}
	 */
	@Deprecated
	public static Date monatsletzterAktMonat() {
		return getMonatsultimo();
	}

	/**
	 * Liefert den Monatsletzten des eines Datums. Die Zeit wird auf 00:00:00 gesetzt.
	 * 
	 * @return Datum des Monatsletztten. Die Zeit wird auf 00:00:00 gesetzt.
	 * @deprecated use {@link DateUtils#getMonatsultimo(Date)}
	 */
	@Deprecated
	public static Date monatsletzter(final Date date) {
		return getMonatsultimo(date);
	}

	/**
	 * addiert einen Monat auf das Datum.
	 * 
	 * @param date das Datum
	 * @return des Datum des nächsten Monatsersten
	 */
	public static Date oneMonthLater(final Date date) {
		if (date == null) {
			return null;
		}
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		return cal.getTime();
	}

	/**
	 * addiert xJahre auf das Datum.
	 * 
	 * @param date das Datum
	 * @return das Datum xYears Jahre spaeter als date
	 */
	public static Date xYearsLater(final Date date, final int xYears) {
		if (date == null) {
			return null;
		}
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, xYears);
		return cal.getTime();
	}

	/**
	 * addiert xMonths Monate auf das Datum.
	 * 
	 * @param date das Datum
	 * @return das Datum xMonths Monate spaeter als date
	 */
	public static Date xMonthsLater(final Date date, final int xMonths) {
		if (date == null) {
			return null;
		}
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, xMonths);
		return cal.getTime();
	}

	/**
	 * Addiert X Tage auf das Datum.
	 * <p>
	 * 
	 * Liefert <tt>null</tt> falls date <tt>null</tt> ist.
	 * 
	 * @param date das Datum
	 * @return das Datum, X Tage später
	 */
	public static Date xDaysLater(final Date date, final Integer days) {
		if (date == null) {
			return null;
		}
		if (days == null) {
			return date;
		}
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		/*
		 * Optimiert: days wird direkt verwendet int i = days; while (i > 0) {
		 * cal.add(Calendar.DATE, days); i--; }
		 */
		cal.add(Calendar.DATE, days);

		return cal.getTime();
	}

	/**
	 * Subtrahiert X Tage vom Datum.
	 * <p>
	 * 
	 * Liefert <tt>null</tt> falls date <tt>null</tt> ist.
	 * 
	 * @param date das Datum
	 * @return das Datum, X Tage später
	 */
	public static Date xDaysBefore(final Date date, final Integer days) {
		if (date == null) {
			return null;
		}
		if (days == null) {
			return date;
		}
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int i = days;
		while (i > 0) {
			cal.add(Calendar.DATE, -1);
			i--;
		}
		return cal.getTime();
	}

	/**
	 * Subtrahiert xMonths Monate vom Datum.
	 * 
	 * @param date das Datum
	 * @return das Datum xMonths Monate spaeter als date
	 */
	public static Date xMonthsBefore(final Date date, final Integer xMonths) {

		if (date == null) {
			return null;
		}
		if (xMonths == null) {
			return date;
		}
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int i = xMonths;
		while (i > 0) {
			cal.add(Calendar.MONTH, -1);
			i--;
		}
		return cal.getTime();
	}

	/**
	 * liefert das Datum von gestern.
	 * 
	 * @return des Datum von gestern
	 */
	public static Date gestern() {
		return getYesterday();
	}

	public static Date heute() {
		final Calendar dateCal = Calendar.getInstance();
		dateCal.setLenient(false);
		setTimeTo0(dateCal);
		return dateCal.getTime();
	}

	public static Date addZeitrhytmus(final Date zahlungstermin, final Zeitrhythmus zeitrhythmus, final boolean ultimo) {
		final Date res = DateUtils.addZeitrhytmus(zahlungstermin, zeitrhythmus);
		if (ultimo) {
			return DateUtils.getMonatsultimo(res);
		}
		return res;
	}

	public static Date addZeitrhytmus(final Date datumZahlung, final Zeitrhythmus zahlungsrhythmus) {
		return calcDatumLetzteZahlung(datumZahlung, zahlungsrhythmus, TWO);
	}

	public static Date addZeitrhytmus(final Date datumZahlung, final Zeitrhythmus zahlungsrhythmus,
			final Integer zahlungstag) {

		final Date lezteZahlung = calcDatumLetzteZahlung(datumZahlung, zahlungsrhythmus, TWO);
		if (zahlungsrhythmus == Zeitrhythmus.WOECHENTLICH) {
			return lezteZahlung;
		}
		if (lezteZahlung != null && zahlungstag != null && zahlungstag.intValue() > 0) {

			final Calendar cal = Calendar.getInstance();
			cal.setTime(lezteZahlung);

			if (cal.get(Calendar.DAY_OF_MONTH) != zahlungstag.intValue()) {
				final int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				if (maxDay >= zahlungstag) {
					cal.set(Calendar.DAY_OF_MONTH, zahlungstag);
				} else {
					cal.set(Calendar.DAY_OF_MONTH, maxDay);
				}
				return cal.getTime();
			}
		}
		return lezteZahlung;
	}

	public static Integer getDay(final Date datum) {
		if (datum == null) {
			return null;
		}
		final Calendar cal = Calendar.getInstance();
		cal.setTime(datum);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Berechnet das Datum der letzen Zahlung fuer eine Reihe von Zahlungen.
	 * <p>
	 * 
	 * Ist einer oder mehrere der Parameter <tt>null</tt> oder anzahlZahlungen < 1, so wird
	 * <tt>null</tt> zurückgeliefert.
	 * 
	 * @param datumErsteZahlung das Datum der ersten Zahlung.
	 * @param zahlungsrhythmus der Zahlungsrhythmus
	 * @param anzahlZahlungen die Anzahl der Zahlungen
	 * @return das Datum der letzten Zahlung
	 */
	public static Date calcDatumLetzteZahlung(final Date datumErsteZahlung, final Zeitrhythmus zahlungsrhythmus,
			final Integer anzahlZahlungen) {

		if (datumErsteZahlung == null || zahlungsrhythmus == null || anzahlZahlungen == null) {
			return null;
		}

		if (anzahlZahlungen < 1) {
			return null;
		}

		final Calendar cal = Calendar.getInstance();
		cal.setTime(datumErsteZahlung);

		int abstand;
		int abstandWoche;
		switch (zahlungsrhythmus) {
		case WOECHENTLICH:
			abstand = 0;
			abstandWoche = 1;
			break;
		case VIERTELJAEHRLICH:
			abstand = 3;
			abstandWoche = 0;
			break;
		case HALBJAEHRLICH:
			abstand = 6;
			abstandWoche = 0;
			break;
		case JAEHRLICH:
			abstand = 12;
			abstandWoche = 0;
			break;
		default:
			abstand = 1;
			abstandWoche = 0;
		}

		// Wird optimiert: zu addierende Monatenzahl = abstand * (anzahlZahlungen - 1)
		/*
		 * for (int i = 1; i < anzahlZahlungen; i++) { cal.add(Calendar.MONTH, abstand); }
		 */
		cal.add(Calendar.MONTH, abstand * (anzahlZahlungen - 1));
		cal.add(Calendar.WEEK_OF_YEAR, abstandWoche * (anzahlZahlungen - 1));

		return cal.getTime();
	}

	public static void addWorkdays(final Calendar calendar, final int dauer) {
		int i = dauer;
		while (i > 0) {
			calendar.add(Calendar.DATE, 1);
			final int day = calendar.get(Calendar.DAY_OF_WEEK);
			if (day != Calendar.SUNDAY && day != Calendar.SATURDAY) {
				i--;
			}
		}
	}

	public static String getActualYearShort() {
		final Calendar cal = Calendar.getInstance();
		final int jahr = cal.get(Calendar.YEAR);

		final String jahrkurz = String.valueOf(jahr);
		return StringUtils.right(jahrkurz, TWO);
	}

	public static int getActualYear() {
		final Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	public static Integer getYear(final Date datum) {
		if (datum == null) {
			return null;
		}
		final Calendar cal = Calendar.getInstance();
		cal.setTime(datum);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * Berechnet das aktuelle Alter einer Person oder eines Objekts ausgehend vom Geburtstag der
	 * Person bzw. dem Tag der Entstehung eines Objekts
	 * 
	 * @param birthday : Ein {@code Date} object, von dem ausgehend das Alter bestimmt werden soll.
	 * 
	 * @return Das Alter in Jahren.
	 */
	public static int getAlter(final Date birthday) {

		final Date today = new Date();

		if (birthday == null || today.compareTo(birthday) < 0) {
			return 0;
		}

		final Calendar cal = Calendar.getInstance();
		cal.setTime(birthday);
		final int birthYear = cal.get(Calendar.YEAR);
		final int birthMonth = cal.get(Calendar.MONTH);
		final int birthDay = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(today);
		final int currentYear = cal.get(Calendar.YEAR);
		final int currentMonth = cal.get(Calendar.MONTH);
		final int currentDay = cal.get(Calendar.DAY_OF_MONTH);

		int age = currentYear - birthYear;
		if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
			age--;
		}

		return age;
	}

	/**
	 * Berechnet das ca. Datum des Geburtstages anhand des Alters in Jahren.
	 * 
	 * @param alter das Alter in Jahren
	 * @return das Geburtstagsdatum als <tt>Date</tt>
	 */
	public static Date getGeburtstag(final int alter) {

		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -alter);
		return cal.getTime();
	}

	/**
	 * Berechnet ein zukünftiges Datum durch Addition von x Jahren.
	 * 
	 * @param anzahlJahre
	 * @return das zukünftige Datum
	 */
	public static Date getFutureDate(final int anzahlJahre) {
		return getGeburtstag(-anzahlJahre);
	}

	/*
	 * Liefert das Datum von gestern.
	 */
	public static Date getYesterday() {
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * Berechnet die Anzahl der Jahre als <tt>BigDecimal</tt> bis zu einem Zukunftsdatum.
	 * <p>
	 * 
	 * Wird als Parameter <tt>null</tt> oder ein Datum in der Vergangenheit übergeben, so liefert
	 * die Methode <tt>null</tt> zurück.
	 * 
	 * @param zukunftsDatum das Zukunftsdatum als <tt>Date</tt>
	 * @return das Ergebnis als <tt>BigDecimal</tt>
	 */
	public static BigDecimal getJahreBis(final Date zukunftsDatum) {
		final Date today = new Date();

		if (zukunftsDatum == null || today.compareTo(zukunftsDatum) > 0) {
			return null;
		}

		final Calendar beginnCal = Calendar.getInstance();
		beginnCal.setTime(today);
		final int currentYear = beginnCal.get(Calendar.YEAR);
		final int currentMonth = beginnCal.get(Calendar.MONTH);
		final int currentDay = beginnCal.get(Calendar.DAY_OF_MONTH);

		final Calendar futureCal = Calendar.getInstance();
		futureCal.setTime(zukunftsDatum);
		final int futureYear = futureCal.get(Calendar.YEAR);
		final int futureMonth = futureCal.get(Calendar.MONTH);
		final int futureDay = futureCal.get(Calendar.DAY_OF_MONTH);

		int ganzeJahre = 0;
		if (currentYear == futureYear) {
			ganzeJahre = 0;
		} else if (currentMonth < futureMonth || (currentMonth == futureMonth && currentDay < futureDay)) {
			ganzeJahre = futureYear - currentYear;
			beginnCal.set(futureYear, currentMonth, currentDay);
		} else {
			ganzeJahre = futureYear - currentYear - 1;
			beginnCal.set(futureYear - 1, currentMonth, currentDay);
		}

		final double jahre = ganzeJahre + getJahresBruchteil(beginnCal, futureCal);

		return new BigDecimal(jahre);
	}

	private static double getJahresBruchteil(final Calendar beginCalendar, final Calendar futureCalendar) {

		final boolean schaltjahr = isSchaltjahr(beginCalendar.get(Calendar.YEAR))
				|| isSchaltjahr(futureCalendar.get(Calendar.YEAR));

		double tage = 0;
		while (!isGleich(futureCalendar, beginCalendar)) {
			beginCalendar.add(Calendar.DAY_OF_MONTH, 1);
			tage++;
		}
		if (schaltjahr) {
			return tage / 366;
		}
		return tage / 365;
	}

	private static boolean isGleich(final Calendar futureCalendar, final Calendar cal) {
		return cal.get(Calendar.YEAR) == futureCalendar.get(Calendar.YEAR)
				&& cal.get(Calendar.MONTH) == futureCalendar.get(Calendar.MONTH)
				&& cal.get(Calendar.DAY_OF_MONTH) == futureCalendar.get(Calendar.DAY_OF_MONTH);
	}

	public static boolean isSchaltjahr(final int year) {
		return (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0));
	}

	public static Date getXDaysBefore(final int days) {
		if (days < 1) {
			return new Date();
		}
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -days);
		return cal.getTime();
	}

	public static boolean isMonatsultimo(final Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(date);
		return cal.get(Calendar.DATE) == cal.getActualMaximum(Calendar.DATE);
	}

	public static boolean isQuartalultimo(final Date date) {
		final Calendar dateCal = Calendar.getInstance();
		dateCal.setLenient(false);
		dateCal.setTime(date);
		final int dateMonth = dateCal.get(Calendar.MONTH);
		final int dateDay = dateCal.get(Calendar.DAY_OF_MONTH);

		if (dateMonth == Calendar.MARCH && dateDay == 31) {
			return true;
		}

		if (dateMonth == Calendar.JUNE && dateDay == 30) {
			return true;
		}

		if (dateMonth == Calendar.SEPTEMBER && dateDay == 30) {
			return true;
		}

		if (dateMonth == Calendar.DECEMBER && dateDay == 31) {
			return true;
		}

		return false;
	}

	/**
	 * Überprüft, ob das erste Datum vor dem zweiten liegt (Tagesgenauigkeit)
	 * <p>
	 * 
	 * Achtung, diese Methode ist nicht null-safe!
	 * 
	 * @param date1 erstes Datum
	 * @param date2 zweites Datum
	 * @return das Ergebnis der Prüfung
	 */
	public static boolean isBeforeDay(final Date date1, final Date date2) {
		return date1.before(date2) && !isSameDay(date1, date2);
	}

	/**
	 * Überprüft, ob das erste Datum nach dem zweiten liegt (Tagesgenauigkeit)
	 * <p>
	 * 
	 * Achtung, diese Methode ist nicht null-safe!
	 * 
	 * @param date1 erstes Datum
	 * @param date2 zweites Datum
	 * @return das Ergebnis der Prüfung
	 */
	public static boolean isAfterDay(final Date date1, final Date date2) {
		return date1.after(date2) && !isSameDay(date1, date2);
	}

	/**
	 * Checks if two date objects are on the same day ignoring time.
	 * <p>
	 * 
	 * Achtung, diese Methode ist nicht null-safe!
	 * 
	 * @param date1 erstes Datum
	 * @param date2 zweites Datum
	 * @return das Ergebnis der Prüfung
	 */
	public static boolean isSameDay(final Date date1, final Date date2) {
		return org.apache.commons.lang.time.DateUtils.isSameDay(date1, date2);
	}

	/**
	 * Überprüft, ob das erste Datum gleich oder vor dem zweiten liegt (Tagesgenauigkeit)
	 * <p>
	 * 
	 * Achtung, diese Methode ist nicht null-safe!
	 * 
	 * @param date1 erstes Datum
	 * @param date2 zweites Datum
	 * @return das Ergebnis der Prüfung
	 */
	public static boolean isBeforeOrSameDay(final Date date1, final Date date2) {
		return date1.before(date2) || isSameDay(date1, date2);
	}

	/**
	 * Überprüft, ob das erste Datum gleich oder nach dem zweiten liegt (Tagesgenauigkeit)
	 * <p>
	 * 
	 * Achtung, diese Methode ist nicht null-safe!
	 * 
	 * @param date1 erstes Datum
	 * @param date2 zweites Datum
	 * @return das Ergebnis der Prüfung
	 */
	public static boolean isAfterOrSameDay(final Date date1, final Date date2) {
		return date1.after(date2) || isSameDay(date1, date2);
	}

	/**
	 * Gibt das übergebene Date mit der Uhrzeit 23:59:59 zurück.
	 * 
	 * @param date
	 * @return Date Ende des tages (23:59:59)
	 */
	public static Date endOfDay(final Date date) {
		if (date == null) {
			return null;
		}
		final Calendar dateCal = Calendar.getInstance();
		dateCal.setLenient(false);
		dateCal.setTime(date);
		dateCal.set(Calendar.HOUR_OF_DAY, 23);
		dateCal.set(Calendar.MINUTE, 59);
		dateCal.set(Calendar.SECOND, 59);
		return dateCal.getTime();
	}

	public static Date startOfDay(final Date date) {
		if (date == null) {
			return null;
		}
		final Calendar dateCal = Calendar.getInstance();
		dateCal.setLenient(false);
		dateCal.setTime(date);
		setTimeTo0(dateCal);
		return dateCal.getTime();
	}

	public static void toVonTime(final Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
	}

	public static void toBisTime(final Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
	}

	public static Date toVonTime(final Date cal) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(cal);
		toVonTime(calendar);
		return calendar.getTime();
	}

	public static Date toBisTime(final Date cal) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(cal);
		toBisTime(calendar);
		return calendar.getTime();
	}

	/**
	 * Gibt ein aus den Parametern day, month und year erzeugtes Date zurück.
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return Date day:month:year
	 */
	public static Date getDate(final int day, final int month, final int year) {
		final Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		setTimeTo0(cal);
		return cal.getTime();
	}

	public static boolean isSameMonth(final Date date1, final Date date2) {
		if (!isSameYear(date1, date2)) {
			return false;
		}
		final Calendar calStart = Calendar.getInstance();
		calStart.setTime(date1);
		final int monatVon = calStart.get(Calendar.MONTH);

		final Calendar calEnde = Calendar.getInstance();
		calEnde.setTime(date2);
		final int monatBis = calEnde.get(Calendar.MONTH);

		if (monatVon == monatBis) {
			return true;
		}
		return false;
	}

	public static boolean isSameQuarter(final Date date1, final Date date2) {
		if (!isSameYear(date1, date2)) {
			return false;
		}
		final Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		final Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		if (getQuarter(date1) == getQuarter(date2)) {
			return true;
		}
		return false;
	}

	public static Integer getQuarter(final Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return (cal.get(Calendar.MONTH) / 3) + 1;
	}

	public static Date getLetztenQuartalsultimo(final Date date) {
		if (date == null) {
			return null;
		}

		final Calendar dateCal = Calendar.getInstance();
		dateCal.setLenient(false);
		dateCal.setTime(date);
		dateCal.set(Calendar.DAY_OF_MONTH, 1);
		dateCal.add(Calendar.MONTH, -1);
		setTimeTo0(dateCal);

		for (int i = 0; i < 3; i++) {
			final int dateMonth = dateCal.get(Calendar.MONTH);

			if (dateMonth == Calendar.MARCH) {
				dateCal.set(Calendar.DAY_OF_MONTH, 31);
				return dateCal.getTime();
			}

			if (dateMonth == Calendar.JUNE) {
				dateCal.set(Calendar.DAY_OF_MONTH, 30);
				return dateCal.getTime();
			}

			if (dateMonth == Calendar.SEPTEMBER) {
				dateCal.set(Calendar.DAY_OF_MONTH, 30);
				return dateCal.getTime();
			}

			if (dateMonth == Calendar.DECEMBER) {
				dateCal.set(Calendar.DAY_OF_MONTH, 31);
				return dateCal.getTime();
			}

			dateCal.add(Calendar.MONTH, -1);
		}

		return null;
	}

	public static Date joinDateTime(final Date date, final Date time) {
		if (date == null || time == null) {
			return date;
		}

		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		final Calendar timeCal = Calendar.getInstance();
		timeCal.setTime(time);

		cal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
		cal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));

		return cal.getTime();
	}

	public static Date getMonatsultimo() {
		return getMonatsultimo(new Date());
	}

	public static Date getMonatsultimo(final Date date) {
		if (date == null) {
			return null;
		}

		final Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(date);
		setTimeTo0(cal);

		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return org.apache.commons.lang.time.DateUtils.truncate(cal.getTime(), Calendar.DATE);
	}

	public static Date getMonatserster() {
		return getMonatserster(new Date());
	}

	public static Date getMonatserster(final Date date) {
		if (date == null) {
			return null;
		}

		final Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(date);
		setTimeTo0(cal);

		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return org.apache.commons.lang.time.DateUtils.truncate(cal.getTime(), Calendar.DATE);
	}

	public static Date getQuartalsultimo(final Date date) {
		if (date == null) {
			return null;
		}

		final Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(date);
		setTimeTo0(cal);

		cal.set(Calendar.DAY_OF_MONTH, 1);
		do {
			cal.add(Calendar.MONTH, 1);
		} while (cal.get(Calendar.MONTH) != Calendar.APRIL && cal.get(Calendar.MONTH) != Calendar.JULY
				&& cal.get(Calendar.MONTH) != Calendar.OCTOBER && cal.get(Calendar.MONTH) != Calendar.JANUARY);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();

	}

	public static Date getHalbjahresultimo(final Date date) {
		if (date == null) {
			return null;
		}

		final Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(date);
		setTimeTo0(cal);

		cal.set(Calendar.DAY_OF_MONTH, 1);
		do {
			cal.add(Calendar.MONTH, 1);
		} while (cal.get(Calendar.MONTH) != Calendar.JULY && cal.get(Calendar.MONTH) != Calendar.JANUARY);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();

	}

	public static Date getNextMonatsultimo(final Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(date);
		setTimeTo0(cal);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return org.apache.commons.lang.time.DateUtils.truncate(cal.getTime(), Calendar.DATE);
	}

	public static int getJahr(final Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	public static int getAgeAt(final Date birthday, final Date date) {

		if (birthday == null || date == null || date.compareTo(birthday) < 0) {
			return 0;
		}

		final Calendar cal = Calendar.getInstance();
		cal.setTime(birthday);
		final int birthYear = cal.get(Calendar.YEAR);
		final int birthMonth = cal.get(Calendar.MONTH);
		final int birthDay = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(date);
		final int year = cal.get(Calendar.YEAR);
		final int month = cal.get(Calendar.MONTH);
		final int day = cal.get(Calendar.DAY_OF_MONTH);

		int age = year - birthYear;
		if (month < birthMonth || (month == birthMonth && day < birthDay)) {
			age--;
		}

		return age;
	}

	public static Date max(final Date date1, final Date date2) {
		if (date1 == null) {
			return date2;
		}
		if (date2 == null) {
			return date1;
		}
		if (date1.after(date2)) {
			return date1;
		}
		return date2;
	}

	public static Date min(final Date date1, final Date date2) {
		if (date1 == null) {
			return date2;
		}
		if (date2 == null) {
			return date1;
		}
		if (date1.before(date2)) {
			return date1;
		}
		return date2;
	}

	/**
	 * Überprüft, ob die beiden übergebenen Daten im selben Jahr liegen.
	 * 
	 * @param date1 erstes Datum
	 * @param date2 zweites Datum
	 * @return das Ergebnis der Prüfung
	 */
	public static boolean isSameYear(final Date date1, final Date date2) {
		if (date1 == null || date2 == null) {
			return date1 == date2; // NOPMD
		}
		final Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		final Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR));
	}

	/**
	 * Überprüft, ob das erste Datum in einem der Vorjahre des zweiten Datums liegt.
	 * 
	 * @param date1 erstes Datum
	 * @param date2 zweites Datum
	 * @return das Ergebnis der Prüfung
	 */
	public static Boolean isBeforeYear(final Date date1, final Date date2) {
		if (date1 == null || date2 == null) {
			return null;
		}
		final Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		final Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		return (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR));
	}

	/**
	 * Gibt für ein Datum den letzten Tag (31.12.) des Vorjahres zurück.
	 * 
	 * @param date Datum
	 * @return der 31.12. des Vorjahres (Uhrzeit: 0:00 Uhr)
	 */
	public static Date getLastDayOfPrevYear(final Date date) {
		if (date == null) {
			return null;
		}
		final Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.add(Calendar.YEAR, -1);

		setTimeTo0(cal);

		return cal.getTime();
	}

	public static Date getFirstDayOfPrevYear(final Date date) {
		if (date == null) {
			return null;
		}
		final Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.add(Calendar.YEAR, -1);

		setTimeTo0(cal);

		return cal.getTime();
	}

	public static Date jahresUltimo(final int jahr) {
		return getDate(30, 12, jahr);
	}

	public static Boolean isLastDayOfYear(final Date datum) {
		if (datum == null) {
			return null;
		}
		final Calendar cal = Calendar.getInstance();
		cal.setTime(datum);

		return (cal.get(Calendar.MONTH) == Calendar.DECEMBER && cal.get(Calendar.DAY_OF_MONTH) == 31);
	}

	/**
	 * @return Letzter Buchungstag
	 */
	public static Date letzterBuchungstag() {
		return vorigerBuchungstag(new Date());
	}

	/**
	 * @param stichtag Angegebener Stichtag
	 * @return Voriger Buchungstag zum angegebenen Stichtag
	 */
	// TODO Feiertage berücksichigen
	public static Date vorigerBuchungstag(final Date stichtag) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(stichtag);
		final int weekday = cal.get(Calendar.DAY_OF_WEEK);

		final int offset;

		switch (weekday) {
		case Calendar.SUNDAY:
			offset = 2;
			break;
		case Calendar.MONDAY:
			offset = 3;
			break;
		default:
			offset = 1;
		}

		return xDaysBefore(stichtag, offset);
	}

	public static String format(final Date date) {
		return format(date, null);
	}

	public static String format(final Date date, final String pattern) {
		if (date == null) {
			return null;
		}

		if (pattern != null) {
			return new SimpleDateFormat(pattern).format(date);
		}
		return new SimpleDateFormat("dd.MM.yyyy").format(date);
	}

	public static Calendar toCalendar(final Date date) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static boolean validateDateFormat(final String dateString, final String pattern) {
		if (StringUtils.isBlank(dateString)) {
			return false;
		}
		final String datePattern = StringUtils.isNotBlank(pattern) ? pattern : "dd.MM.yyyy";
		Date datum;

		try {
			final SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateInstance();
			sdf.applyPattern(datePattern);
			sdf.setLenient(false);

			datum = sdf.parse(dateString);
			return dateString.equals(sdf.format(datum));
		} catch (final ParseException e) {
			return false;
		}
	}

	private static void setTimeTo0(final Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	public static boolean isTime0(final Calendar calendar) {
		if (calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 0
				&& calendar.get(Calendar.SECOND) == 0 && calendar.get(Calendar.MILLISECOND) == 0) {
			return true;
		}
		return false;
	}

	public static Date getDate(final int day, final int month, final int year, final int hour, final int minutes,
			final int seconds) {

		final Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minutes);
		cal.set(Calendar.SECOND, seconds);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	public static Date xMinutesBefore(final Date date, final int minutes) {

		final Calendar cal = Calendar.getInstance();

		if (date != null) {

			cal.setTime(date);
		}

		cal.add(Calendar.MINUTE, minutes * -1);

		return cal.getTime();
	}

	// TODO Tests schreiben

	public static Date getLetzterTagDesJahres(final Date date) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		setTimeTo0(calendar);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.getFirstDayOfWeek());
		calendar.add(Calendar.YEAR, 1);
		calendar.add(Calendar.DAY_OF_WEEK, -1);
		calendar.add(Calendar.SECOND, -1);

		return calendar.getTime();
	}

	public static Date getLetzterTagdesMonats(final Date date) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		setTimeTo0(calendar);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getFirstDayOfWeek());
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_WEEK, -1);
		calendar.add(Calendar.SECOND, -1);

		return calendar.getTime();
	}

	public static Date getErsterTagDerWoche(final Date date) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		setTimeTo0(calendar);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		calendar.getTime();
		return calendar.getTime();
	}

	public static Date getLetzterTagDerWoche(final Date date) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		setTimeTo0(calendar);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		calendar.add(Calendar.SECOND, -1);
		return calendar.getTime();
	}

	public static boolean isBetween(final Date date, final Date first, final Date last) {
		if (date == null) {
			return false;
		}
		return (date.before(last) || isSameDay(date, last)) && (date.after(first) || isSameDay(date, first));
	}

	public static String getAktMonthName() {
		return MONTHS[Calendar.getInstance().get(Calendar.MONTH)];
	}
}
