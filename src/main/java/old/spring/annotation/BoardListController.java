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
public class BoardListController{

	@RequestMapping(value = "/boardList.do")
	public ModelAndView boardWrite(HttpServletRequest request, BoardDAO boardDAO, BoardDTO dto, ModelAndView mav) {
		HttpSession session = request.getSession();
		System.out.println("글 목록 보기");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) pageNum = "1";
		
		List<BoardDTO> boardList = boardDAO.getBoardList(dto);
		System.out.println(boardList);
		mav.addObject("boardList", boardList);
		mav.setViewName("boardList.jsp");
		
		return mav;
	}
}
