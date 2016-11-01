/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: NtlmServlet.java 26083 2011-03-01 10:27:24Z gerrit $
 */
package de.subito.sunrise.webapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jcifs.ntlmssp.Type1Message;
import jcifs.ntlmssp.Type2Message;
import jcifs.ntlmssp.Type3Message;
import jcifs.smb.NtlmChallenge;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbAuthException;
import jcifs.smb.SmbSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 26083 $
 */
public class NtlmServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Log LOG = LogFactory.getLog(NtlmServlet.class);

	private static final String NTLM_CHALLENGE = "NtlmChallenge";

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
			IOException {
		final ServletOutputStream out = response.getOutputStream();
		final String authMessage = request.getHeader("Authorization");
		if (authMessage == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setHeader("WWW-Authenticate", "NTLM");
			response.flushBuffer();
			return;
		}
		if (authMessage.startsWith("NTLM ")) {
			final byte[] msg = Base64.decodeBase64(authMessage.substring(5).getBytes());
			final HttpSession session = request.getSession();
			NtlmChallenge challenge = (NtlmChallenge) session.getAttribute(NTLM_CHALLENGE);
			if (challenge == null) {
				challenge = SmbSession.getChallengeForDomain();
				session.setAttribute(NTLM_CHALLENGE, challenge);
			}
			if (msg[8] == 1) {
				final Type1Message type1msg = new Type1Message(jcifs.util.Base64.decode(authMessage.substring(5)));
				final Type2Message type2msg = new Type2Message(type1msg, challenge.challenge, null);
				response.setHeader("WWW-Authenticate", "NTLM " + jcifs.util.Base64.encode(type2msg.toByteArray()));
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			} else if (msg[8] == 3) {

				System.setProperty("jcifs.netbios.wins", "172.17.4.79");
				jcifs.Config.setProperty("jcifs.netbios.wins", "172.17.4.79");

				final Type3Message type3msg = new Type3Message(jcifs.util.Base64.decode(authMessage.substring(5)));

				out.println("<html><body>");
				out.println("Username:" + type3msg.getUser() + "<BR>");
				out.println("Workstation:" + type3msg.getWorkstation() + "<BR>");
				out.println("Domain:" + type3msg.getDomain() + "<BR>");
				out.println("</body></html>");

				final NtlmPasswordAuthentication ntlmAuth = getAuthentication(type3msg, session, challenge);

				try {
					SmbSession.logon(challenge.dc, ntlmAuth);
				} catch (SmbAuthException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}

	}

	private NtlmPasswordAuthentication getAuthentication(final Type3Message type3msg, final HttpSession session,
			final NtlmChallenge challenge) {
		final byte[] lmResponse = (type3msg.getLMResponse() != null) ? type3msg.getLMResponse() : new byte[0];
		final byte[] ntResponse = (type3msg.getNTResponse() != null) ? type3msg.getNTResponse() : new byte[0];
		return new NtlmPasswordAuthentication(type3msg.getDomain(), type3msg.getUser(), challenge.challenge,
				lmResponse, ntResponse);
	}

}
