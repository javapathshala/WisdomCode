/*
 * File: SimulateLog.java
 * Date: 17-Jul-2013
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
package com.jp.spring.aop.logging;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author dimit.chadha
 */
public class SimulateLog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("../SPRING_APP/properties/LogAopConfig.xml");
		final ExampleBean example = (ExampleBean) context.getBean("exampleBean");
		printContentsOfExample(example);
	}

	/**
	 * @param example
	 */
	private static void printContentsOfExample(ExampleBean example) {
		System.out.println("\n\n=============================================================");
		System.out.println(" Java Pathshala .....");
		System.out.println("=============================================================");
		System.out.println("Name: " + example.getExampleName());
		System.out.println("Version: " + example.getExampleVersion());
		System.out.println("Name (Version): " + example.provideNameAndVersion());
		System.out.println("=============================================================\n\n");
	}
}
