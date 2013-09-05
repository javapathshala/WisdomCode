/*
 * File: Dog.java Date: 05-Sep-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.collections.map.hash;

/**
 * @author dchadha
 */
public class DogTree implements Comparable<Object>{

	private String color;
	private int size;

	public DogTree(String color, int size) {
		this.color=color;
		this.size=size;
	}

	@Override
	public String toString() {
		return "Dog [color=" + color + "]";
	}

	
	@Override
	public int compareTo(Object o) {
		return ((DogTree)this).size-((DogTree)o).size;
	}

//	public boolean equals(Object o) {
//		return ((DogTree) o).color == this.color;
//	}
//
//	public int hashCode() {
//		return color.length();
//	}
}
