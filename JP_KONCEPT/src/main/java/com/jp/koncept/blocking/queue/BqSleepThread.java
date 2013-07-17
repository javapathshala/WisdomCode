/*
 * File: BqSleepThread.java
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
package com.jp.koncept.blocking.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author dimit.chadha
 */
public class BqSleepThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		BlockingQueue queue = new ArrayBlockingQueue(2);

		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);

		new Thread(producer).start();
		new Thread(consumer).start();

		Thread.sleep(4000);
	}
}
