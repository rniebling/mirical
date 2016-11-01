/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: ConversationState.java 36657 2012-05-04 13:28:03Z gerrit $
 */
package de.nieblings.webapp.servlet;


/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 36657 $
 */
public class ConversationState {

	private volatile long previousRequestId;

	private volatile long lastRequestTime;

	private ResponseWrapper lastRequest;

	private volatile boolean processing;
	
	private volatile boolean processingAjax;
	
	private volatile boolean skipAll;
	
	private volatile boolean ignore = false;
	
	private volatile boolean skipAjax = true;

	public long getPreviousRequestId() {
		return previousRequestId;
	}

	public void setPreviousRequestId(final long previousRequestId) {
		this.previousRequestId = previousRequestId;
	}

	public long getLastRequestTime() {
		return lastRequestTime;
	}

	public void setLastRequestTime(final long lastRequestTime) {
		this.lastRequestTime = lastRequestTime;
	}

	public ResponseWrapper getLastRequest() {
		return lastRequest;
	}

	public void setLastRequest(final ResponseWrapper lastRequest) {
		this.lastRequest = lastRequest;
	}

	public boolean isProcessing() {
		return processing;
	}

	public void setProcessing(final boolean processing) {
		this.processing = processing;
	}

	public boolean isProcessingAjax() {
		return processingAjax;
	}

	public void setProcessingAjax(final boolean processingAjax) {
		this.processingAjax = processingAjax;
	}

	public boolean isSkipAll() {
		return skipAll;
	}

	public void setSkipAll(final boolean skipAll) {
		this.skipAll = skipAll;
	}

	public void setSkipAjax(final boolean skipAjax) {
		this.skipAjax = skipAjax;
	}

	public boolean isSkipAjax() {
		return skipAjax;
	}

	public void setIgnore(final boolean ignore) {
		this.ignore = ignore;
	}

	public boolean isIgnore() {
		return ignore;
	}
}
