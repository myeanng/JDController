package com.test.global.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * 之前通知
 * 
 * @author Administrator
 *
 */
public class BeforeAdvicePrint implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("之前通知");
		System.out.println("请求方法：" + method.getName());
		System.out.println("args:" + args.toString());
		System.out.println("target:" + target.toString());
	}

}
