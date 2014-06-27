package com.blogspot.mikler.java;

import java.text.MessageFormat;

import org.apache.log4j.Logger;

public class Bean2 {
	Logger logger = Logger.getLogger(Bean2.class);

	public void importantMethod() {
		System.out.println("Doing job for 3 seconds");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void doSomethingQuick(String param1) {
		System.out.println(MessageFormat.format(
				"doing something quick with param = {0}", param1));
	}
}