package com.spring.service;

import java.util.HashMap;

public interface LoginService {
	public HashMap<String, Object> getUserInfo(String userId);
	
	public int getInsertSignUp(HashMap<String, Object> hashMap);
	
	public HashMap<String, Object> getUserIdChk(String userId);
}
