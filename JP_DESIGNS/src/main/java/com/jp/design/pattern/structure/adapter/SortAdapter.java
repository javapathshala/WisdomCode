/*
 * File: SortAdapter.java Date: 13-Sep-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.design.pattern.structure.adapter;

import java.util.Arrays;
import java.util.List;

/**
 * @author dchadha
 */
public class SortAdapter implements SortArrayClient {

	private SortListTarget sortListTarget;

	public SortAdapter(SortListTarget sortListTarget) {
		this.sortListTarget = sortListTarget;
	}

	@Override
	public Integer[] sortNumbers(Integer[] numbers) {

		List<Integer> listIntegers = Arrays.asList(numbers);
		sortListTarget.sortList(listIntegers);
		System.out.println("Getting List from [] by using adapter Pattern");
		return (Integer[]) listIntegers.toArray();
	}

}
