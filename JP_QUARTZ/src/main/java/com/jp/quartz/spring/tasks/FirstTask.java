package com.jp.quartz.spring.tasks;

import org.apache.log4j.Logger;

/**
 * First Task
 * 
 * 
 */
public class FirstTask {

	private static Logger log = Logger.getLogger(FirstTask.class);

	/**
	 * Execute this task
	 * 
	 */
	public void execute() {
		System.out.println("FirstTask runs successfully...");
	}
}
