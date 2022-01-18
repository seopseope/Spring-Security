package com.spring.controller.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.service.LoginService;

@RequestMapping("/login")
@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping("/loginPage.do")
	public String loginPage(Model model) {
		return "/login/loginPage";
	}
	
	@RequestMapping("/access_defind_page.do")
	public String authError() {
		return "/error/access_defind_page";
	};
	
	@RequestMapping("/signUpPage.do")
	public String signUpPage(Model model) {
		List<String> authList = new ArrayList<String>();
		authList.add("ROLE_USER");
		authList.add("ROLE_MEMBER");
		authList.add("ROLE_ADMIN");
		
		model.addAttribute("authList", authList);
		
		return "/login/signUpPage";
	}
	
	@RequestMapping("/userIdChk.do")
	@ResponseBody
	public HashMap<String, Object> userIdChk(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			
			if(userId == null || userId == "") {
				resultMap.put("callbackType", "fail");
				resultMap.put("message", "작업 중 오류가 발생하였습니다.");
			}
			
			hashMap = loginService.getUserIdChk(userId);
			
			if(hashMap == null) {
				resultMap.put("callbackType", "success");
				resultMap.put("chkId", "null");
			}else {
				resultMap.put("callbackType", "success");
				resultMap.put("chkId", "notNull");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("callbackType", "fail");
			resultMap.put("message", "작업 중 오류가 발생하였습니다.");
		}
		
		return resultMap;
	}
	
	@RequestMapping("/signUp_ajax.do")
	@ResponseBody
	public HashMap<String, Object> signUp_ajax(@RequestParam Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> signMap = new HashMap<String, Object>();
		
		try {
			
			if(map == null || map.isEmpty()) {
				signMap.put("callbackType", "fail");
				signMap.put("failMessage", "작업에 실패하였습니다.");
			}
			
			signMap.putAll(map);
			
			String userPwd = signMap.get("userPwd").toString();
			System.out.println("사용자 입력 비밀번호 >>> : " + userPwd);
			
			// 비밀번호 암호화(BCryptPasswordEncoder 방법)
			String enPassword = passwordEncoder.encode(signMap.get("userPwd").toString());
			System.out.println("암호화된 비밀번호 >>> : " + enPassword);
			signMap.put("userPwd", enPassword);
			
			int result = loginService.getInsertSignUp(signMap);
			
			if(result > 0) {
				signMap.put("callbackType", "success");
				signMap.put("successMessage", "작업에 성공하였습니다.");
			}else {
				signMap.put("callbackType", "fail");
				signMap.put("failMessage", "작업에 실패하였습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			signMap.put("callbackType", "fail");
			signMap.put("failMessage", "작업에 실패하였습니다.");
		}
		
		return signMap;
	}
}
