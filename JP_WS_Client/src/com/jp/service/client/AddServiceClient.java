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
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;

import com.jp.service.client.rpc.handler.XMLSignatureHandler;
import com.jp.service.impl.AddServiceImpl;
import com.jp.service.impl.AddServiceImplServiceLocator;
import com.jp.service.request.NumRequest;
import com.jp.service.response.NumResponse;

/**
 * @author dchadha
 */
public class AddServiceClient {

	// static {
	// // for localhost testing only
	// javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new
	// javax.net.ssl.HostnameVerifier() {
	//
	// public boolean verify(String hostname, javax.net.ssl.SSLSession
	// sslSession) {
	// if (hostname.equals("localhost")) {
	// return true;
	// }
	// return false;
	// }
	// });
	// }

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, IOException {
		try {
			AddServiceImplServiceLocator locator = new AddServiceImplServiceLocator();
			URL wsdlURL = new URL(locator.getAddServiceImplAddress());
			AddServiceImpl service = locator.getAddServiceImpl(wsdlURL);

			// If tomcat is configured for https then wsdlURL value is as under
			// String httpsURL =
			// "https://localhost:8443/JP_WS/services/AddServiceImpl?wsdl";
			System.setProperty("javax.net.ssl.trustStore", "D:\\certi\\cacerts.jks");
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
			HandlerInfo info = new HandlerInfo();
			info.setHandlerClass(XMLSignatureHandler.class);
			handlerChain.add(info);

			NumRequest numRequest = new NumRequest();
			numRequest.setNum1(9);
			numRequest.setNum2(10);
			NumResponse numResponse = service.doAdd(numRequest);
			System.out.println("Answer is :: " + numResponse.getNumResult());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}