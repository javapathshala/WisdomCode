/*
 * File: AddServiceClient.java
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
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;

import com.jp.service.client.rpc.handler.XMLSignatureHandler;
import com.jp.service.impl.AddServiceImpl;
import com.jp.service.impl.AddServiceImplServiceLocator;
import com.jp.service.impl.AddServiceImpl;
import com.jp.service.impl.AddServiceImplServiceLocator;
import com.jp.service.request.MathRequest;
import com.jp.service.request.NumRequest;
import com.jp.service.response.MathResponse;
import com.jp.service.response.NumResponse;

/**
 * @author dchadha
 *
 */
public class AddServiceClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			
			try {
				AddServiceImplServiceLocator locator = new AddServiceImplServiceLocator();
				URL wsdlURL = new URL(locator.getAddServiceImplAddress());
				AddServiceImpl addService = locator.getAddServiceImpl(wsdlURL);
				NumRequest numRequest= new NumRequest();
				numRequest.setNum1(19);
				numRequest.setNum2(12);
				HandlerRegistry handleRegistry = locator.getHandlerRegistry();
				QName qName = new QName(locator.getAddServiceImplAddress(), "AddServiceImpl");
				//
				// // Calling Request Handler
				List handlerChain = handleRegistry.getHandlerChain(qName);
				// HandlerInfoChainFactory ch= new HandlerInfoChainFactory();
				// HandlerChain hc = ch.createHandlerChain();

				HandlerInfo info = new HandlerInfo();
				info.setHandlerClass(XMLSignatureHandler.class);
				handlerChain.add(info);
				NumResponse numResponse = addService.doAdd(numRequest);
				System.out.println(numResponse.getNumResult());
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
