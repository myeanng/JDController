package com.test.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.test.dao.IDBCustDao;

@Repository(value="dBCustDaoImpl")
public class DBCustDaoImpl implements IDBCustDao {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public List<com.test.model.dbEntity.DBCust> find() {
		Query query = em.createQuery("SELECT t FROM DBCust t");
		@SuppressWarnings("unchecked")
		List<com.test.model.dbEntity.DBCust> list = (List<com.test.model.dbEntity.DBCust>) query.getResultList();
		return list;
	}

}
