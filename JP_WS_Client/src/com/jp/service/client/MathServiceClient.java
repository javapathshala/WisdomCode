/*
 * File: MathServiceClient.java
 * Date: 19-Oct-2013
 *
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn 
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any 
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 * 
 * Visit us at www.javapathshala.com
 */
package com.jp.service.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.jp.service.impl.AddServiceImpl;
import com.jp.service.impl.AddServiceImplServiceLocator;
import com.jp.service.impl.MathServiceImpl;
import com.jp.service.impl.MathServiceImplServiceLocator;
import com.jp.service.request.MathRequest;
import com.jp.service.response.MathResponse;

/**
 * @author dchadha
 *
 */
public class MathServiceClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			
			try {
				MathServiceImplServiceLocator locator = new MathServiceImplServiceLocator();
				URL wsdlURL = new URL(locator.getMathServiceImplAddress());
				MathServiceImpl mathSerice = locator.getMathServiceImpl(wsdlURL);
				MathRequest mathRequest= new MathRequest();
				mathRequest.setNum1(19);
				mathRequest.setNum2(12);
				mathRequest.setOpertion("Adding two Numbers");
				MathResponse mathResponse = mathSerice.doAdd(mathRequest);
				System.out.println(mathResponse.getResponse());
				System.out.println(mathResponse.getMathResponse());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
	}

}
