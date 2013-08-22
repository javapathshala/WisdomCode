/*
 * File: RunSingletonPattern.java Date: 14-Jun-2012 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.design.pattern.create.singleton;

/**
 * @author dimit.chadha
 */
public class RunCrack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Creating SingleTon Pattern Instance");

		try {
			CrackSingleTon stp1 = CrackSingleTon.getInstance();
			System.out.println("First Instance Created::" + stp1);
			System.out.println("#########");
			System.out.println("Try Creating singleton object by serialization");
			System.out.println("#########");
			System.out.println("Try Creating singleton object by Reflection");
			try {
				Class<CrackSingleTon> refClass = (Class<CrackSingleTon>) Class.forName("com.jp.design.pattern.singleton.CrackSingleTon");
				CrackSingleTon refInstance = refClass.newInstance();
				System.out.println("Second Instance is created using clonning -> " + refInstance);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			System.out.println("Try Creating Second Instance by clonning");
			CrackSingleTon clonedObject = (CrackSingleTon) stp1.clone();
			System.out.println("Second Instance is created using clonning -> " + clonedObject);
		} catch (SingletonException singletonException) {
			System.out.println("Instance created is null ->-> Exception is -> " + singletonException.getMessage());
		} catch (CloneNotSupportedException e) {
			System.out.println("Clone of Object Restricted");
			e.printStackTrace();
		}
	}

}
