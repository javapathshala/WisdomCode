/*
 * File: LoginForm.java Date: 19-Jul-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */

package com.application.spring.forms;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author dimit.chadha
 */

public class LoginForm {

	@NotEmpty(message = "Username is required")
	private String j_username;

	@NotEmpty(message = "Password is required")
	private String j_password;

	public String getJ_username() {

		return j_username;
	}

	public void setJ_username(String j_username) {

		this.j_username = j_username;
	}

	public String getJ_password() {

		return j_password;
	}

	public void setJ_password(String j_password) {

		this.j_password = j_password;
	}

}
