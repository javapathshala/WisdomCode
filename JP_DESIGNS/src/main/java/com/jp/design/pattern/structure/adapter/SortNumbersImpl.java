/*
 * File: SortNumbersImpl.java Date: 13-Sep-2013 This source code is part of Java
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
import java.util.Collections;
import java.util.List;

/**
 * This is client interface , which can not be changed. Thus required an adapter
 * inbetween
 * 
 * @author dchadha
 */
public class SortNumbersImpl implements SortArrayClient {

	@Override
	public Integer[] sortNumbers(Integer[] numbers) {
		List<Integer> asList = Arrays.asList(numbers);
		Collections.sort(asList);

		return (Integer[]) asList.toArray();
	}

}
