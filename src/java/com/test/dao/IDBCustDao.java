package com.test.dao;

import java.util.List;

import com.test.model.dbEntity.DBCust;

public interface IDBCustDao {
	List<DBCust> find();
}
