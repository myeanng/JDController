package hello;

import bean.IBean;

public class HelloSpring {
	private IBean customer;
	private IBean scheduler;
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public void printHello() {
		System.out.println("Spring 4 : Hello ! " + name);
	}

	public HelloSpring(String name) {
		super();
		this.name = name;
	}

	/**
	 * 已xml文件形式配置Spring时，无默认构造方法时，会报注入错误
	 */
	public HelloSpring() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 用于构造注入
	 * 
	 * @param customer
	 */
	public HelloSpring(IBean customer) {
		super();
		this.customer = customer;
	}

	public IBean getCustomer() {
		return customer;
	}

	public void setCustomer(IBean customer) {
		this.customer = customer;
	}

	public IBean getScheduler() {
		return scheduler;
	}

	/**
	 * 用于Set注入
	 * 
	 * @param scheduler
	 */
	public void setScheduler(IBean scheduler) {
		this.scheduler = scheduler;
	}

	public String getName() {
		return name;
	}

}
