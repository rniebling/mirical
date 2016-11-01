/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: Tab.java 80120 2015-05-20 10:51:56Z norman $
 */
package de.nieblings.webapp.core;

import java.util.List;

/**
 * 
 * @author $Author: norman $
 * @version $Revision: 80120 $
 */
public interface Tab {

	String getPage();

	boolean isVisible();

	void setVisible(boolean visible);

	void setParent(WorkflowPhase workflowPhase);

	List<WorkflowPhase> getPhases();

	boolean isMandatory();

	String getRendered();

	String getController();

	String getMarked();

	String getEvaluate();

	String getEvaluator();

	String getValidate();

	String getValidator();

	boolean isReadonly();
}
