package com.spring.config;

import java.io.Reader;
import java.util.Properties;

import javax.sql.DataSource;
import org.apache.ibatis.io.Resources;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 * Database 설정
 */
@Configuration
@EnableTransactionManagement
public class ContextDataSource {

	/*
	 * root-context.xml의 dataSource 부분을 Java class로 설정
	 * 데이터소스 등록
	 */
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		Properties props = new Properties();
		try {
			Reader reader = Resources.getResourceAsReader("/jdbc.properties");
			props.load(reader);
			
			dataSource.setDriverClassName(props.getProperty("jdbc.driverClassName"));
			dataSource.setUrl(props.getProperty("jdbc.url"));
			dataSource.setUsername(props.getProperty("jdbc.username"));
			dataSource.setPassword(props.getProperty("jdbc.password"));
			dataSource.setDefaultAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dataSource;
	}
	
	/*
	 * 트랜잭션 매니저 등록
	 */
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
