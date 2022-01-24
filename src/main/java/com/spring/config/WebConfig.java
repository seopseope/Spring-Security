package com.spring.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.spring.exception.UncheckException;

/*
 * Web Context 설정 파일
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.spring.controller.*", "com.spring.exception.*"})
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
	
	/*
	 * Exception Resolver 설정 -> 예외가 발생하였을때 특정 에러에 대한 특정 에러페이지를 보여주게 설정하고, 또한 예외에 대해서 원하는 HTTP 응답코드를 지정할 수 있다.
	 */
	@Bean
	public SimpleMappingExceptionResolver getExceptionResolver() {
		SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
		
		// 지정되지 않은 예외에 대한 기본 에러페이지
		smer.setDefaultErrorView("/error/error");
		// 상태코드 맵핑이 없는 예외를 위한 기본 상태값 입니다.
		smer.setDefaultStatusCode(200);
		// 기본값이 "exception"이다. 예외 모듈 속성의 키값
		smer.setExceptionAttribute("exception");
		// 하나 또는 그 이상의 예외를 리졸버에서 제외. 제외된 예외는 web.xml에서 지정된 값이 적용
		smer.setExcludedExceptions(UncheckException.class);
		
		// 예외 클래스에 대해 에러 페이지를 지정
		Properties props = new Properties();
		props.setProperty("com.spring.exception.DatabaseException", "/error/databaseError");
		props.setProperty("com.spring.exception.SecurityException", "/error/securityError");
		props.setProperty("com.spring.exception.BusinessException", "/error/businessError");
		props.setProperty("com.spring.exception.AjaxException", "/error/ajaxError");
		smer.setExceptionMappings(props);
		
		// 에러페이지에 상태코드를 지정
		Properties errorCode = new Properties();
		errorCode.setProperty("/error/databaseError", "500");
		errorCode.setProperty("/error/securityError", "403");
		errorCode.setProperty("/error/businessError", "200");
		errorCode.setProperty("/error/ajaxError", "200");
		smer.setStatusCodes(errorCode);
		
		return smer;
	}
	
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addRedirectViewController("/", "/home.do");
//	}
}
