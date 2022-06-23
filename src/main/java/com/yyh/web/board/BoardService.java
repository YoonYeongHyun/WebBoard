package com.yyh.web.board;

import java.util.List;

public interface BoardService {

	void insertBoard(BoardDTO dto);
	List<BoardDTO> getBoardList(BoardDTO dto);
	BoardDTO getBoard(BoardDTO dto);
	void updateBoard(BoardDTO dto);
	void deleteBoard(BoardDTO dto);

	void updateBoardCnt(BoardDTO dto);

	public int getBoardMaxseq(BoardDTO board);
	public int getBoardCount(BoardDTO board);
	List<BoardDTO> getBoardListSearch(BoardDTO dto);
}
