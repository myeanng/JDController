package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bean.Customer;

@Configuration
public class CustomerConfig {
	/**
	 * 注入Customer java类
	 */
	@Bean
	public Customer customer() {
		return new Customer("Customer!");
	}
}
