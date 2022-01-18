package com.spring.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/guestPage.do")
	public String guestPage() {
		return "/guest";
	}
	
	@RequestMapping("/user/userPage.do")
	public String userPage() {
		return "/user/userPage";
	}
	
	@RequestMapping("/member/memberPage.do")
	public String memberPage() {
		return "/member/memberPage";
	}
	
	@RequestMapping("/admin/adminPage.do")
	public String adminPage() {
		return "/admin/adminPage";
	}
}
