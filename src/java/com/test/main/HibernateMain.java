package com.test.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.dao.IDBCustDao;

public class HibernateMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IDBCustDao cust = (IDBCustDao) context.getBean("dBCustDaoImpl");
		cust.find();
	}

}
