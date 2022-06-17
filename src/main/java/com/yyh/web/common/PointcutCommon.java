package com.yyh.web.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class PointcutCommon {

	@Pointcut("execution(* com.yyh.web..*Impl.*(..))")
	public void allPointcut() {} // 이름만 빌려주는 메서드 = 참조 메서드, 포인트컷 메서드
	
	@Pointcut("execution(* com.yyh.web..*Impl.get*(..))")	
	public void getPointcut() {}
}
