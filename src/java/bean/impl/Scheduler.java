package bean.impl;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import bean.IBean;

/**
 * 注释注入 并指定作用域为原型。 bean普通组件Test类@Component – 指示自动扫描组件。
 * 
 * @author Administrator
 *
 */
@Component
@Scope("prototype")
public class Scheduler implements IBean {
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 在Spring中，既可以实现 InitializingBean和DisposableBean接口或在bean配置文件中指定 init-method
	 * 和 destroy-method 在初始化和销毁回调函数。在这篇文章中，我们将介绍如何使用 @PostConstruct和@PreDestroy
	 * 注解来做同样的事情。 注：@PostConstruct和@PreDestroy 标注不属于 Spring，它是在J2EE库-
	 * common-annotations.jar。
	 * 默认情况下，Spring不会意识到@PostConstruct和@PreDestroy注解。要启用它，要么注册“
	 * CommonAnnotationBeanPostProcessor”，要么在bean配置文件的
	 * <context:annotation-config />‘ 指定，
	 */
	@PostConstruct
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
