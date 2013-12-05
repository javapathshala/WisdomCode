/*
 * File: LoginValidator.java Date: Dec 4, 2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jps.jsf.login;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author 72010964
 */
public class LoginValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent component, Object value) throws ValidatorException {
		String user = (String) value;
		if (!user.equalsIgnoreCase("tester")) {
			FacesMessage message = new FacesMessage();
			message.setDetail("User " + user + " does not exists");
			message.setSummary("Login Incorrect");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

}
