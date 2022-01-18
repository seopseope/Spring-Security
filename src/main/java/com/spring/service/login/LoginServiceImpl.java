package com.spring.service.login;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDao loginDao;
	
	@Override
	public HashMap<String, Object> getUserInfo(String userId) {
//		HashMap<String, Object> hashMap = new HashMap<String, Object>();
//		
//		hashMap = loginDao.getUserInfo(userId);
//		
//		return hashMap;
		return null;
	}

}
