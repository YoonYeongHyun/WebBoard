package old.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.yyh.web.board.BoardDTO;
import com.yyh.web.board.impl.BoardDAO;


public class BoardWriteContoller implements Controller{
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		System.out.println("글쓰기 처리");
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int ref = Integer.parseInt(request.getParameter("ref"));
		int re_step = Integer.parseInt(request.getParameter("re_step"));
		int re_level = Integer.parseInt(request.getParameter("re_level"));

		BoardDAO boardDAO = new BoardDAO();
		BoardDTO board = new BoardDTO();	
		
		board.setWriter(writer);
		board.setTitle(title);
		board.setContent(content);
		board.setRef(ref);
		board.setRe_step(re_step);
		board.setRe_level(re_level);
		boardDAO.insertBoard(board);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardList");
		return mav;
	}
}
