package com.yyh.web.view.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yyh.web.member.MemberDTO;
import com.yyh.web.member.MemberService;

@Controller
public class LoginController{
	
	@Autowired
	private MemberService memberService;
	
	//로그인 폼으로 이동 - get방식
		@GetMapping(value = "/login.do")
		public String loginGET() throws Exception{
			System.out.println("로그인 이동");
			return "redirect:login.jsp";
		}
		
		//로그인 처리 - post방식 
		@PostMapping(value = "/login.do")
		public String handleRequest(HttpServletRequest request, MemberDTO dto) throws Exception{
			System.out.println("로그인 처리");
			int cnt = memberService.login(dto);
			String id = dto.getId();
			System.out.println(dto);
			if(cnt == 1){
				HttpSession session = request.getSession();
				session.setAttribute("memberId", id);
				return "redirect:boardList.do";
			}else {
				return "redirect:/login.do";
			}
		}
	
	@GetMapping(value = "/logout.do")
	public String handleRequest(HttpServletRequest request) {
		System.out.println("로그아웃 처리");
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "login.jsp";
	}
}
