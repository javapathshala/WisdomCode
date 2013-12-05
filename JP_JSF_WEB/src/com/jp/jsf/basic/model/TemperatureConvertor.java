/*
 * File: TemperatureConvertor.java Date: Dec 4, 2013 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.jsf.basic.model;

/**
 * @author 72010964
 */
public class TemperatureConvertor {
	private double celsius;
	private double fahrenheit;
	private boolean initial = true;

	public String celsiusToFahrenheit() {
		initial = false;
		fahrenheit = (celsius * 9 / 5) + 32;
		return "calculated";
	}

	public String reset() {
		initial = true;
		fahrenheit = 0;
		celsius = 0;
		return "reset";
	}

	/**
	 * @return the celsius
	 */
	public double getCelsius() {
		return celsius;
	}

	/**
	 * @param celsius
	 *            the celsius to set
	 */
	public void setCelsius(double celsius) {
		this.celsius = celsius;
	}

	/**
	 * @return the fahrenheit
	 */
	public double getFahrenheit() {
		return fahrenheit;
	}

	/**
	 * @param fahrenheit
	 *            the fahrenheit to set
	 */
	public void setFahrenheit(double fahrenheit) {
		this.fahrenheit = fahrenheit;
	}

	/**
	 * @return the initial
	 */
	public boolean isInitial() {
		return initial;
	}

	/**
	 * @param initial
	 *            the initial to set
	 */
	public void setInitial(boolean initial) {
		this.initial = initial;
	}

}
