package com.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/*
 * AOP 활성화 설정 파일
 */

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.spring.*"})
public class AopConfig {

}
