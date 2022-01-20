package com.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeCheckAop {

	@Around("@annotation(TimeCheck)")
	public Object logPerf(ProceedingJoinPoint pjp) throws Throwable{
		long before = System.currentTimeMillis();
		Object ret = pjp.proceed();
		System.out.println("메소드 실행 시간 >>> : " + (System.currentTimeMillis() - before));
		
		return ret;
	}
}
