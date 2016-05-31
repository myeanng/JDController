package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.IMysql;
import dao.impl.Mysql;

public class JDBCMain {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IMysql mysql=(Mysql)context.getBean("mysql");
		mysql.find();
	}
	
}
