package dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dao.IMysql;

@Repository
public class Mysql implements IMysql{
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	@Override
	public void find() {
		String sql = "select * from user";
        System.out.println(jdbcTemplate.queryForList(sql).toString());
	}

}
