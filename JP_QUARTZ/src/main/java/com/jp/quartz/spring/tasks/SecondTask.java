package com.jp.quartz.spring.tasks;

import org.apache.log4j.Logger;

/**
 * Second Task
 *@author dimit.chadha 
 */
public class SecondTask {

	private static Logger log = Logger.getLogger(SecondTask.class);

	/**
	 * Execute this task
	 * 
	 */
	public void execute() {
		System.out.println("SecondTask runs successfully...");
	}
}
