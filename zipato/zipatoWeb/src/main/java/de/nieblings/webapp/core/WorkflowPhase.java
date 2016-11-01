/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: WorkflowPhase.java 80120 2015-05-20 10:51:56Z norman $
 */
package de.nieblings.webapp.core;

import java.util.List;

/**
 * 
 * @author $Author: norman $
 * @version $Revision: 80120 $
 */
public interface WorkflowPhase {

	Integer getPos();

	PhaseType getType();

	/**
	 * Liefert den Namen der Workflowphase zur�ck.
	 * 
	 * @return der Name als <code>String</code>
	 */
	String getName();

	/**
	 * Liefert die zust�ndige Bean-Klasse der Workflowphase zur�ck.
	 * 
	 * @return die zust�ndige Bean-Klasse vom Typ <code>Class</code>
	 */
	Class<?> getBeanClass();

	/**
	 * Liefert die zust�ndige Bean-Klasse der Workflowphase zur�ck Falls diese <code>null</code>
	 * ist, wird die Bean-Klasse der n�chsten �bergeordneten Workflowphase zur�ckgegeben, welche
	 * ungleich <code>null</code> ist.
	 * 
	 * @return die zust�ndige Bean-Klasse vom Typ <code>Class</code>
	 */
	Class<?> getBean();

	/**
	 * Liefert die ID der Workflowphase zur�ck.
	 * 
	 * @return die ID als <code>String</code>
	 */
	String getPhaseId();

	/**
	 * Gibt die Seite der Workflowphase zur�ck.
	 * 
	 * @return die Seite als <code>String</code>
	 */
	String getPage();

	/**
	 * Liefert die �bergeordnete Workflowphase zur�ck.
	 * 
	 * @return die �bergeordnete Workflowphase vom Typ <code>WorkflowPhase</code>
	 */
	WorkflowPhase getParent();

	/**
	 * Setzt die �bergeordnete Workflowphase.
	 * 
	 * @param parent die �bergeordnete Workflowphase vom Typ <code>WorkflowPhase</code>
	 */
	void setParent(final WorkflowPhase parent);

	/**
	 * Liefert alle untergeordneten Workflowphasen in einer Liste zur�ck.
	 * 
	 * @return die untergeordneten Workflowphasen
	 */
	List<WorkflowPhase> getPhases();

	/**
	 * F�gt der Workflowphase eine neue untergeordnete Workflowphase hinzu. Die neue Workflowphase
	 * wird am Ende der Liste eingef�gt.
	 * 
	 * @param child die neue untergeordnete Workflowphase
	 */
	void addPhase(final WorkflowPhase child);

	/**
	 * Liefert den Controller der Workflowphase als <code>String</code> zur�ck.
	 * 
	 * Ist kein Controller f�r diese Workflowphase gesetzt, so wird der Controller der n�chsten
	 * �bergeordneten Workflowphase zur�ckgegeben.
	 * 
	 * @return der Controller als <code>String</code>
	 */
	String getController();

	/**
	 * Gibt die auf diese Workflowphase folgende Workflowphase zur�ck.
	 * 
	 * @return die n�chste Workflowphase vom Typ <code>WorkflowPhase</code>
	 */
	WorkflowPhase getNext();

	/**
	 * Gibt die auf diese Workflowphase vorhergehende Workflowphase zur�ck.
	 * 
	 * @return die Vorherige Workflowphase vom Typ <code>WorkflowPhase</code>
	 */
	WorkflowPhase getPrevious();

	/**
	 * Gibt die Bedingung f�r die Workflowphase zur�ck.
	 * 
	 * @return die Bedingung vom Typ <code>String</code>
	 */
	String getCondition();

	String getSaveTransactionId();

	List<Tab> getTabs();

	boolean isContinue();

	boolean isPrivate();

	boolean isDisabled();

	String getReadonly();

	String getModule();

	String getValidate();

	String getValidator();

	String getEvaluate();

	String getEvaluator();

	Prozess getProzess();

	String getRole();

	boolean isSinglePhase();

	boolean equals(final Object obj);

	int hashCode();

	boolean isRollenWechsel();

	boolean isDecision();

	boolean isCall();

	boolean isDisableInKisok();

	Rolle getNextRole();

	boolean isExpand();

	String getConfigPage();

	String getConfigController();

	boolean isReferenz();

	boolean isOneForMany();

	boolean isAbfrage();

	Integer getListindex();

	boolean isAbfrageEnabled();

}
