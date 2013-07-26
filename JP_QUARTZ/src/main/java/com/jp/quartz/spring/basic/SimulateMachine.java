/**
 * 
 */
package com.jp.quartz.spring.basic;

import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author dimit.chadha
 * 
 */
public class SimulateMachine {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// new ClassPathXmlApplicationContext("quartz-app.xml");
		FileSystemXmlApplicationContext factory = new FileSystemXmlApplicationContext(
				"../JP_QUARTZ/properties/quartz-app.xml");
	}

}
