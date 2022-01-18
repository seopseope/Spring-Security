package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.spring.controller.login.LoginFailHandle;
import com.spring.security.CustomAuthenticationProvider;
import com.spring.security.CustomUserDetailsService;

/*
 * 등록순서 - 1번쨰
 * xml설정에서 springSecurityFilterChain 등록 하는 부분을 Java 클래스로 설정한 부분이다.
 * WebSecurityConfigurerAdapter 상속하고 @EnableWebSecurity 명시해주면 자동으로 springSecurityFilterChain 포함되어 진다.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	// 인증 매커니즘 구성
	/*
	 * httpBasic -> 사용자 인증 방법으로 HTTP Basic Authentication 방법을 사용한다는 뜻
	 * authorizeRequests -> Security를 처리 할 때 HttpServletRequest을 이용한다는 뜻
	 * antMatchers -> 특정 경로를 지정할 떄 사용을 한다.
	 * access -> 특정 경로에 들어가기 위해 허용되는 조건 같은 것이다.
	 * formLogin -> form 형태의 로그인 방식을 사용하겠다는 뜻
	 * loginPage -> 로그인 페이지 경로를 명시
	 * usernameParameter -> 로그인 페이지의 아이디 Input의 name을 명시
	 * passwordParameter -> 로그인 페이지의 비밀번호 Input의 name을 명시
	 * loginProcessingUrl -> 사용자 아이디와 비밀번호를 제출할 URL을 뜻함
	 * permitAll -> 인증없이 누구나 로그인 페이지 접속 가능하다.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/member/**").hasAnyRole("MEMBER", "ADMIN")
				.antMatchers("/user/**").hasAnyRole("USER", "MEMBER", "ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login/loginPage.do")
				.usernameParameter("loginId")
				.passwordParameter("loginPwd")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/")
				.failureHandler(failHandler())
				.permitAll()
				.and()
			.exceptionHandling()
				.accessDeniedPage("/login/access_defind_page.do")
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.deleteCookies("JSESSIONID", "remember-me")
				.and()
			.rememberMe()
				.key("randomToken")
				.tokenValiditySeconds(60000)
				.userDetailsService(customUserDetailsService())
				.and()
			.csrf().disable()
			.httpBasic();
	}
	
	/*
	 * 사용자의 아이디와 비밀번호를 확인하여 인증 인가 확인 하는 부분이다.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService());
		auth.authenticationProvider(customAuthenticationProvider());
//		auth.inMemoryAuthentication()
//			.withUser("member").password("member").roles("MEMBER")
//			.and()
//			.withUser("user").password("user").roles("USER")
//			.and()
//			.withUser("admin").password("admin").roles("ADMIN");
	}
	
	@Bean
	public AuthenticationFailureHandler failHandler() {
		return new LoginFailHandle();
	}
	
	@Bean
	public UserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public AuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider();
	}
}
