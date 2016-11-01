/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: SecurityUtils.java 80502 2015-05-28 13:19:52Z norman $
 */
package de.nieblings.webapp.core;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * 
 * @author $Author: norman $
 * @version $Revision: 80502 $
 */
public final class SecurityUtils {

	private static final Log LOG = LogFactory.getLog(SecurityUtils.class);

	private static final Collection<GrantedAuthority> NO_AUTHORITIES = Collections.emptyList();

	private SecurityUtils() {

	}

	public static boolean hasRight(final String right) {
		final String trim = StringUtils.trim(right);


		final Collection<? extends GrantedAuthority> authorities = getUserAuthorities();

		for (final GrantedAuthority grantedAuthority : authorities) {
			if (trim.equals(grantedAuthority.getAuthority())) {
				return true;
			}
		}

		return false;
	}

	private static boolean isImpliedBySuper(final String right) {
		if (right.equals("ROLE_DEBUG") || right.equals("ROLE_ORGA_EDIT")) {
			return false;
		}
		return true;
	}

	public static boolean hasRight(final WorkflowPhase phase) {
		
			return true;
			}

	public static boolean hasWritePermission(final WorkflowPhase phase) {
		if (phase == null) {
			return false;
		}
		if (StringUtils.isNotBlank(phase.getRole())) {
			final String[] roles = phase.getRole().split("\\|");
			for (String role : roles) {
				if (role != null) {
					role = role.trim();
				}
				try {
					if (hasRight(role + "_WRITE")) {
						return true;
					}
				} catch (final IllegalArgumentException e) {
					LOG.error(e.getMessage(), e);
				}
			}
			return false;
		}
		return hasWritePermission(phase.getParent());
	}

	/**
	 * Returns true if the current user has the specified authority.
	 * 
	 * @param authority the authority to test for (e.g. "ROLE_A").
	 * @return true if a GrantedAuthority object with the same string representation as the supplied
	 *         authority name exists in the current user's list of authorities. False otherwise, or
	 *         if the user in not authenticated.
	 * @deprecated use {@link SecurityUtils#hasRight}
	 */
	@Deprecated
	public static boolean userHasAuthority(final String authority) {
		return hasRight(authority);
	}

	/**
	 * Returns the authorities of the current user.
	 * 
	 * @return an array containing the current user's authorities (or an empty array if not
	 *         authenticated), never null.
	 */
	private static Collection<? extends GrantedAuthority> getUserAuthorities() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null || auth.getAuthorities() == null) {
			return NO_AUTHORITIES;
		}
		final Object principal = auth.getPrincipal();
		
		return auth.getAuthorities();
	}

	public static boolean isAvailable(final WorkflowPhase phase, final String role) {
		if (phase.getRole() == null || role == null) {
			return true;
		}

		final String[] roles = phase.getRole().split("\\|");
		for (final String phaseRole : roles) {
			if (phaseRole.equals(role)) {
				return true;
			}
		}

		return false;
	}

	public static User getCurrentUser() {
		User user = getCurrentUserBySpring();
		if (user == null) {
			user = UserContextHolder.getUser();
		}
		return user;

	}

	private static User getCurrentUserBySpring() {
		final SecurityContext secCtx = SecurityContextHolder.getContext();
		final Authentication auth = secCtx.getAuthentication();
		if (auth == null) {
			return null;
		}
		final Object principal = auth.getPrincipal();
		if (principal instanceof User) {
			return (User) principal;
		}
		return null;
	}

	@SafeVarargs
	public static <E extends Enum<?>> boolean isAnyGranted(final E... roles) {
		for (final E role : roles) {
			if (hasRight(role.name())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isAnyGranted(final String... roles) {
		for (final String role : roles) {
			if (hasRight(role)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isAllGranted(final String... roles) {
		for (final String role : roles) {
			if (!hasRight(role)) {
				return false;
			}
		}
		return true;
	}

	public static boolean hasRolle(final String name) {
		return hasRolle(getCurrentUser(), name);
	}

	public static boolean hasRolle(final Rolle rolle) {
		if (rolle == null) {
			return true;
		}
		return hasRolle(getCurrentUser(), rolle.getName());
	}

	public static boolean hasRolle(final User user, final String name) {
		final Collection<Rolle> rollen = getRollen(user);
		for (final Rolle rolle : rollen) {
			if (name.equals(rolle.getName())) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasRolle(final User user, final Rolle rolle) {
		if (rolle == null) {
			return true;
		}
		return hasRolle(user, rolle.getName());
	}

	public static Collection<Rolle> getRollen(final User user) {
		return user.getRollen();
	}

	public static boolean hasRight(final User user, final String right) {
		final Collection<GrantedAuthority> authorities = getAuthorities(user);

		for (final GrantedAuthority grantedAuthority : authorities) {
			if (right.equals(grantedAuthority.getAuthority())) {
				return true;
			}
		}

		return false;
	}

	public static Collection<GrantedAuthority> getAuthorities(final User user) {
		final Collection<GrantedAuthority> res = new HashSet<GrantedAuthority>();
		res.addAll(user.getUserAuthorities());
		final Set<UserGroup> groups = user.getGroups();
		for (final UserGroup userGroup : groups) {
			if (EntityUtils.isValid(userGroup)) {
				res.addAll(userGroup.getAuthorities());
			}
		}
		final Collection<GrantedAuthority> additionalAuthorities = user.getAdditionalAuthorities();
		if (additionalAuthorities != null) {
			res.addAll(additionalAuthorities);
		}
		return res;
	}
}
