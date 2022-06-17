package old.spring.annotation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yyh.web.board.BoardDTO;
import com.yyh.web.board.impl.BoardDAO;

@Controller
public class BoardContentController {

	@RequestMapping(value = "/boardContent.do")
	public ModelAndView boardContent(HttpServletRequest request, BoardDAO boardDAO, BoardDTO board, ModelAndView mav) {
		HttpSession session = request.getSession();
		System.out.println("글 보기 ");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) pageNum = "1";
		
		board = boardDAO.getBoard(board);
		List<BoardDTO> boardList_re = boardDAO.getBoardList(board);
		session.setAttribute("board", board);
		session.setAttribute("boardList_re", boardList_re);
		int ref = board.getRef();
		int re_step = board.getRe_step();
		int re_level = board.getRe_level();
		String writer = board.getWriter();
		
		mav.addObject("pageNum", pageNum);
		mav.addObject("board", board);
		mav.addObject("boardList_re", boardList_re);
		mav.setViewName("boardContent.jsp");
		return mav;
	}
}
