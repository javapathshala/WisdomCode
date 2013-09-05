/*
 * File: SimulateHashMap.java Date: 05-Sep-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.collections.map.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author dchadha
 */
public class SimulateHashMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<Dog, Integer> aniMap = new HashMap<Dog, Integer>();
		Dog d1 = new Dog("red");
		Dog d2 = new Dog("black");
		Dog d3 = new Dog("white");
		Dog d4 = new Dog("white");

		aniMap.put(d1, 10);
		aniMap.put(d2, 15);
		aniMap.put(d3, 5);
		aniMap.put(d4, 20);

		// print size
		System.out.println(aniMap.size());

		// loop HashMap
		for (Entry<Dog, Integer> entry : aniMap.entrySet()) {
			System.out.println(entry.getKey().toString() + " - " + entry.getValue());
		}

	}

}
