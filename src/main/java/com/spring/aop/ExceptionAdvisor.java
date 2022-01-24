package com.spring.aop;

import java.net.BindException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import com.spring.controller.index.HomeController;

@ControllerAdvice
public class ExceptionAdvisor {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<Map<String, String>> exception(Exception ex){
//		Map<String, String> error = new HashMap<String, String>();
//		error.put("code", "ERR5000");
//		error.put("message", ex.getMessage());
//		
//		return ResponseEntity.badRequest().body(error);
//	}
	
	// 기본적인 에러를 처리
	@ExceptionHandler(Exception.class)
	public String basicException(Exception ex, Model model) {
		log.info("Exception >>> : " + ex.getMessage());
		model.addAttribute("exception", ex);
		model.addAttribute("message", "Exception 에러 입니다.");
		
		return "/error/error_test";
	}
	
	// Security 에러 처리
	@ExceptionHandler(SecurityException.class)
	public String securityException(SecurityException ex, Model model) {
		log.info("Security Exception >>>> : " + ex.getMessage());
		model.addAttribute("exception", ex);
		model.addAttribute("message", "Security 에러 입니다.");
		
		return "/error/error_test";
	}
	
	// NullPointException 에러 처리
	@ExceptionHandler(NullPointerException.class)
	public String nullPointerException(NullPointerException ex, Model model) {
		log.info("NullPointer Exception >>>> : " + ex.getMessage());
		model.addAttribute("exception", ex);
		model.addAttribute("message", "NullPointer 에러 입니다.");
		
		return "/error/error_test";
	}
	
	// BindException 에러 처리
	@ExceptionHandler(BindException.class)
	public String bindException(BindException ex, Model model) {
		log.info("Bind Exception >>>> : " + ex.getMessage());
		model.addAttribute("exception", ex);
		model.addAttribute("message", "Bind 에러 입니다.");
		
		return "/error/error_test";
	}
	
	// IllegalArgumentException 에러 처리
	@ExceptionHandler(IllegalArgumentException.class)
	public String illegalArgumentException(IllegalArgumentException ex, Model model) {
		log.info("IllegalArgument Exception >>>> : " + ex.getMessage());
		model.addAttribute("exception", ex);
		model.addAttribute("message", "IllegalArgument 에러 입니다.");
		
		return "/error/error_test";
	}
	
	// HttpClientErrorException 에러 처리
	@ExceptionHandler(HttpClientErrorException.class)
	public String httpClientErrorException(HttpClientErrorException ex, Model model) {
		log.info("HttpClientError Exception >>>> : " + ex.getMessage());
		model.addAttribute("exception", ex);
		model.addAttribute("message", "HttpClientError 에러 입니다.");
		
		return "/error/error_test";
	}
	
	// HttpServerErrorException 에러 처리
	@ExceptionHandler(HttpServerErrorException.class)
	public String httpServerErrorException(HttpServerErrorException ex, Model model) {
		log.info("HttpServerError Exception >>>> : " + ex.getMessage());
		model.addAttribute("exception", ex);
		model.addAttribute("message", "HttpServerError 에러 입니다.");
		
		return "/error/error_test";
	} 
	
	// UnknownHttpStatusCodeException 에러 처리
	@ExceptionHandler(UnknownHttpStatusCodeException.class)
	public String unknownHttpStatusCodeException(UnknownHttpStatusCodeException ex, Model model) {
		log.info("UnknownHttpStatusCode Exception >>>> : " + ex.getMessage());
		model.addAttribute("exception", ex);
		model.addAttribute("message", "UnknownHttpStatusCode 에러 입니다.");
		
		return "/error/error_test";
	}
}
