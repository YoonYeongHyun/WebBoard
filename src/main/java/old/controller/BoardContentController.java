package old.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.yyh.web.board.BoardDTO;
import com.yyh.web.board.impl.BoardDAO;

public class BoardContentController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		System.out.println("글 보기 ");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) pageNum = "1";
		
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		int seq = Integer.parseInt(request.getParameter("seq"));
		dto.setSeq(seq);
		BoardDTO board = boardDAO.getBoard(dto);

		List<BoardDTO> boardList_re = boardDAO.getBoardList(dto);

		int ref = board.getRef();
		int re_step = board.getRe_step();
		int re_level = board.getRe_level();
		String writer = board.getWriter();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNum", pageNum);
		mav.addObject("board", board);
		mav.addObject("boardList_re", boardList_re);
		mav.setViewName("boardContent");
		return mav;
	}
}
