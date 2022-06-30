package com.yyh.web.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import com.yyh.web.member.MemberDTO;

//@Service
//@Aspect
public class AfterReturingAdvice {

	@Pointcut("execution(* com.yyh.web..*Impl.*(..))")
	public void allPointcut() {} // 이름만 빌려주는 메서드 = 참조 메서드, 포인트컷 메서드
	
	@Pointcut("execution(* com.yyh.web..*Impl.get*(..))")	
	public void getPointcut() {}
	
	//@AfterReturning(pointcut="getPointcut()", returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj){
		String method = jp.getSignature().getName(); //비즈니스 메서드 이름
		if(returnObj instanceof MemberDTO) {
			MemberDTO member = (MemberDTO)returnObj;
			if(member.getId().equals("admin")) {
				System.out.println(member.getName() + "로그인");
			}
		}
		Object[] args = jp.getArgs(); //비즈니스 메서드 매개변수 
		System.out.println("[사후처리] method : " + method + ", 매개변수 : "+returnObj.toString());
	}
	/*
	public void afterLog(JoinPoint jp, Object returnObj){
		String method = jp.getSignature().getName(); //비즈니스 메서드 이름
		if(returnObj instanceof MemberDTO) {
			MemberDTO member = (MemberDTO)returnObj;
			if(member.getId().equals("admin")) {
				System.out.println(member.getName() + "로그인");
			}
		}
		Object[] args = jp.getArgs(); //비즈니스 메서드 매개변수 
		System.out.println("[사후처리] method : " + method + ", 매개변수 : "+returnObj.toString());
	}
	*/
}
