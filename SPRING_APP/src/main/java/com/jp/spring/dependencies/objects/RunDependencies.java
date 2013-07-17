/*
 * File: RunDependencies.java
 * Date: 08-May-2013
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
package com.jp.spring.dependencies.objects;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author dimit.chadha
 */
public class RunDependencies {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("../SPRING_APP/properties/Dependencies.xml");
		Triangle triangle = (Triangle) context.getBean("triangle2");

		System.out.println(triangle.getPointA());
		System.out.println(triangle.getPointB());
		System.out.println(triangle.getPointC());
		System.out.println(" ## Application Context ##");
		triangle.getBeanAware();
		System.out.println("##List Tag ##");
		System.out.println(triangle.getListTag());
		
		System.out.println("### Triangle Alias##");
		Triangle triangle_alias = (Triangle) context.getBean("triangle-alias");
		System.out.println(triangle_alias.getPointA());
		System.out.println(triangle_alias.getPointB());
		System.out.println(triangle_alias.getPointC());

		
		System.out.println("### Auto Wire ###");
		Triangle triangleAutoWire = (Triangle) context.getBean("triangleAutoWire");
		System.out.println(triangleAutoWire.getAutoWireA());
		System.out.println(triangleAutoWire.getAutoWireB());
		System.out.println(triangleAutoWire.getAutoWireC());
		
	}
}
