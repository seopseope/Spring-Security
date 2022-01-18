package com.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

// Root Context 설정 파일
@Configuration
@Import({
	 ContextDataSource.class
	,ContextSqlMapper.class
})
@ComponentScan(basePackages = {"com.spring.service.*"})
public class RootConfig {

}
