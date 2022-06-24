package com.yyh.web.board;

import com.yyh.web.board.impl.BoardDAOMybatis;

public class BoardServiceClient2 {
	
	public static void main(String[] args) {
		BoardDAOMybatis boardDAO = new BoardDAOMybatis();
		
		BoardDTO dto = new BoardDTO();
		System.out.println(boardDAO.getBoardMaxseq(dto));
		
		
	}
}
