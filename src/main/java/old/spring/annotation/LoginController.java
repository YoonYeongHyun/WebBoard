package old.spring.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yyh.web.member.MemberDTO;
import com.yyh.web.member.impl.MemberDAO;

@Controller
public class LoginController{
	
	
	//로그인 폼으로 이동 - get방식
	@RequestMapping(value = "/login.do", method=RequestMethod.GET)
	public String loginGET(HttpServletRequest request, MemberDTO dto, MemberDAO memberDAO, ModelAndView mav) throws Exception{
		System.out.println("로그인 이동");
		
		return "login.jsp";
		
	}
	
	//로그인 처리 - post방식 
	@RequestMapping(value = "/login.do", method=RequestMethod.POST)
	public ModelAndView handleRequest(HttpServletRequest request, MemberDTO dto, MemberDAO memberDAO, ModelAndView mav) throws Exception{
		System.out.println("로그인 처리");
		
		
		int cnt = memberDAO.login(dto);
		String id = dto.getId();
		if(cnt == 1){
			HttpSession session = request.getSession();
			session.setAttribute("memberId", id);
			mav.setViewName("/boardList.do");
			return mav;
		}else {
			mav.setViewName("redirect:login.jsp");
			return mav;
		}
	}
}
