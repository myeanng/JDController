package com.test.dao;

import java.util.List;

import com.test.model.dbEntity.Cust;

public interface ICustDao {

	Cust findById(int id);

	List<Cust> find();

	void insertBatch(List<Cust> custList);

	void updateBatch(String[] sqls);
}
