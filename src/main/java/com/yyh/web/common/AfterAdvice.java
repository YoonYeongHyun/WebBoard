package com.yyh.web.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

//@Service
//@Aspect
public class AfterAdvice {


	@Pointcut("execution(* com.yyh.web..*Impl.*(..))")
	public void allPointcut() {} // 이름만 빌려주는 메서드 = 참조 메서드, 포인트컷 메서드
	
	@Pointcut("execution(* com.yyh.web..*Impl.get*(..))")	
	public void getPointcut() {}
	
	@After("allPointcut()")
	public void afterLog(JoinPoint jp){
		String method = jp.getSignature().getName(); //비즈니스 메서드 이름
		Object[] args = jp.getArgs(); //비즈니스 메서드 매개변수 
		System.out.println("[사후처리] method : " + method + ", 매개변수 : "+ args[0].toString());
		System.out.println("[사후처리] 비즈니스");
	}
	/*
	public void afterLog(JoinPoint jp, Object returnObj){
		String method = jp.getSignature().getName(); //비즈니스 메서드 이름
		Object[] args = jp.getArgs(); //비즈니스 메서드 매개변수 
		System.out.println("[사후처리] method : " + method + ", 매개변수 : "+ args[0].toString());
		System.out.println("[사후처리] 비즈니스");
	}
	*/
}
