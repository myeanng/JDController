package com.test.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.dao.IMysql;
import com.test.dao.impl.Mysql;
import com.test.service.IMysqlService;
import com.test.service.impl.MysqlService;

public class JDBCMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// System.out.println("mysqlService--------------------------------------------------");
		// IMysqlService mysqlService = (IMysqlService)
		// context.getBean("mysqlServiceProxy");
		// mysqlService.find();
		System.out.println("mysqlService2--------------------------------------------------");
		IMysqlService mysqlService2 = (IMysqlService) context.getBean("mysqlService");
		mysqlService2.find();
		//System.out.println("mysql--------------------------------------------------");
		//IMysql mysql = (Mysql) context.getBean("mysql");
		//mysql.find();
	}

}
