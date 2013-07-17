/*
 * File: SimpleExchanger.java
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
package com.jp.koncept.threads.exchange;

import java.util.concurrent.Exchanger;

/**
 * @author dimit.chadha
 */
public class SimpleExchanger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Exchanger<Address> exchanger = new Exchanger<Address>();

		// create Resi address
		Address residence = new Address();
		residence.setCity("New Delhi");
		residence.setCountry("India");

		// create Office address
		Address office = new Address();
		office.setCity("Altrincham");
		office.setCountry("United Kingdom");

		System.out.println("Address Before Exchange : ");
		System.out.println("Residence Address  : " + residence);
		System.out.println("Office  Address : " + office);

		ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger, residence);
		ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger, office);

		new Thread(exchangerRunnable1).start();
		
		new Thread(exchangerRunnable2).start();
	}

}
