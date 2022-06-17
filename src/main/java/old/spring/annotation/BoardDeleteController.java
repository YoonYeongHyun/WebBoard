package old.spring.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yyh.web.board.BoardDTO;
import com.yyh.web.board.impl.BoardDAO;

@Controller
public class BoardDeleteController{
	
	@RequestMapping(value = "/boardDelete")
	public ModelAndView handleRequest(HttpServletRequest request, BoardDTO board, BoardDAO boardDAO, ModelAndView mav) {
		System.out.println("글 삭제 처리");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) pageNum = "1";
		
		boardDAO.deleteBoard(board);
		mav.addObject("pageNum", pageNum);
		mav.setViewName("redirect:boardList.do");
		
		return mav;
	}
}
