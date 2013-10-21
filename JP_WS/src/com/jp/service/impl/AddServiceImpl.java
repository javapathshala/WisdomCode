/*
 * File: AddServiceImpl.java Date: 09-Oct-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.service.impl;

import javax.annotation.Resource;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.ws.WebServiceContext;

import com.jp.service.AddService;
import com.jp.service.request.NumRequest;
import com.jp.service.response.NumResponse;

/**
 * @author dchadha
 */
public class AddServiceImpl implements AddService {
	 @Resource
	    WebServiceContext wsctx;
	@Override
	public NumResponse doAdd(NumRequest numRequest) {
//		HandlerChain handlerChain = new HandlerChainImpl();
//		HandlerInfo info = new HandlerInfo();
//		info.setHandlerClass(WSServerHandler.class);
//		handlerChain.add(info);
	//	 MessageContext mctx = (MessageContext)wsctx.getMessageContext();
		int result = numRequest.getNum1() + numRequest.getNum2();
		NumResponse numResponse = new NumResponse();
		numResponse.setNumResult(result);
		return numResponse;
	}

}
