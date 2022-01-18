package com.spring.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.spring.service.*"})
public class ContextSqlMapper {

	@Autowired
	ApplicationContext applicationContext;
	
	/*
	 * root-context.xml의 sqlSessionFactory 설정 부분을 Java Class로 설정
	 */
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException{
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean(); 
		factoryBean.setDataSource(dataSource);
		factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config.xml"));
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/mappers/*.xml"));
		
		return factoryBean;
	}
	
	/*
	 * root-context.xml의 sqlSessionTemplate 부분을 Java Class로 설정
	 */
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
