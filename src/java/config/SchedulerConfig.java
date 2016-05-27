package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bean.Scheduler;

@Configuration
public class SchedulerConfig {

	@Bean
	public Scheduler scheduler() {
		return new Scheduler("Scheduler!");
	}
}
