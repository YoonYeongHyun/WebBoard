package com.yyh.web.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {
	public static void main(String[] args) {
		
		//1. Spring 컨테이너를 구동하여 설정파일을 찾음(Look Up)
		AbstractApplicationContext container = new GenericXmlApplicationContext("boardContext.xml");
		
		// 2. boardService 구현체를  생성
		
		BoardService boardService = (BoardService)container.getBean("boardService");
		
		// 3.객체를 생성후, 테이블에 삽입
		
		BoardDTO dto = new BoardDTO();

		dto.setTitle("555555555");
		dto.setWriter("admin");
		dto.setContent("안녕하세요3333333");
		boardService.insertBoard(dto);
		
		// 글전체보기
		/*
		List<BoardDTO> boardList = boardService.getBoardList(dto);
		for(BoardDTO board : boardList) {
			System.out.println(board);
		}*/
		
		
		/*
		dto.setSeq(1);
		BoardDTO board = boardService.getBoard(dto);
		System.out.println(board);
		*/
		/*
		dto.setSeq(1);
		dto.setTitle("변경");
		dto.setContent("변경함");
		boardService.updateBoard(dto);
		*/
		/*
		dto.setSeq(1);	
		boardService.deleteBoard(dto);
		*/
		//컨테이너 종료
		container.close();
	}
}
