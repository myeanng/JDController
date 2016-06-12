package com.test.global.aspect;

import java.lang.reflect.Field;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class GlobalLogPrint2 {

	public void globalLog() {

	}

	public void logAfter(JoinPoint joinPoint, Object returnValue) {
		System.out.println("logAfter2() is running!");
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
