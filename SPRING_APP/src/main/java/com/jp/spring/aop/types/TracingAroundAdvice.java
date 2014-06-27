package com.jp.spring.aop.types;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TracingAroundAdvice implements MethodInterceptor{

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		System.out.println("Method name : "
				+ methodInvocation.getMethod().getName());
		System.out.println("Method arguments : "
				+ Arrays.toString(methodInvocation.getArguments()));
 
		// same with MethodBeforeAdvice
		System.out.println("HijackAroundMethod : Before method hijacked!");
 
		try {
			// proceed to original method call
			Object result = methodInvocation.proceed();
 
			// same with AfterReturningAdvice
			System.out.println("HijackAroundMethod : Before after hijacked!");
 
			return result;
 
		} catch (IllegalArgumentException e) {
			// same with ThrowsAdvice
			System.out.println("HijackAroundMethod : Throw exception hijacked!");
			throw e;
		}
	}

//	public Object invoke(MethodInvocation arg0) throws Throwable {
//		System.out.println("Around Advice Called::");
//		String str=(String)arg0.getArguments()[0];
//		Object str1=arg0.proceed();
//		return str1;
//	}

}
