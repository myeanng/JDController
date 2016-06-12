package com.test.global.advice;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * 环绕通知
 * 
 * @author Administrator
 *
 */
public class MethodAdvicePrint implements MethodInterceptor {

	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		System.out.println("环绕通知");
		System.out.println("Method name : " + arg1.getName());
		System.out.println("Method arguments : " + arg2.toString());
		// same with MethodBeforeAdvice
		System.out.println("HijackAroundMethod : Before method hijacked!");
		try {
			// proceed to original method call
			Object result = arg0;
			// same with AfterReturningAdvice
			System.out.println("HijackAroundMethod : Before after hijacked!");
			return result;
		} catch (Exception e) {
			// same with ThrowsAdvice
			System.out.println("HijackAroundMethod : Throw exception hijacked!");
			throw e;
		}
	}

}
