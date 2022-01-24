package com.spring.controller.index;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/")
	public String home(Locale locale, Model model, Authentication auth) throws Exception{
		logger.info("Welcome home!");
		if(auth != null) {
			logger.info("getAuthorities >>> : "+auth.getAuthorities());
			logger.info("getName >>> : "+auth.getName());
			// 강제로 에러 생성(에러 테스트 할려고 생성)
//			throw new Exception();
			model.addAttribute("userName", auth.getName());
			model.addAttribute("userAuth", auth.getAuthorities());
		}
		
		return "home";
	}
}
