/*
 * File: LoggingAspect.java
 * Date: 02-Jul-2013
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
package com.jp.spring.aop.basic;

import java.util.Arrays;

import javax.persistence.criteria.Join;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author dimit.chadha
 */
@Component
@Aspect
@Order(2)
public class LoggingAspect {
	Logger log = Logger.getLogger(getClass());
	
//	 @Around("execution( * com.jp.spring.aop.basic..*.* (..) )")
//	    public Object callDurationAdvice(ProceedingJoinPoint pjp) throws Throwable {
//	        Signature signature = pjp.getSignature();
//	        Object[] args = pjp.getArgs();
//	        String argList = Arrays.toString(args);
//	        System.out.println(signature.getDeclaringTypeName() +
//	                "." + signature.getName() + "(" + argList + ") started");
//	        long s = System.nanoTime();
//	        Object proceed = pjp.proceed(args);
//	        long e = System.nanoTime();
//	        System.out.println(signature.getDeclaringTypeName() +
//	                "." + signature.getName() + "(" + argList + ") ended after " +
//	                ((double)(e-s)/1000000) + " ms");
//	        return proceed;
//	    }
	
	@Around("execution(* com.jp.spring.aop.basic..*.*(..))")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable{
       // final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName());
        Object retVal = null;

        try {
            StringBuffer startMessageStringBuffer = new StringBuffer();

            startMessageStringBuffer.append("Start method ");
            startMessageStringBuffer.append(joinPoint.getSignature().getName());
            startMessageStringBuffer.append("(");

            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                startMessageStringBuffer.append(args[i]).append(",");
            }
            if (args.length > 0) {
                startMessageStringBuffer.deleteCharAt(startMessageStringBuffer.length() - 1);
            }

            startMessageStringBuffer.append(")");

           System.out.println(startMessageStringBuffer.toString());

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            retVal = joinPoint.proceed();

            stopWatch.stop();

            StringBuffer endMessageStringBuffer = new StringBuffer();
            endMessageStringBuffer.append("Finish method ");
            endMessageStringBuffer.append(joinPoint.getSignature().getName());
            endMessageStringBuffer.append("(..); execution time: ");
            endMessageStringBuffer.append(stopWatch.getTotalTimeMillis());
            endMessageStringBuffer.append(" ms;");

            System.out.println(endMessageStringBuffer.toString());
        } catch (Throwable ex) {
            StringBuffer errorMessageStringBuffer = new StringBuffer();

             // Create error message 
             System.out.println(errorMessageStringBuffer.toString());

            throw ex;
        }

        return retVal;
    }
	
	
//	Logger logger = Logger.getLogger(LoggingAspect.class);

//	@Before("execution(public String com.jp.spring.aop.basic.TriangleModel.getName())")
//	public void LoggingAdvice() {
//		System.out.println("Advice Run. Get Method is called");
//	}

//	 // @Before("execution( public  * get*())")
//	 @Before("execution( * com.jp.spring.aop.basic.*.get*(..))")
//	 public void LoggingAdviceCommon() {
//	 System.out.println("Advice Run for all getters in Applications");
//	 }

	// one point - multiple advice.. do not repeat configuration rather define
	// point

//	@Pointcut("execution( * com.jp.spring.aop.basic.*.get*(..))")
//	public void allGettters() {
//		// dumpy methods
//	}
//
//	@Before("allGettters()")
//	public void LoggingAdviceForAll() {
//		System.out.println("Advice Run for all getters in Applications");
//	}
//
//	@Before("allGettters()")
//	public void LoggingAdviceForAll2() {
//		System.out.println("Advice Run2 for all getters in Applications");
//	}
//
//	@Pointcut("execution(*com.jp.spring.aop.basic..*.*(..*))")
	//@Pointcut("within(*com.jp.spring.aop.basic..*)")
	//@Pointcut("execution(public * *(..))")
//	@Pointcut("within(*com.jp.spring.aop.basic..*.*(..*))")
//	public void allMethods() {
//	}

//	@Pointcut("execution(* com.jp.spring.aop.basic.*.*(..))")
//public void allMethods2(JoinPoint joinPoint) {
//}
//
//	@After("allMethods()")
//	public void LoggingAdviceForAfter() {
//		System.out.println("After Advice Run for all methods");
//	}
	
	
	
	
//	@Around("execution(* com.jp.spring.aop.basic.*.*(..))")
//	public Object logBefore(ProceedingJoinPoint  joinPoint) throws Throwable{
//		System.out.println("logBefore() is running!");
//		Object clazz = joinPoint.getTarget().getClass().getSimpleName();
//		String methodName = joinPoint.getSignature().getName();
//		System.out.println("Entering to Class " + clazz + " With Method Name " + methodName);
//		System.out.println("############");
//		System.out.println("hijacked : " + joinPoint.getSignature().getName());
//		System.out.println(joinPoint.toShortString());
//		System.out.println("aa " + joinPoint.toString());
//		System.out.println(joinPoint.getTarget());
//		System.out.println("The method " + joinPoint.getSignature().getName()+ "() with parameters " + Arrays.toString(joinPoint.getArgs()));
//		System.out.println("The method " + joinPoint.getTarget().getClass().getName());
//		//System.out.println(joinPoint.getSourceLocation().getClass());
////		try {
////
////			Logger logger = Logger.getLogger(invocation.getMethod()
////
////			.getDeclaringClass().getName());
////
////			logger.info(invocation.getMethod().getName());
////
////			result = invocation.proceed();
////
////			} catch (InvocationTargetException e) {
//		Object[] args = joinPoint.getArgs();
//		 Object proceed = joinPoint.proceed(args);
//		System.out.println("******");
//		return proceed;
//	}
	
//	@After("allMethods()")
//	public void logAfter(JoinPoint joinPoint){
//		System.out.println("logAfter() is running!");
//		System.out.println("hijacked : " + joinPoint.getSignature().getName());
//		System.out.println(joinPoint.toLongString());
//		System.out.println(joinPoint.toString());
//		System.out.println(joinPoint.getTarget());
//		System.out.println("******");
//	}
//	
//	@AfterThrowing(pointcut="allMethods()",throwing="error")
//	public void logException(JoinPoint jointPoint,Throwable error){
//		System.out.println("Exception thrown :" + jointPoint.getSignature().getDeclaringType());
//		System.out.println("Error is "+error.getStackTrace()[1]);
//	}
	

//	@Before("allMethods()")
//	public void LoggingAdviceJointPoint(JoinPoint joinPoint) {
//		System.out.println("Before Advice Run for all methods");
//		System.out.println(joinPoint.getSignature());
//		System.out.println(joinPoint.toLongString());
//		System.out.println(joinPoint.toString());
//		System.out.println(joinPoint.getTarget());
//	}
	
	
	
	
	
//  /**
//  * Log method exit after returning.
//  *
//  * @param joinPoint
//  */
// @AfterReturning(pointcut = "loggingOperation()", returning = "result")
// public void logExitAfterReturn(final JoinPoint joinPoint, Object result) {
//     log("Exiting method (after return) "
//             + joinPoint.getSignature().getName() + "." + result, args);
// }
//
// @AfterThrowing(pointcut = "loggingOperation()", throwing = "error")
// public void logException(JoinPoint jointPoint, Throwable error) {
//     System.out.println("Exception thrown :" + jointPoint.getSignature().getDeclaringType());
//     System.out.println("Error is " + error.getStackTrace()[1]);
// }

// @Around("execution(* *.*(..))")
//@Order(4)
//public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable
//{
//log.info("The method " + joinPoint.getSignature().getName()+ "() begins with " + Arrays.toString(joinPoint.getArgs()));
//try
//{
//Object result = joinPoint.proceed();
//log.info("The method " + joinPoint.getSignature().getName()+ "() ends with " + result);
//return result;
//} catch (IllegalArgumentException e)
//{
//log.error("Illegal argument "+ Arrays.toString(joinPoint.getArgs()) + " in "+ joinPoint.getSignature().getName() + "()");
//throw e;
//}     
//}
// @Around("execution(com.*)")
//public void logAround(JoinPoint pjp) throws Throwable {
//long start = System.currentTimeMillis();
//Object clazz = pjp.getTarget().getClass().getName();
//String methodName = pjp.getSignature().getName();
//log.info("***************************************** Start **************************************");
//log.info("Entering to Class " + clazz + " With Method Name " + methodName);
//Object output = pjp.getTarget().getClass().getName();
//log.info("Method Excecution Completed " + methodName + " in Calss " + clazz);
//long elapsedTime = System.currentTimeMillis() - start;
//log.info("Method execution time for method " + methodName + " in Calss " + clazz + ":" + elapsedTime + " milliseconds.");
//log.info("***************************************** End **************************************");
//}
//
//@Before("execution(com.*)")
//public void logBefore(JoinPoint joinpoint) {
//Object clazz = joinpoint.getTarget().getClass().getName();
//String methodName = joinpoint.getSignature().getName();
//log.info("Entering to Class " + clazz + " With Method Name " + methodName);
//if (log.isDebugEnabled()) {
//Object[] obj = joinpoint.getArgs();
//for (Object o : obj)
//log.info("Parameter Name..." + o != null ? o.toString() : "");
//
//}
//}
//@After("execution(com.*)")
//public void logAfter(JoinPoint joinpoint) {
//Object clazz = joinpoint.getTarget().getClass().getName();
//String methodName = joinpoint.getSignature().getName();
//log.info("After Entring to Method " + methodName+ " in Calss " + clazz);
//
//}
//@AfterReturning("execution(com.*)")
//public void logAfterReturning(JoinPoint joinpoint) {
//Object clazz = joinpoint.getTarget().getClass().getName();
//String methodName = joinpoint.getSignature().getName();
//log.info("After Returning From Method " + methodName + " in Calss " + clazz);
//
//}
//@AfterThrowing(value = "execution(com.*)", throwing = "ex")
//public void logAfterThrowing(JoinPoint joinpoint, Exception ex) {
//Object clazz = joinpoint.getTarget().getClass().getName();
//String methodName = joinpoint.getSignature().getName();
//log.info("After Throwing From Method " + methodName + " in Calss " + clazz);
//log.error("After Throwing From Method " + methodName + " in Calss " + clazz, ex);
//ex.printStackTrace();
//}
// @Around("execution(* com.foo.bar..*.*(..))")
//	public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//			StopWatch stopWatch = new StopWatch();
//			stopWatch.start();
//			Object retVal = joinPoint.proceed();
//			stopWatch.stop();
//			StringBuffer logMessage = new StringBuffer();
//			logMessage.append(joinPoint.getTarget().getClass().getName());
//			logMessage.append(".");
//			logMessage.append(joinPoint.getSignature().getName());
//			logMessage.append("(");
//			// append args
//			Object[] args = joinPoint.getArgs();
//			for (int i = 0; i < args.length; i++) {
//				logMessage.append(args[i]).append(",");
//			}
//			if (args.length > 0) {
//				logMessage.deleteCharAt(logMessage.length() - 1);
//			}
//			logMessage.append(")");
//			logMessage.append(" execution time: ");
//			logMessage.append(stopWatch.getTotalTimeMillis());
//			logMessage.append(" ms");
//			log.info(logMessage.toString());
//			return retVal;
//	}
}
