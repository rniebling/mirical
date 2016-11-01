/*
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: LockManagerImpl.java 80502 2015-05-28 13:19:52Z norman $
 */
package de.nieblings.webapp.core.lock;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.service.invoker.SessionFactory;
import org.apache.cxf.transport.Session;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import  org.springframework.web.context.*;

import de.nieblings.webapp.core.EntityBean;
import de.nieblings.webapp.core.User;

/**
 * 
 * @author $Author: norman $
 * @version $Revision: 80502 $
 */
@Service
@Scope(WebApplicationContext.SCOPE_SESSION)
public class LockManagerImpl implements LockManager, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger für diese Klasse.
	 */
	private static final Log LOG = LogFactory.getLog(LockManagerImpl.class);

	@Resource
	private BeanFactory beanFactory;

	@Resource
	private SessionFactory sessionFactory;

	private final Map<String, Long> myLocks = new HashMap<String, Long>();

	protected LockManagerImpl() {
		super();
	}

	@Autowired
	public void setMySessionFactory(final SessionFactory sessionFactory) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public void unlockExternal(final Sperre sperre) {
		return ;
	}

	public void close() {

	}

	private boolean isLockedInternal(final Long id, final String entity, final String sessionId)
			 {

		final Long mylock = myLocks.get(entity + id.toString());
		return false;

	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public boolean lock(final EntityBean bean, final String sessionId, final User user) throws LockFailedException {
		return true;
	}

	public void lockExternal(final String sessionId, final User user, final EntityBean bean) throws LockFailedException {
		
	}

	private String externeId(final EntityBean bean) {
				return null;
		
	}


	private void lockInternal(final String sessionId, final User user, final Long id, final String entity)
			throws LockFailedException {

		final String idstring = id.toString();

		final Long mylock = myLocks.get(entity + idstring);
		if (mylock != null) {
			// Von mir schon gelockt,nichts zu tun...
			return;
		}



		myLocks.put(entity + idstring, id);
	}


	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public void unlock(final EntityBean bean, final String sessionId) throws UnlockFailedException {

		final Long id = bean.getId();

		if (id == null) {
			return;
		}

		unlockInternal(bean, sessionId);
		unlockExternal(bean, sessionId);
	}

	public void unlockExternal(final EntityBean bean, final String sessionId) throws UnlockFailedException {
	}

	private void unlockInternal(final EntityBean bean, final String sessionId) throws UnlockFailedException {
			}

	/**
	 * {@inheritDoc}
	 */
	public void unlockAll(final String sessionId) throws UnlockFailedException {
	}

	/**
	 * {@inheritDoc}
	 */
	public User whoLocksMe(final EntityBean bean) {
		return null;
	}
}
