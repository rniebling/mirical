/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: LockManager.java 26078 2011-03-01 10:17:50Z gerrit $
 */
package de.nieblings.webapp.core.lock;

import de.nieblings.webapp.core.EntityBean;
import de.nieblings.webapp.core.User;


/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 26078 $
 */
public interface LockManager {

	void unlock(EntityBean bean, String sessionId) throws UnlockFailedException;

	void unlockAll(String sessionId) throws UnlockFailedException;

	boolean lock(EntityBean bean, String sessionId, User user) throws LockFailedException;

	void close();

	User whoLocksMe(EntityBean bean);

	void lockExternal(String sessionId, User user, EntityBean bean) throws LockFailedException;

	void unlockExternal(EntityBean bean, String sessionId) throws UnlockFailedException;

	void unlockExternal(Sperre sperre);
}
