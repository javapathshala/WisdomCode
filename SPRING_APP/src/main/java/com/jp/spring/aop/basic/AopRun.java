/*
 * File: AopRun.java
 * Date: 02-Jul-2013
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
package com.jp.spring.aop.basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author dimit.chadha
 */
public class AopRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("../SPRING_APP/properties/aopConfig.xml");
		ShapeService shapeService = context.getBean("shapeService", ShapeService.class);
		System.out.println(shapeService.getCircleModel().getName());
		//System.out.println(shapeService.getTriangleModel().getName());
		System.out.println(shapeService.getAll(3));
	}
}
