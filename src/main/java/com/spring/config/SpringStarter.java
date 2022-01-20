package com.spring.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.spring.config.RootConfig;
import com.spring.config.WebConfig;

/*
 * 스프링 프레임워크 시작 파일(Root 컨텍스트 설정, Web 컨텍스트 설정, *.do 확장자를 처리하는 서블릿 디스패처를 설정한다.)
 */
public class SpringStarter implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		//Root Context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootConfig.class, WebSecurityConfig.class, AopConfig.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		//Web Context
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(WebConfig.class);
		
		//Dispatcher Servlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		dispatcher.addMapping("*.do");
	}
}
