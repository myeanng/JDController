package bean;

public class Customer {
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public void printHello() {
		System.out.println("Spring 4 : Hello ! " + name);
	}

	public Customer(String name) {
		super();
		this.name = name;
	}

	/**
	 * 已xml文件形式配置Spring时，无默认构造方法时，会报注入错误
	 */
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
