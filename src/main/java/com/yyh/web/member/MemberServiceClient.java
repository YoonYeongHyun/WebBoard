package com.yyh.web.member;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MemberServiceClient {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너를 구동
		AbstractApplicationContext container = new GenericXmlApplicationContext("boardContext.xml");
		
		// 2. 멤버 서비스 객체를 찾음 
		MemberService memberService = (MemberService)container.getBean("memberService");
		
		MemberDTO dto = new MemberDTO();
		// 3-1. 회원 등록 
		/*
		MemberDTO dto = new MemberDTO();
		dto.setId("bbb2222");
		dto.setPassword("1234");
		dto.setName("윤영현");
		dto.setRole("일반사용자");
		memberService.insertMember(dto);
		*/
		// 3-2. 회원정보확인(1건)
		
		dto.setId("admin");
		
		MemberDTO m = memberService.getMember(dto);
		System.out.println(m);
		
		
		// 3-3. 회원 정보 수정
		/*
		dto.setId("aaa1111");
		dto.setPassword("1234");
		dto.setName("김상식");
		dto.setRole("관리자");
		memberService.updateMember(dto);
		*/

		// 3-3. 회원 정보 삭제
		/*
		dto.setId("aaa1111");
		dto.setPassword("1234");
		memberService.deleteMember(dto);
		*/
		// 4. 스프링 컨테이너를 종료
	
	}
}
