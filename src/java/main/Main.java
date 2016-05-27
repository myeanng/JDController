package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import config.AppConfig;
import hello.HelloSpring;

public class Main {

	public static void main(String[] args) {
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("applicationContext.xml");

		/**
		 * javaConfig加载方式
		 */
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		HelloSpring obj = (HelloSpring) context.getBean("helloSpring");
		obj.printHello();
	}

}
