package old.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.yyh.web.board.BoardDTO;
import com.yyh.web.board.impl.BoardDAO;

public class BoardUpdateContoller implements Controller {
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 수정 처리");
		HttpSession session = request.getSession();
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) pageNum = "1";
		
		BoardDTO board = new BoardDTO();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int seq = Integer.parseInt(request.getParameter("seq"));
		board.setTitle(title);
		board.setContent(content);
		board.setSeq(seq);
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.updateBoard(board);
		session.setAttribute("pageNum", pageNum);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNum", pageNum);
		mav.setViewName("boardList");
		return mav;
	}

}
