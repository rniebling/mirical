/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: ApplicationStateInfoImpl.java 86367 2015-10-02 13:35:03Z markuss $
 */
package de.nieblings.webapp.spring;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import de.nieblings.webapp.core.LicenceService;
import de.nieblings.webapp.core.User;
import de.nieblings.webapp.core.lock.LockManager;


/**
 * 
 * @author $Author: markuss $
 * @version $Revision: 86367 $
 */
@Service
public class ApplicationStateInfoImpl implements ApplicationStateInfo, Serializable {

	private static final long serialVersionUID = 1L;

	private static final String LICENCE_SERVICE = "licenceService";

	private static final Log LOG = LogFactory.getLog(ApplicationStateInfoImpl.class);

	private final List<String> sessionIds = Collections.synchronizedList(new ArrayList<String>());

	private final List<HttpSession> sessions = Collections.synchronizedList(new ArrayList<HttpSession>());

	private final Map<String, UserInfo> users = Collections.synchronizedMap(new HashMap<String, UserInfo>());

	/**
	 * {@inheritDoc}
	 */
	public void sessionCreated(final HttpSession session) {
		synchronized (sessionIds) {
			sessionIds.add(session.getId());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void killSession(final UserInfo userInfo) {
		final String sessionId = findSessionId(userInfo);
		if (sessionId == null) {
			return;
		}
		final HttpSession session = findSession(sessionId);
		if (session == null) {
			return;
		}
		session.invalidate();
	}

	private HttpSession findSession(final String sessionId) {
		for (HttpSession session : sessions) {
			if (session.getId().equals(sessionId)) {
				return session;
			}
		}
		return null;
	}

	private String findSessionId(final UserInfo userInfo) {
		final Set<Entry<String, UserInfo>> entrySet = users.entrySet();
		for (Entry<String, UserInfo> entry : entrySet) {
			if (entry.getValue().equals(userInfo)) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void sessionDestroyed(final HttpSession session) {
		final String sessionId = session.getId();
		sessions.remove(session);
		sessionIds.remove(sessionId);
		users.remove(sessionId);

		final LicenceService licenceService = (LicenceService) session.getAttribute(LICENCE_SERVICE);
		if (licenceService != null) {
			licenceService.logoutEvent(sessionId);
		}

		final LockManager lockManager = (LockManager) session.getAttribute("lockManager");
		LOG.info("Session size was ~" + (sessionSize(session) / 1024) + "KB");
	}

	private int sessionSize(final HttpSession session) {

		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return 0;
		}
		final Enumeration<String> enumeration = session.getAttributeNames();

		while (enumeration.hasMoreElements()) {
			final String name = enumeration.nextElement();
			final Object obj = session.getAttribute(name);
			try {
				oos.writeObject(obj);
			} catch (NotSerializableException e) {
				// ignore
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		}

		return baos.size();
	}

	/**
	 * {@inheritDoc}
	 */
	public int getSessionCount() {
		synchronized (sessionIds) {
			return sessionIds.size();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void login(final HttpServletRequest request, final User user, final String schema) {
		final HttpSession session = request.getSession();
		final String id = session.getId();

		final String remoteAddr = request.getRemoteAddr();

		sessions.add(session);
		users.put(id, new UserInfo(user, schema, remoteAddr));

		final LicenceService licenceService = WebApplicationContextUtils.getWebApplicationContext(
				session.getServletContext()).getBean(LICENCE_SERVICE, LicenceService.class);
		licenceService.loginEvent(id, user, remoteAddr);

		if (licenceService instanceof ScopedObject) {
			session.setAttribute(LICENCE_SERVICE, ((ScopedObject) licenceService).getTargetObject());
		} else {
			session.setAttribute(LICENCE_SERVICE, licenceService);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<UserInfo> getUsersLoggedIn() {
		return new ArrayList<UserInfo>(users.values());
	}

	/**
	 * {@inheritDoc}
	 */
	public List<UserInfo> getUsersLoggedIn(final String schema) {
		final Collection<UserInfo> values = users.values();
		final List<UserInfo> list = new ArrayList<UserInfo>();
		for (UserInfo userInfo : values) {
			if (schema.equals(userInfo.getSchema())) {
				list.add(userInfo);
			}
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public UserInfo getUserInfo(final String sessionId) {
		return users.get(sessionId);
	}

	public List<HttpSession> getSessions() {
		return sessions;
	}

}
