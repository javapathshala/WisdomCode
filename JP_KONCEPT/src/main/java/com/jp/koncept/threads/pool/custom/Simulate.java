/*
 * File: Simulate.java
 * Date: 10-Jul-2013
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
package com.jp.koncept.threads.pool.custom;

/**
 * @author dimit.chadha
 */
public class Simulate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CustomThreadPool threadPool = new CustomThreadPool(10);
		for (int i = 1; i < 5; i++) {
			threadPool.assign(new SimulateWork());
		}
		threadPool.complete();
		System.out.println("All tasks are done.");
	}

}
