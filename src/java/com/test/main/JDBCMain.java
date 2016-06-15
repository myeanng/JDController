package com.test.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.dao.ICustDao;
import com.test.dao.IMysql;
import com.test.dao.impl.Mysql;
import com.test.model.dbEntity.Cust;
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
		// System.out.println("mysql--------------------------------------------------");
		// IMysql mysql = (Mysql) context.getBean("mysql");
		// mysql.find();
		System.out.println("结果集查询：-----------------------------------------------------");
		ICustDao custDao = (ICustDao) context.getBean("custDaoImpl");
		Cust c1 = custDao.findById(6);
		List<Cust> c2Lsit = custDao.find();
		System.out.println(c1.toString());
		System.out.println(c2Lsit.toString());
		System.out.println("批量插入：-----------------------------------------------------");
		List<Cust> cs = new ArrayList<Cust>();
		Cust c2 = new Cust();
		c2.setName("污撸撸");
		c2.setAge(22);
		c2.setAddress("~~~~(>_<)~~~~");
		c2.setJob("职员");
		c2.setRemark("系那个过");
		c2.setSex("男");
		cs.add(c2);

		Cust c3 = new Cust();
		c3.setName("哇咔咔");
		c3.setAge(22);
		c3.setAddress("香港");
		c3.setSex("男");
		cs.add(c3);

		custDao.insertBatch(cs);

	}

}
