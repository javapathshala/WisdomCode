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
package com.blogspot.mikler.java;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author dimit.chadha
 */
public class TraceRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("../SPRING_APP/properties/TraceConfig.xml");
		
		Bean1 bean1 = context.getBean("bean1", Bean1.class);
		bean1.entryPoint();
		//ShapeService shapeService = context.getBean("shapeService", ShapeService.class);
//		System.out.println(shapeService.getTriangleModel());
//		System.out.println(shapeService.getCircleModel("dimit"));
		//shapeService.test();
//		try {
//			System.out.println(shapeService.getTriangleModel());
//		} catch (IOException e) {
//			
//		}
		//System.out.println(shapeService.getTriangleModel().getName());
		//System.out.println(shapeService.getAll(3));
	}
}
