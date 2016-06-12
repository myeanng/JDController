package com.test.global.advice;

import org.springframework.aop.ThrowsAdvice;

/**
 * 抛出后通知
 * 
 * @author Administrator
 *
 */
public class ThrowsAdvicePrint implements ThrowsAdvice {
	/**
	 * 创建一个afterThrowing方法拦截抛出：Exception异常。
	 * 
	 * @param e
	 * @throws Throwable
	 */
	public void afterThrowing(Exception e) throws Throwable {
		System.out.println("HijackThrowException : Throw exception hijacked!");
	}
}
