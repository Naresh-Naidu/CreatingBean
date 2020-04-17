package com.creatingBean.CreatingBean.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.creatingBean.CreatingBean.module.Demo;

@Configuration
public class AppConfig {

	@Bean
	public Demo getDemo() {
		return new Demo();
	}
}
