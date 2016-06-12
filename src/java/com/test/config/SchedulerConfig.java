package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.bean.impl.Scheduler;

@Configuration
public class SchedulerConfig {

	@Bean
	public Scheduler scheduler() {
		return new Scheduler("Scheduler!");
	}
}
