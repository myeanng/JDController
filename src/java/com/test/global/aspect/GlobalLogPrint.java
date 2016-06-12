package com.test.global.aspect;

import java.lang.reflect.Field;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 常见AspectJ的注解：
 * 
 * @Before – 方法执行前运行
 * @After – 运行在方法返回结果后
 * @AfterReturning – 运行在方法返回一个结果后，在拦截器返回结果。
 * @AfterThrowing – 运行方法在抛出异常后，
 * @Around – 围绕方法执行运行，结合以上这三个通知。 注意 Spring AOP 中没有 AspectJ 支持
 * @author Administrator
 *
 */
@Aspect
@Component
public class GlobalLogPrint {

	@Pointcut("execution(* com.test.dao..*.*(..))" + " || execution(* com.test.service..*.*(..))"
			+ " || execution(* com.test.controller..*.*(..))")
	public void globalLog() {

	}

	/**
	 * 产生该错误的原因是我们在使用AfterReturning注解的时候，没有定义返回的参数，但是拦截的方法中缺需要传入一个参数，比如下面的“
	 * returnValue”参数。如果AfterReturing注解拦截的方法需要接收参数，需要在AfterReturning中声明。
	 * 
	 * @param joinPoint
	 * @param returnValue
	 */
	@AfterReturning(pointcut = "globalLog()", returning = "returnValue")
	public void logBefore(JoinPoint joinPoint, Object returnValue) {
		System.out.println("logBefore() is running!");
		try {
			System.out.println("请求时间:" + new Date());
			System.out.println("请求方法:" + (joinPoint.getSignature().getDeclaringTypeName() + "."
					+ joinPoint.getSignature().getName() + "()"));

			StringBuilder param = new StringBuilder();
			if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
				for (int i = 0; i < joinPoint.getArgs().length; i++) {
					Object pObject = joinPoint.getArgs()[i];
					param.append(getFieldsValues(pObject));
				}
			}
			System.out.println("请求参数:" + param);
			System.out.println("返回值为:" + getFieldsValues(returnValue));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * only executed when exception
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "globalLog()", throwing = "e")
	public void afterThrowing(JoinPoint joinPoint, Exception e) {
		try {
			System.out.println("请求时间:" + new Date());
			System.out.println("请求方法:" + (joinPoint.getSignature().getDeclaringTypeName() + "."
					+ joinPoint.getSignature().getName() + "()"));

			StringBuilder param = new StringBuilder();
			if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
				for (int i = 0; i < joinPoint.getArgs().length; i++) {
					Object pObject = joinPoint.getArgs()[i];
					param.append(getFieldsValues(pObject));
				}
			}
			System.out.println("请求参数:" + param);
			if (e != null) {
				System.out.println("出现异常:" + e.getMessage());
			}
		} catch (Exception ex) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * get the value of each fields of the object
	 * 
	 * @param pObject
	 *            Object
	 * @return String
	 * @throws Exception
	 *             Exception
	 */
	private String getFieldsValues(Object pObject) throws Exception {
		StringBuilder param = new StringBuilder();
		if (pObject != null) {
			if (pObject instanceof String || pObject instanceof Integer || pObject instanceof Long
					|| pObject instanceof JSONArray || pObject instanceof JSONObject) {
				param.append(pObject.toString() + "; ");
			} else {
				Class<? extends Object> pClass = (Class<? extends Object>) pObject.getClass();

				// just for BaseOutDto, to get the error code and error message
				Class<? extends Object> superClass = (Class<? extends Object>) pClass.getSuperclass();
				if (superClass != null && superClass.getName().contains("BaseOutDto")) {
					Field[] fs = superClass.getDeclaredFields();
					for (int j = 0; j < fs.length; j++) {
						Field f = fs[j];
						f.setAccessible(true); // make the fields accessible
						param.append(f.getName() + ":" + f.get(pObject) + "; ");
					}
				}

				// get the value of the returned object
				Field[] fs = pClass.getDeclaredFields();
				for (int j = 0; j < fs.length; j++) {
					Field f = fs[j];
					f.setAccessible(true); // make the fields accessible
					param.append(f.getName() + ":" + f.get(pObject) + "; ");
				}
			}
		}
		return param.toString();
	}
}
