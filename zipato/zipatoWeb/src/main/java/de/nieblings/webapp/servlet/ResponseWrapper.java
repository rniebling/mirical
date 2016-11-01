/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: ResponseWrapper.java 30535 2011-10-05 11:44:38Z gerrit $
 */
package de.nieblings.webapp.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 30535 $
 */
public class ResponseWrapper extends HttpServletResponseWrapper {

	private final ServletOutputStreamWrapper outputStream;

	private PrintWriter writer;

	private String redirect;
	
	public ResponseWrapper(final HttpServletResponse response) {
		super(response);
		outputStream = new ServletOutputStreamWrapper();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sendRedirect(final String location) throws IOException {
		redirect = location;
//		super.sendRedirect(location);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return outputStream;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (writer == null) {
			writer = new PrintWriter(new OutputStreamWriter(outputStream, getCharacterEncoding()));
		}
		return writer;
	}

	public String getResponseContent() throws IOException {
		getWriter().flush();
		return outputStream.toString(getCharacterEncoding());
	}

	public String getRedirect() {
		return redirect;
	}

}
