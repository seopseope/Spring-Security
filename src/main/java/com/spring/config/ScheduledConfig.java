package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
public class ScheduledConfig {

	@Bean
	public TaskScheduler scheduler() {
		ThreadPoolTaskScheduler secheduler = new ThreadPoolTaskScheduler();
		secheduler.setPoolSize(4);
		
		return secheduler;
	}
}
