/*
 * File: EventDispatcher.java Date: Apr 30, 2014 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.eda;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 72010964
 */
public class EventDispatcher implements DynamicRouter<Event> {
	private Map<Class<? extends Event>, Handler> handlers;

	public EventDispatcher() {
		handlers = new HashMap<Class<? extends Event>, Handler>();
	}

	@Override
	public void dispatch(Event content) {
		handlers.get(content.getClass()).dispatch(content);

	}

	@Override
	public void registerChannel(Class<? extends Event> contentType, Channel<? extends Event> channel) {
		handlers.put(contentType, (Handler) channel);
	}

}
