/*
 * File: Person.java Date: 29-Jul-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.koncept.clone.copy.constructor;

/**
 * @author dimit.chadha
 */
public class Person {

	private Car car;
	private String name;

	public Person(Car car, String name) {
		this.car = car;
		this.name = name;
	}

	public Person(Person person) {
		this.name = person.name;
		car = new Car(person.car);
		// we assume we have a copy constructor for Car
	}

	public String toString() {
		return "This is person has " + car;
	}

}
