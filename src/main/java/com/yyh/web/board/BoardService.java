package com.yyh.web.board;

import java.util.List;

public interface BoardService {

	void insertBoard(BoardDTO dto);
	List<BoardDTO> getBoardListSearch(BoardDTO dto);
	BoardDTO getBoard(BoardDTO dto);
	void updateBoard(BoardDTO dto);
	void deleteBoard(BoardDTO dto);

	void updateBoardCnt(BoardDTO dto);

	public int getBoardMaxseq(BoardDTO board);
	List<BoardDTO> getBoardList(BoardDTO board);
	public int getBoardCount(BoardDTO board);
	

	public int getPagingBoardCount(PagingDTO paging);
	List<BoardDTO> getPagingBoardList(PagingDTO paging);
}
