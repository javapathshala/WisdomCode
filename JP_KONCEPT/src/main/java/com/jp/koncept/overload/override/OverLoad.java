/*
 * File: OverLoad.java Date: 30-Jul-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.koncept.overload.override;

/**
 * @author dimit.chadha
 */
public class OverLoad {

	/**
	 * 
	 */
	public OverLoad() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OverLoad overLoad = new OverLoad();
		int sum = overLoad.add(1, 4);
		float sum2 = overLoad.add(1f, 12f);
		System.out.println(sum);
		System.out.println(sum2);
	}

	private int add(int i, int j) {
		return i + j;
	}

	private float add(float i, float j) {
		return i + j+9f;
	}

}
