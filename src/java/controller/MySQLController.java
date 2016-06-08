package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import service.IMysqlService;

/**
 * 表示控制层Test类，并使用注解@Controller – 表示在表示层控制器组件。
 * 
 * @author Administrator
 *
 */
@Controller
public class MySQLController {

	/**
	 * 注入业务层服务组件
	 */
	@Autowired
	private IMysqlService mysqlService;

	public void find() {
		mysqlService.find();
	}
}
