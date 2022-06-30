package com.yyh.web.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AroundAdvice {
	
	
	@Around("PointcutCommon.allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
	 	String methodName = pjp.getSignature().getName(); //비즈니스 메서드 이름
		System.out.println("[BEFORE] " + methodName + "실행");
		StopWatch sw = new StopWatch();
		sw.start();
		Object returnObj = pjp.proceed();
		sw.stop();
		System.out.println("[AFTER] " + methodName + "종료, 메서드 동작시간(ms) : " + sw.getTotalTimeMillis());
		return returnObj;
	}
}
	
	
	/*
	 public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
	 	String method = pjp.getSignature().getName(); //비즈니스 메서드 이름
		System.out.println("[BEFORE] 비즈니스로직 수행 전 발생");
		StopWatch sw = new StopWatch();
		sw.start();
		Object returnObj = pjp.proceed();
		sw.stop();
		System.out.println("[AFTER] 비즈니스로직 수행 후 발생, 메서드 동작시간(ms) : " + sw.getTotalTimeMillis());
		return returnObj;
		//System.out.println("[BEFORE] 비즈니스로직 수행 전 발생");
		//Object returnObj = pjp.proceed();
		//System.out.println("[AFTER] 비즈니스로직 수행 후 발생");
		//return returnObj;
	}
	
}*/
