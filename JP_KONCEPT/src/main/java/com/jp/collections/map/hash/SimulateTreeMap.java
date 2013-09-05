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

import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author dchadha
 */
public class SimulateTreeMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DogTree d1 = new DogTree("red", 5);
		DogTree d2 = new DogTree("black", 1);
		DogTree d3 = new DogTree("white", 16);
		// DogTree d4 = new DogTree("white", 10);

		TreeMap<DogTree, Integer> treeMap = new TreeMap<DogTree, Integer>();
		treeMap.put(d1, 5);
		treeMap.put(d2, 1);
		treeMap.put(d3, 16);
		// treeMap.put(d4, 20);
		System.out.println("TreeMap Size : " + treeMap.size());
		for (Entry<DogTree, Integer> entry : treeMap.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}

	}

}
