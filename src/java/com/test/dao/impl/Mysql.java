package com.test.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.test.dao.IMysql;

/**
 * 持久层Test类，并使用注解@Repository – 表示在持久层DAO组件。
 * 
 * @author Administrator
 *
 */
@Repository
public class Mysql implements IMysql {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void find() {
		String sql = "select * from test_table_1";
		System.out.println(jdbcTemplate.queryForList(sql).toString());
	}

}
