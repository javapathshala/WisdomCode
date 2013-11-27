/*
 * File: AddServiceClient.java Date: 09-Oct-2013 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.service.client;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import com.jp.service.request.MathRequest;
import com.jp.service.response.MathResponse;

/**
 * @author dchadha
 */
public class ServiceClient {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public static void main(String[] args) {
		AddServiceAdapter adapter = new AddServiceAdapter();
		MathRequest mathRequest = new MathRequest();
		mathRequest.setNum1(16);
		mathRequest.setNum2(16);
		MathResponse mathResponse = adapter.adapterAdd(mathRequest);
		System.out.println(mathResponse.getResponse());
		System.out.println(mathResponse.getMathResponse());
	}

}