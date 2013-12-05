/*
 * File: Card.java Date: Dec 4, 2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jps.jsf.login;

import java.util.Random;

/**
 * @author 72010964
 */
public class Card {
	private int left;
	private int right;
	private int result = 0;

	public Card() {
		Random random = new Random();
		int i = 0;
		int j = 0;
		do {
			i = random.nextInt(10);
		} while (i <= 4);

		do {
			j = random.nextInt(100);
		} while (j <= 20);

		left = i;
		right = j;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	// Controller

	public String show() {
		result = left * right;
		return "success";
	}

	public String clear() {
		result = 0;
		return "clear";
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	} 
}
