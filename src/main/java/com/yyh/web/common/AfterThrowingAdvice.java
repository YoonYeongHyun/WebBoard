package com.yyh.web.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

//@Service
//@Aspect
public class AfterThrowingAdvice {
	

	@Pointcut("execution(* com.yyh.web..*Impl.*(..))")
	public void allPointcut() {} // 이름만 빌려주는 메서드 = 참조 메서드, 포인트컷 메서드
	
	@Pointcut("execution(* com.yyh.web..*Impl.get*(..))")	
	public void getPointcut() {}
	
	//@AfterThrowing(pointcut="allPointcut()", throwing="exceptObj")
	public void exceptionLog(JoinPoint jp, Exception exceptObj){
		String method = jp.getSignature().getName(); //비즈니스 메서드 이름
		
		System.out.println("[예외처리] method : " + method + ", 예외 처리 메시지 : "+ exceptObj.getMessage());
	}
	/*
	public void exceptionLog(JoinPoint jp, Exception exceptObj){
		String method = jp.getSignature().getName(); //비즈니스 메서드 이름
		
		System.out.println("[예외처리] method : " + method + ", 예외 처리 메시지 : "+ exceptObj.getMessage());
	}
	*/
}
