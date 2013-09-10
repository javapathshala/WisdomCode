/*
 * File: RegularExpMap.java Date: 09-Sep-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.collections.map.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * @author dchadha
 */
public class RegularExpMap<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = -910987767606300924L;
	// list of regular expression patterns
	private ArrayList<Pattern> regExPatterns = new ArrayList<Pattern>();
	// list of regular expression values which match patterns
	private ArrayList<V> regExValues = new ArrayList<V>();

	/**
	 * Compile regular expression and add it to the regexp list as key.
	 */
	@Override
	public V put(K key, V value) {

		regExPatterns.add(Pattern.compile(key.toString()));
		regExValues.add(value);
		return value;
	}

	/**
	 * If requested value matches with a regular expression, returns it from
	 * regexp lists.
	 */
	@Override
	public V get(Object key) {
		CharSequence cs = new String(key.toString());

		for (int i = 0; i < regExPatterns.size(); i++) {
			if (regExPatterns.get(i).matcher(cs).matches()) {

				return regExValues.get(i);
			}
		}
		return super.get(key);
	}

	public static void main(String[] args) {
		System.out.println("This is useless");
	}

}
