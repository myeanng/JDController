package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bean.impl.Customer;
import bean.impl.Scheduler;
import config.AppConfig;
import hello.HelloSpring;

public class Main {

	public static void main(String[] args) {
		/**
		 * 已xml文件形式配置Spring时，无默认构造方法时，会报注入错误，javaConfig形式则正常；
		 */
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		/**
		 * javaConfig加载方式
		 */
		// ApplicationContext context = new
		// AnnotationConfigApplicationContext(AppConfig.class);
		HelloSpring obj = (HelloSpring) context.getBean("helloSpring");
		Customer cust = (Customer) context.getBean("customer");
		Scheduler sche = (Scheduler) context.getBean("scheduler");
		obj.printHello();
		cust.printHello();
		sche.printHello();
		
		obj.getCustomer().printHello();
		obj.getScheduler().printHello();
	}

}
