package com.test.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.test.dao.ICustDao;
import com.test.model.dbEntity.Cust;

@Repository
public class CustDaoImpl implements ICustDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 单结果集查询
	 */
	@Override
	public Cust findById(int id) {
		String sql = "select * from cust where id=?";
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Cust cust = (Cust) jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper(Cust.class));
		return cust;
	}

	/**
	 * 多结果集查询
	 */
	@Override
	public List<Cust> find() {
		String sql = "select * from cust";
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Cust> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Cust.class));
		return list;
	}

	/**
	 * 数据集插入
	 */
	@Override
	public void insertBatch(final List<Cust> custList) {
		String sql = "INSERT INTO CUST (NAME, AGE, ADDRESS,SEX, JOB, REMARK) VALUES (?, ?, ?, ?, ?, ?)";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Cust cust = custList.get(i);
				ps.setString(1, cust.getName());
				ps.setInt(2, cust.getAge());
				ps.setString(3, cust.getAddress());
				ps.setString(4, cust.getSex());
				ps.setString(5, cust.getJob());
				ps.setString(6, cust.getRemark());
			}

			@Override
			public int getBatchSize() {
				return custList.size();
			}

		});

	}

	/**
	 * 多sql更新
	 */
	@Override
	public void updateBatch(String[] sqls) {
		// TODO Auto-generated method stub

	}

}
