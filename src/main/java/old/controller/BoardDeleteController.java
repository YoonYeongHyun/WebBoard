package old.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.yyh.web.board.BoardDTO;
import com.yyh.web.board.impl.BoardDAO;

public class BoardDeleteController implements Controller {
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 삭제 처리");
		HttpSession session = request.getSession();
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) pageNum = "1";
		
		BoardDTO board = new BoardDTO();
		int seq = Integer.parseInt(request.getParameter("seq"));
		board.setSeq(seq);
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.deleteBoard(board);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNum", pageNum);
		mav.setViewName("boardList");
		
		return mav;
	}
}
