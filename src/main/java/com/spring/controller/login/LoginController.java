package com.spring.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.LoginService;

@RequestMapping("/login")
@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/loginPage.do")
	public String loginPage(Model model) {
		return "/login/loginPage";
	}
	
	@RequestMapping("/access_defind_page.do")
	public String authError() {
		return "/error/access_defind_page";
	};
}
