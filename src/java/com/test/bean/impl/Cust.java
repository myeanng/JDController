package com.test.bean.impl;

public class Cust {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	String name;
	int age;
	boolean flag;
	String add;
	public Cust(String name, int age, boolean flag, String add) {
		super();
		this.name = name;
		this.age = age;
		this.flag = flag;
		this.add = add;
	}
	public Cust() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
