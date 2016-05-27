package bean.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import bean.IBean;

/**
 * 注释注入
 * 并指定作用域为原型
 * @author Administrator
 *
 */
@Service
@Scope("prototype")
public class Scheduler implements IBean {
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void printHello() {
		System.out.println("Spring 4 : Hello ! " + name);
	}

	public Scheduler(String name) {
		super();
		this.name = name;
	}

	/**
	 * 已xml文件形式配置Spring时，无默认构造方法时，会报注入错误
	 */
	public Scheduler() {
		super();
		// TODO Auto-generated constructor stub
	}

}
