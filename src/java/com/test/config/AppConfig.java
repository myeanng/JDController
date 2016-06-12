package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.test.hello.HelloSpring;

/**
 * javaConfig类； 代替applicationContext.xml配置文件
 * 
 * @author Administrator
 *
 */
@Configuration
@Import({ CustomerConfig.class, SchedulerConfig.class })
public class AppConfig {
	/**
	 * 注入HelloSpring java类
	 */
	@Bean
	public HelloSpring helloSpring() {
		return new HelloSpring("word!");
	}
}
