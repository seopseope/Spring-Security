package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*
 * Web Context 설정 파일
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.spring.controller.*"})
public class WebConfig implements WebMvcConfigurer{
	
	/*
	 * View Resolver 설정
	 */
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}
	
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addRedirectViewController("/", "/home.do");
//	}
}