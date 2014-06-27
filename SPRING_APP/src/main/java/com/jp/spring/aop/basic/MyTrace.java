package com.jp.spring.aop.basic;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;

public class MyTrace extends CustomizableTraceInterceptor {
	 
	  protected void writeToLog(Log logger, String message, Throwable ex) {
	    if (ex != null) {
	     logger.info(message, ex);
	    } else {
	     logger.info(message);
	    }
	 }
	 
	 protected boolean isInterceptorEnabled(MethodInvocation invocation, Log logger) {
	   return true;
	 }
	}
