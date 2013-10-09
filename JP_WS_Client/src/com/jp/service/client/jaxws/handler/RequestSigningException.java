/*
 * File: RequestSigningException.java
 * Date: 09-Oct-2013
 *
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn 
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any 
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 * 
 * Visit us at www.javapathshala.com
 */
package com.jp.service.client.jaxws.handler;

/**
 * @author dchadha
 *
 */
public class RequestSigningException extends Exception {

	private static final long serialVersionUID = 1562049973721130993L;

	public RequestSigningException(Throwable throwable) {
		super(throwable);
	}

	public RequestSigningException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public RequestSigningException(String message) {
		super(message);
	}
}