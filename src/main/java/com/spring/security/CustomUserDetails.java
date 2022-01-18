package com.spring.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// Security의 Value Object 역할을 하는 클래스
@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails{

	private String mem_id;
	private String mem_pw;
	private String mem_nm;
	private String mem_team;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(mem_team));
		
		return auth;
	}

	@Override
	public String getPassword() {
		return mem_pw;
	}

	@Override
	public String getUsername() {
		return mem_id;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getMem_nm() {
		return mem_nm;
	}

	public void setMem_nm(String mem_nm) {
		this.mem_nm = mem_nm;
	}
	
}
