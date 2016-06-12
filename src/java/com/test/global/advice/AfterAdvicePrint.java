package com.test.global.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

/**
 * 返回后通知
 * 
 * @author Administrator
 *
 */
public class AfterAdvicePrint implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("返回后通知");
		System.out.println("请求方法：" + method.getName());
		System.out.println("返回参数：" + returnValue);
		System.out.println("args:" + args.toString());
		System.out.println("target:" + target.toString());

	}

}
