package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

// 사용자가 입력한 아이디 및 비밀번호를 CustomUserDetailsService 클래스가 DB에서 가져온 사용자 정보와 비교하는 클래스이다.
@SuppressWarnings("unused")
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = (String)authentication.getPrincipal();
		String userPwd = (String)authentication.getCredentials();
		
		CustomUserDetails user = (CustomUserDetails)customUserDetailsService.loadUserByUsername(userId);
		System.out.println("userPwd >>> : " + userPwd);
		System.out.println("user.getPassword >>> : " + user.getPassword());
		if(!matchPassword(userPwd, user.getPassword())) {
			throw new BadCredentialsException(userId);
		}
		
		return new UsernamePasswordAuthenticationToken(userId, userPwd, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	private boolean matchPassword(String userPwd, String dbPwd) {
		return userPwd.equals(dbPwd);
	}

}
