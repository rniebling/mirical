/* 
 * Copyright (C) SUBITO AG, all rights reserved.
 * 
 * $Id: ServletOutputStreamWrapper.java 26083 2011-03-01 10:27:24Z gerrit $
 */
package de.nieblings.webapp.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;

/**
 * 
 * @author $Author: gerrit $
 * @version $Revision: 26083 $
 */
public class ServletOutputStreamWrapper extends ServletOutputStream {

	private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	@Override
	public String toString() {
		return baos.toString();
	}

	public String toString(final String enc) throws UnsupportedEncodingException {
		return baos.toString(enc);
	}

	@Override
	public void write(final int b) throws IOException {
		baos.write(b);
	}

}
