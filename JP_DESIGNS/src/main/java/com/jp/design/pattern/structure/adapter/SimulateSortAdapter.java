/*
 * File: SimulateSortAdapter.java Date: 13-Sep-2013 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.design.pattern.structure.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dchadha
 */
public class SimulateSortAdapter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Testing Third party Functionality");
		SortListTarget sortListTarget = new SortListImp();
		List<Integer> unList = new ArrayList<Integer>();
		unList.add(3);
		unList.add(1);
		unList.add(10);
		unList.add(5);
		unList.add(7);
		System.out.println("UnSorted List is : " + unList);
		List<Integer> sortedList = sortListTarget.sortList(unList);
		System.out.println("Sorted List is : " + sortedList);
		System.out.println("############################");

		System.out.println("Testing Client Code Functionality");
		SortArrayClient sortedArrayClient = new SortNumbersImpl();
		Integer[] unSortedNumbers = { 49, 34, 1, 58, 2, 3, 9 };
		System.out.println("Unsorted Array is : " + Arrays.toString(unSortedNumbers));
		Integer[] sortedNumbers = sortedArrayClient.sortNumbers(unSortedNumbers);
		System.out.println("sorted Array is : " + Arrays.toString(sortedNumbers));
		System.out.println("############################");

		// now creating calling adapter

		SortAdapter sortAdapter = new SortAdapter(sortListTarget);
		Integer[] sn = sortAdapter.sortNumbers(unSortedNumbers);
		System.out.println("Sorted Numbers using Adapter : " + Arrays.toString(sn));

	}

}
