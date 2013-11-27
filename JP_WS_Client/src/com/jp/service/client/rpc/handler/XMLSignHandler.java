/*
 * File: XMLSignHandler.java Date: 19-Oct-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.service.client.rpc.handler;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.GenericHandler;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.axis.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author dchadha
 */
public class XMLSignHandler extends GenericHandler {

	@Override
	public QName[] getHeaders() {
		return null;
	}

	@Override
	public boolean handleRequest(MessageContext context) {

		// Initialize the library
		org.apache.xml.security.Init.init();

		SOAPMessageContext ctx = (SOAPMessageContext) context;

		// axis message
		Message message = (Message) ctx.getMessage();

		Document document = null;
		try {
			document = message.getSOAPEnvelope().getAsDocument();
		} catch (Exception e) {
			System.out.println("Document could not be obtained from the MessageContext");
			e.printStackTrace();
		}
		System.out.println("Unsigned Request");
		dumpDocument(document);
		
		
		
		
		
		
		return true;

	}

	private static void dumpDocument(Node root) {
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(root), new StreamResult(System.out));
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}
}
