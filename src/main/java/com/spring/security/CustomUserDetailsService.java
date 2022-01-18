package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.service.login.LoginDao;

// DB에서 사용자 정보를 가져 오는 클래스
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private LoginDao loginDao;
	
	// 해당 메소드로 DB에서 유저 정보를 가져온다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUserDetails user = loginDao.getUserInfo(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return user;
	}

}
