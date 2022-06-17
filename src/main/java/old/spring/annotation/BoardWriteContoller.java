package old.spring.annotation;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yyh.web.board.BoardDTO;
import com.yyh.web.board.impl.BoardDAO;

/*
 	Command 객체 - 요청한 정보를 해당 메소드의 매개변수의 객체를 생성하여 프로퍼티로 넣어주는 객체 
*/



@Controller
public class BoardWriteContoller{
	
	@RequestMapping(value = "/boardWrite.do")
	public String boardWrite(BoardDTO dto, BoardDAO boardDAO){
		System.out.println("글쓰기 처리");
		boardDAO.insertBoard(dto);
		return "redirect:boardList.do";
		
		
	}
}
