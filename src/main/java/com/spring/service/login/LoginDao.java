package com.spring.service.login;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import com.spring.security.CustomUserDetails;

@Mapper
@Transactional
public interface LoginDao {
	CustomUserDetails getUserInfo(String userId);
	
	int getUserInsert(HashMap<String, Object> hashMap);
	
	HashMap<String, Object> getUserIdChk(String userId);
}
