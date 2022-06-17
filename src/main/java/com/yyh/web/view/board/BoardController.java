package com.yyh.web.view.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yyh.web.board.BoardDTO;
import com.yyh.web.board.BoardService;
import com.yyh.web.board.impl.BoardDAO;

@Controller
@SessionAttributes("board")
public class BoardController {
	
/*
@RequestMapping - 클라이언트의 요청 경로를 컨트롤러에서 찾도록 하는 역할
@ModelAttribute - RequestMapping이전에 동작하여 해당 내용을 모델(객체)로 만들어 주는 역할 
@SessionAttributes - 객체를 세션으로 등록하여 사용하도록 하는 역할 
*/
	@Autowired
	private BoardService boardService;
	

	@ModelAttribute("conditionMap") //RequestMapping 이전에 실행
	public Map<String, String> searchConditionMap(){
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "title");
		conditionMap.put("내용", "content");
		conditionMap.put("작성자", "writer");
		
		return conditionMap;
	}
	
	@RequestMapping(value = "/boardList.do")
	public String boardList(HttpServletRequest request, BoardDTO dto, Model model) {
		HttpSession session = request.getSession();
		System.out.println("글 목록 보기 ");
		System.out.println(dto);
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || "null".equals(pageNum)){
			pageNum = "1";
		}
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		if (dto.getSearch_condition() == null) dto.setSearch_condition("title");
		if (dto.getSearch_keyword() == null) dto.setSearch_keyword("");
		if(!dto.getSearch_keyword().equals("")) {
			 boardList = boardService.getBoardListSearch(dto);
		}else {
			boardList = boardService.getBoardList(dto);
		}
			
		System.out.println(boardList);
		model.addAttribute("boardList", boardList);
		
		return "boardList.jsp?pageNum="+pageNum;
	}
	
	@RequestMapping(value = "/boardWrite.do" , method=RequestMethod.GET)
	public String boardWrite(BoardDTO dto, BoardDAO boardDAO){
		System.out.println("글쓰기 입장");
		
		return "boardWrite.jsp";
	}

	@RequestMapping(value = "/boardWrite.do", method=RequestMethod.POST)
	public String boardWritedo(BoardDTO dto, BoardDAO boardDAO){
		System.out.println("글쓰기 처리");
		boardDAO.insertBoard(dto);
		
		return "redirect:boardList.do";
	}
	
	@RequestMapping(value = "/boardContent.do")
	public String boardContent(HttpServletRequest request, BoardDTO board, Model model) {
		HttpSession session = request.getSession();
		System.out.println("글 보기 ");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || "null".equals(pageNum)){
			pageNum = "1";
		}
	
		board = boardService.getBoard(board);
		List<BoardDTO> boardList_re = boardService.getBoardList(board);
		session.setAttribute("board", board);
		session.setAttribute("boardList_re", boardList_re);
		System.out.println(board);
		int ref = board.getRef();
		int re_step = board.getRe_step();
		int re_level = board.getRe_level();
		String writer = board.getWriter();
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("board", board);
		model.addAttribute("boardList_re", boardList_re);
		
		return "boardContent.jsp";
	}

	@RequestMapping(value = "/boardUpdate.do")
	public String boardUpdate(HttpServletRequest request, @ModelAttribute("board") BoardDTO board, Model model) {
		//앞선 BoardDTO의 객체를 전부 받아옴
		
		System.out.println("글 수정 처리");
		HttpSession session = request.getSession();
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || "null".equals(pageNum)){
			pageNum = "1";
		}
		session.setAttribute("pageNum", pageNum);
		
		
		boardService.updateBoard(board);
		System.out.println("작성 정보 : " + board);
		model.addAttribute("pageNum", pageNum);
		
		return "redirect:boardList.do";
	}
	
	@RequestMapping(value = "/boardDelete")
	public String handleRequest(HttpServletRequest request, BoardDTO board, Model model) {
		System.out.println("글 삭제 처리");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || "null".equals(pageNum)){
			pageNum = "1";
		}
		System.out.println("글삭제 페이지넘버 :" + pageNum);
		boardService.deleteBoard(board);
		model.addAttribute("pageNum", pageNum);
		
		return "redirect:boardList.do";
	}
	
}
