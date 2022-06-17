package old.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.yyh.web.board.BoardDTO;
import com.yyh.web.board.impl.BoardDAO;


public class BoardListController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		System.out.println("글 목록 보기");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) pageNum = "1";
		
		BoardDTO dto = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		List<BoardDTO> boardList = boardDAO.getBoardList(dto);
		System.out.println(boardList);
		System.out.println(boardList);
		System.out.println(boardList);
		System.out.println(boardList);
		System.out.println(boardList);
		System.out.println(boardList);
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList);
		mav.setViewName("boardList.jsp");
		
		return mav;
	}

}
