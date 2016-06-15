package com.test.model.dbEntity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the Cust database table.
 * 
 */
@Entity
@Table(name = "CUST")
@NamedQuery(name = "DBCust.findAll", query = "SELECT t FROM DBCust t")
public class DBCust implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 11, scale = 0)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "ENTITY_SEQ")
	// @SequenceGenerator(name = "ENTITY_SEQ", sequenceName = "ENTITY_SEQ",
	// allocationSize = 1)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private Integer age;

	@Column(name = "address")
	private String address;

	@Column(name = "sex")
	private String sex;

	@Column(name = "job")
	private String job;

	@Column(name = "remark")
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}