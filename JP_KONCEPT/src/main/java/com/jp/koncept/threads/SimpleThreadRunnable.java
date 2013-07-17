/*
 * File: SimpleThread.java
 * Date: 17-Apr-2012
 *
 * Copyright (C) 2012, Java Pathshala
 * All rights reserved.
 * Visit us at blog http://javapathshala.wordpress.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.jp.koncept.threads;

/**
 * Description :
 * @author dimit.chadha
 */
public class SimpleThreadRunnable implements Runnable {

	/**
	 * Description :
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleThreadRunnable st = new SimpleThreadRunnable();
		Thread t = new Thread(st);
		t.setName("Tech Thread");
		t.start();
		System.out.println("In Main");
		System.out.println(t.getName());
	}
	public void run() {
		System.out.println("In Thread Run");
	}

}
