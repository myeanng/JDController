package com.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.IMysql;
import com.test.service.IMysqlService;

/**
 * 业务层Test类，并使用注解@Service – 表示在业务层服务组件。
 * 
 * @author Administrator
 *
 */
@Service
public class MysqlService implements IMysqlService {
	

	/**
	 * 注入持久层DAO组件
	 */
	@Autowired
	private IMysql mysql;

	@Override
	public void find() {
		mysql.find();
	}

}
