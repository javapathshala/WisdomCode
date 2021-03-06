/*
 * File: Employee.java
 * Date: 06-Dec-2012
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
package com.jp.koncept.clone.deep;

/**
 * @author dimit.chadha
 */
public class Employee implements Cloneable {

	private String name;

	private String designation;

	private Department dept;

	public Employee(String name, String desi, Department department) {
		this.designation = desi;
		this.name = name;
		this.dept = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the dept
	 */
	public Department getDept() {
		return dept;
	}

	/**
	 * @param dept
	 *            the dept to set
	 */
	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Object clone() throws CloneNotSupportedException {
		// Deep copy
		Employee emp = new Employee("Neha", "SSE", new Department("002", ".NET"));
		return emp;

	}
}
