package com.test.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.bean.impl.Customer;
import com.test.bean.impl.Scheduler;
import com.test.config.AppConfig;
import com.test.hello.HelloSpring;

public class Main {

	public static void main(String[] args) {
		/**
		 * 已xml文件形式配置Spring时，无默认构造方法时，会报注入错误，javaConfig形式则正常；
		 */
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		/**
		 * spring加载多个配置文件
		 */
		/*ApplicationContext context2 = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml", "spring-customer.xml", "spring-scheduler.xml" });*/

		/**
		 * javaConfig加载方式
		 */
		// ApplicationContext context = new
		// AnnotationConfigApplicationContext(AppConfig.class);
		HelloSpring obj = (HelloSpring) context.getBean("helloSpring");
		Customer cust = (Customer) context.getBean("customer");
		Scheduler sche = (Scheduler) context.getBean("scheduler");
		HelloSpring obj2 = (HelloSpring) context.getBean("helloSpring");
		Scheduler sche2 = (Scheduler) context.getBean("scheduler");
		Customer cust2 = (Customer) context.getBean("customer");
		obj.setName("set值，测试原型");
		obj.printHello();
		cust.setName("set值，测试原型");
		cust.printHello();
		sche.setName("set值，测试原型");
		sche.printHello();
		obj2.printHello();
		cust2.printHello();
		sche2.printHello();

		obj.getCustomer().printHello();
		obj.getScheduler().printHello();
	}

}
