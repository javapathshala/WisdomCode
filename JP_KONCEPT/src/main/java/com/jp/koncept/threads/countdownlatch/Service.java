/*
 * File: Service.java
 * Date: 11-Jul-2013
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
package com.jp.koncept.threads.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author dimit.chadha
 */
public class Service implements Runnable {

	private final String name;

	private final int timeToStart;

	private final CountDownLatch latch;

	public Service(String name, int timeToStart, CountDownLatch latch) {
		this.name = name;
		this.timeToStart = timeToStart;
		this.latch = latch;
	}

	public void run() {
		try {
			Thread.sleep(timeToStart);
			System.out.println("Now in  Thread Name:: " + Thread.currentThread().getName());
			System.out.println("Kakee " + name + "  Service started after sleeping for 1000");
		} catch (InterruptedException ex) {
			System.out.println(ex);
		}
		System.out.println(name + " is Up");
		latch.countDown(); // reduce count of CountDownLatch by 1
	}

}
