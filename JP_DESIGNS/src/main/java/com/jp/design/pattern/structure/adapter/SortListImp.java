/*
 * File: SortListImp.java Date: 13-Sep-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.design.pattern.structure.adapter;

import java.util.Collections;
import java.util.List;

/**
 * A third party implementation of a number sorter that deals with Lists, not
 * arrays This is our adaptee. This is target.
 * 
 * @author dchadha
 */
public class SortListImp implements SortListTarget {

	@Override
	public List<Integer> sortList(List<Integer> listIntegers) {
		Collections.sort(listIntegers);
		return listIntegers;
	}

}
