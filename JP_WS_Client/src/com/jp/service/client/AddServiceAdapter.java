/*
 * File: AddServiceAdapter.java Date: 19-Oct-2013 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
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
import com.jp.service.request.MathRequest;
import com.jp.service.request.NumRequest;
import com.jp.service.response.MathResponse;
import com.jp.service.response.NumResponse;

/**
 * @author dchadha
 */
public class AddServiceAdapter {

	public MathResponse adapterAdd(MathRequest mathRequest) {
		MathResponse mathResponse=null;
		try {
			AddServiceImplServiceLocator locator = new AddServiceImplServiceLocator();
			URL wsdlURL = new URL(locator.getAddServiceImplAddress());
			AddServiceImpl addService = locator.getAddServiceImpl(wsdlURL);

			// If tomcat is configured for https then wsdlURL value is as under
			// String httpsURL =
			// "https://localhost:8443/JP_WS/services/AddServiceImpl?wsdl";
			// System.setProperty("javax.net.ssl.trustStore",
			// "D:\\certi\\cacerts.jks");
			// System.setProperty("javax.net.ssl.trustStorePassword",
			// "dimit123");
			// System.setProperty("javax.net.ssl.keyStore",
			// "D:\\certi\\Dimit.jks");
			// System.setProperty("javax.net.ssl.keyStoreType", "JKS");
			// System.setProperty("javax.net.ssl.keyStorePassword", "dimit123");
			// disable SSL certificate verification - this will be needed when
			// using HTTPS server.
			// disableCertificateValidation();
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

			NumRequest numRequest = modifyRequest(mathRequest);

			NumResponse numResponse = addService.doAdd(numRequest);
			
			mathResponse=new MathResponse();
			mathResponse.setMathResponse(numResponse.getNumResult());
			mathResponse.setResponse("Dimit Success Full");
			// create mathservice response
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mathResponse;
	}

	private NumRequest modifyRequest(MathRequest mathRequest) {
		NumRequest numRequest = new NumRequest();
		numRequest.setNum1(mathRequest.getNum1());
		numRequest.setNum2(mathRequest.getNum2());
		System.out.println(mathRequest.getOpertion());
		return numRequest;
	}
}
