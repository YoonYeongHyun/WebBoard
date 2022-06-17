package old.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.yyh.web.member.MemberDTO;
import com.yyh.web.member.impl.MemberDAO;


public class LoginController implements Controller{
	
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("로그인 처리");
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPassword(password);

		MemberDAO memberDAO = new MemberDAO();
		int cnt = memberDAO.login(dto);
		System.out.println(cnt);
		System.out.println(cnt);
		System.out.println(cnt);
		
		ModelAndView mav = new ModelAndView();
		
		if(cnt == 1){
			HttpSession session = request.getSession();
			session.setAttribute("memberId", id);
			mav.setViewName("boardList");
			return mav;
		}else {
			mav.setViewName("redirect:login.jsp");
			return mav;
		}
	}
}
