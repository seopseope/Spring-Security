package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

// 사용자가 입력한 아이디 및 비밀번호를 CustomUserDetailsService 클래스가 DB에서 가져온 사용자 정보와 비교하는 클래스이다.
@SuppressWarnings("unused")
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = (String)authentication.getPrincipal();
		String userPwd = (String)authentication.getCredentials();
		
		CustomUserDetails user = (CustomUserDetails)customUserDetailsService.loadUserByUsername(userId);
		System.out.println("사용자 입력 비밀번호 >>> : " + userPwd);
		System.out.println("DB의 암호화된 패스워드 >>> : " + user.getPassword());
		
		// 암호화 하지 않은 비밀번호 비교
//		if(!matchPassword(userPwd, user.getPassword())) {
//			throw new BadCredentialsException(userId);
//		}
		
		// BCryptPasswordEncoder로 암호화된 패스워드는 PasswordEncoder의 함수 matches로 사용자 입력값과 DB의 암호화된 패스워드 값을 비교해야한다.
		// BCryptPasswordEncoder는 암호화는 가능하고 복호화는 불가능하기 떄문에 PasswordEncoder의 matches를 통해서 비밀번호 값을 비교해야 된다.
		if(!passwordEncoder.matches(userPwd, user.getPassword())) {
			throw new BadCredentialsException(userId);
		}
		
		return new UsernamePasswordAuthenticationToken(userId, userPwd, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	// 비밀번호 값 비교
	private boolean matchPassword(String userPwd, String dbPwd) {
		return userPwd.equals(dbPwd);
	}

}
