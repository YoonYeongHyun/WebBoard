package old.spring.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yyh.web.board.BoardDTO;
import com.yyh.web.board.impl.BoardDAO;

@Controller
public class BoardUpdateContoller{
	
	@RequestMapping(value = "/boardUpdate.do")
	public ModelAndView boardUpdate(HttpServletRequest request, BoardDTO board, BoardDAO boardDAO, ModelAndView mav) {
		System.out.println("글 수정 처리");
		HttpSession session = request.getSession();
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) pageNum = "1";
		
		boardDAO.updateBoard(board);
		session.setAttribute("pageNum", pageNum);
		
		mav.addObject("pageNum", pageNum);
		mav.setViewName("boardList.do");
		return mav;
	}

}
