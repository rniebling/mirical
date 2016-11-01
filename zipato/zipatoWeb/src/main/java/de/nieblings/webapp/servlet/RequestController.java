/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: RequestController.java 86367 2015-10-02 13:35:03Z markuss $
 */
package de.nieblings.webapp.servlet;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author $Author: markuss $
 * @version $Revision: 86367 $
 */
public class RequestController {

	private static final Logger LOG = LoggerFactory.getLogger(RequestController.class);

	public static final String REQUEST_ID = "requestId";

	private final Map<String, ConversationState> states = new ConcurrentHashMap<String, ConversationState>();

	public void control(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		final ConversationState state = getState(request);

		if (state.isIgnore()) {
			chain.doFilter(request, response);
			state.setIgnore(false);
			return;
		}

		if (RequestControlUtils.isAjaxRequest(request)) {
			handleAjax(request, response, chain, state);
			return;
		}

		if (state.isProcessing() || (state.isSkipAll() && state.isProcessingAjax())) {
			LOG.warn("##### skipping request");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// ignore
			}
			response.sendRedirect(request.getRequestURI() + "?conversationContext="
					+ request.getParameter("conversationContext"));
			return;
		}

		state.setProcessing(true);
		final long last = System.currentTimeMillis();

		try {
			handle(request, response, chain, state);
		} finally {
			if (!ignorable(response.getContentType())) {
				state.setLastRequestTime(last);
			}
			state.setProcessing(false);
			state.setSkipAll(false);
			state.setSkipAjax(true);
		}
	}

	private void handleAjax(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain chain, final ConversationState state) {
		final boolean processing = state.isProcessing();
		final long lastRequestTime = state.getLastRequestTime();

		final long requestId = requestId(request);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Processing           : " + processing);
			LOG.debug("From last request    : " + (requestId <= lastRequestTime));
			LOG.debug("Ajax request id      : " + requestId);
			LOG.debug("Last request time    : " + lastRequestTime);
		}
		if (state.isSkipAjax() && (processing || (requestId > 0 && requestId <= lastRequestTime))) {
			LOG.warn("##### skipping ajax request");
		} else {
			state.setProcessingAjax(true);
			try {
				chain.doFilter(request, response);
			} catch (Exception e) {
				LOG.warn("##### error during ajax request ");
			} finally {
				state.setProcessingAjax(false);
			}
		}
	}

	public void setSkipAll(final HttpServletRequest request) {
		getState(request).setSkipAll(true);
	}

	private ConversationState getState(final HttpServletRequest request) {
		final String key = getParameter(request, "conversationContext");
		if (!states.containsKey(key)) {
			states.put(key, new ConversationState());
		}
		return states.get(key);
	}

	private String getParameter(final HttpServletRequest request, final String name) {
		
		final ServletRequestWrapper servletrequest = (ServletRequestWrapper) request;
		final ServletRequest request2 = servletrequest.getRequest();
		return StringUtils.defaultString(request2.getParameter(name));
	}

	private void handle(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain,
			final ConversationState state) throws IOException, ServletException {
		final long requestId = requestId(request);
		final boolean isDebug = LOG.isDebugEnabled();

		if (requestId <= 0) {
			if (isDebug) {
				LOG.debug("execute request");
			}
			chain.doFilter(request, response);
			return;
		}

		if (requestId == state.getPreviousRequestId()) {
			LOG.warn("##### prevent double request " + requestId);
			final ResponseWrapper lastRequest = state.getLastRequest();
			if (lastRequest.getRedirect() == null) {
				lastRequest.sendRedirect(location(request));
			}
			handle(response, lastRequest);
		} else {
			if (isDebug) {
				LOG.debug("handle first request " + requestId);
			}
			handleFirstRequest(request, response, chain, requestId, state);
			if (isDebug) {
				LOG.debug("request processed " + requestId);
			}
		}
	}

	private String location(final HttpServletRequest request) {
		final StringBuffer sb = new StringBuffer();
		sb.append(request.getRequestURI());
		sb.append("?conversationContext=");
		sb.append(request.getParameter("conversationContext"));
		return sb.toString();
	}

	private void handleFirstRequest(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain chain, final long requestId, final ConversationState state) throws IOException,
			ServletException {

		final ResponseWrapper responseWrapper = new ResponseWrapper(response);
		chain.doFilter(request, responseWrapper);
		if (!ignorable(response.getContentType())) {
			state.setLastRequest(responseWrapper);
			state.setPreviousRequestId(requestId);
		}
		handle(response, responseWrapper);
	}

	private void handle(final HttpServletResponse response, final ResponseWrapper responseWrapper) throws IOException {
		if (responseWrapper == null) {
			return;
		}

		final String redirect = responseWrapper.getRedirect();
		if (redirect != null) {
			LOG.debug("send redirect to " + redirect);
			response.sendRedirect(redirect);
			return;
		}

		final String contentType = responseWrapper.getContentType();
		if (contentType != null) {
			response.setContentType(contentType);
		}
		final String charset = responseWrapper.getCharacterEncoding();
		if (charset != null) {
			response.setCharacterEncoding(charset);
		}
		final Locale locale = responseWrapper.getLocale();
		if (locale != null) {
			response.setLocale(locale);
		}

		LOG.debug("print response content");
		try {
			response.getWriter().print(responseWrapper.getResponseContent());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private boolean ignorable(final String contentType) {
		if (contentType == null || StringUtils.contains(contentType, Mime.TEXT_HTML)) {
			return false;
		}
		return true;
	}

	private long requestId(final HttpServletRequest request) {
		final String requestId = request.getParameter(REQUEST_ID);

		if (requestId == null) {

			return 0;
		}
		return Long.parseLong(requestId);
	}

	public void setDontSkipAjax(final HttpServletRequest request) {
		getState(request).setSkipAjax(false);
	}

	public void setIgnore(final HttpServletRequest request) {
		getState(request).setIgnore(true);
	}
}
