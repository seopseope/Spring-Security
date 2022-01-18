package com.spring.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/*
 *	Security 설정 - 2번쨰
 *	WebSecurityConfig 클래스에서 WebSecurityConfigurerAdapter 상속받아 springSecurityFilterChain이 포함되어 져 있는데 이걸 등록 할려면 AbstractSecurityWebApplicationInitializer를 상속받는 클래스를 하나 만들면 된다.
 *	
 *	Security 설정 - 3번쨰
 *	Spring MVC를 설정하는 SpringStarter 클래스의 Root Context를 설정하는 부분에 WebSecurityConfig.class를 설정해주면 된다.(그럼 기본적인 Security 설정 끝)
 * 
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{
	
}
