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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
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
			String httpsURL = "https://localhost:8443/JP_WS/services/AddServiceImpl?wsdl";
			System.setProperty("javax.net.ssl.trustStore", "D:\\certi\\cacerts.jks");
			System.setProperty("javax.net.ssl.trustStorePassword", "dimit123");
			System.setProperty("javax.net.ssl.keyStore", "D:\\certi\\Dimit.jks");
			System.setProperty("javax.net.ssl.keyStoreType", "JKS");
			System.setProperty("javax.net.ssl.keyStorePassword", "dimit123");
			
			 HandlerRegistry handleRegistry = locator.getHandlerRegistry();
			QName qName = new QName(locator.getAddServiceImplAddress(),
			 "AddServiceImpl");
			//
			// // Calling Request Handler
			 List handlerChain = handleRegistry.getHandlerChain(qName);
			 HandlerInfo info = new HandlerInfo();
			 info.setHandlerClass(XMLSignatureHandler.class);
			 handlerChain.add(info);

			// Calling Request Handler with jaxws client
			// AddServiceImplService servicejaxws = new
			// AddServiceImplService(wsdlfileurl, qname);
			// servicejaxws.setHandlerResolver(new XMLSignHandlerResolver());

			
			// Create a context that doesn't check certificates.
            SSLContext ssl_ctx = SSLContext.getInstance("TLS");
            TrustManager[ ] trust_mgr = get_trust_mgr();
            ssl_ctx.init(null,                // key manager
                         trust_mgr,           // trust manager
                         new SecureRandom()); // random number generator
            HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
 
            URL url = new URL(httpsURL);
	    HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
 
	    // Guard against "bad hostname" errors during handshake.
            con.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String host, SSLSession sess) {
                    if (host.equals("localhost")) return true;
                    else return false;
                }
            });
 
	    //dumpl all cert info
	    print_https_cert(con);
 
	    //dump all the content
	    print_content(con);
			
			NumRequest numRequest = new NumRequest();
			numRequest.setNum1(9);
			numRequest.setNum2(10);
			NumResponse numResponse = service.doAdd(numRequest);
			System.out.println("Answer is :: " + numResponse.getNumResult());

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

	private static void print_https_cert(HttpsURLConnection con) {
		if (con != null) {

			try {

				System.out.println("Response Code : " + con.getResponseCode());
				System.out.println("Cipher Suite : " + con.getCipherSuite());
				System.out.println("\n");

				Certificate[] certs = con.getServerCertificates();
				for (Certificate cert : certs) {
					System.out.println("Cert Type : " + cert.getType());
					System.out.println("Cert Hash Code : " + cert.hashCode());
					System.out.println("Cert Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());
					System.out.println("Cert Public Key Format : " + cert.getPublicKey().getFormat());
					System.out.println("\n");
				}

			} catch (SSLPeerUnverifiedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void print_content(HttpsURLConnection con) {
		if (con != null) {

			try {

				System.out.println("****** Content of the URL ********");

				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

				String input;

				while ((input = br.readLine()) != null) {
					System.out.println(input);
				}
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	 private static TrustManager[ ] get_trust_mgr() {
	     TrustManager[ ] certs = new TrustManager[ ] {
	        new X509TrustManager() {
	           public X509Certificate[ ] getAcceptedIssuers() { return null; }
	           public void checkClientTrusted(X509Certificate[ ] certs, String t) { }
	           public void checkServerTrusted(X509Certificate[ ] certs, String t) { }
	         }
	      };
	      return certs;
	  }
}