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
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/")
	public String home(Locale locale, Model model, Authentication auth) {
		logger.info("Welcome home!");
		if(auth != null) {
			System.out.println("getAuthorities >>> : "+auth.getAuthorities());
			System.out.println("getName >>> : "+auth.getName());
			
			model.addAttribute("userName", auth.getName());
			model.addAttribute("userAuth", auth.getAuthorities());
		}
		
		return "home";
	}
}
