/**
 * 
 */
package com.jp.service.client.jaxws.handler;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

/**
 * Handler Class implementing the Default Handler Resolver Of JAX-WS
 * 
 * @author dchadha
 */
public class XMLSignHandlerResolver implements HandlerResolver {

	// private static ConfigurationReader spmlConfig;
	// private static String keyProperty;

	public XMLSignHandlerResolver() {
		// this.spmlConfig = spmlConfig;
		// this.keyProperty=keyProperty;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * javax.xml.ws.handler.HandlerResolver#getHandlerChain(javax.xml.ws.handler
	 * .PortInfo)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Handler> getHandlerChain(PortInfo portInfo) {
		List<Handler> handlerChain = new ArrayList<Handler>();
		XMLSignatureHandler hh = new XMLSignatureHandler();
		handlerChain.add(hh);
		return handlerChain;
	}

}
