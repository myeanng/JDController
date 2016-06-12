package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.service.IMysqlService;

/**
 * 表示控制层Test类，并使用注解@Controller – 表示在表示层控制器组件。
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "mysql")
public class MySQLController {

	/**
	 * 注入业务层服务组件
	 */
	@Autowired
	private IMysqlService mysqlService;

	@RequestMapping(value = "find", method = RequestMethod.GET)
	public void find() {
		mysqlService.find();
	}
}
