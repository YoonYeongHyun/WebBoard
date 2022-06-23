package com.yyh.web.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yyh.web.board.BoardDTO;
import com.yyh.web.board.BoardService;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAOMybatis2 boardDAO;
	
	@Override
	@Transactional("txManager")
	public void insertBoard(BoardDTO dto) {
		boardDAO.insertBoard(dto);
	}

	@Override
	public List<BoardDTO> getBoardList(BoardDTO dto) {
		return boardDAO.getBoardList(dto);
	}

	@Override
	public BoardDTO getBoard(BoardDTO dto) {
		boardDAO.updateBoardCnt(dto);
		return boardDAO.getBoard(dto);
	}

	@Override
	public void updateBoard(BoardDTO dto) {
		boardDAO.updateBoard(dto);
	}

	@Override
	public void deleteBoard(BoardDTO dto) {
		boardDAO.deleteBoard(dto);
	}
	
	@Override
	public void updateBoardCnt(BoardDTO dto) {
		boardDAO.updateBoardCnt(dto);
	}
	
	@Override
	public List<BoardDTO> getBoardListSearch(BoardDTO dto){
		return boardDAO.getBoardListSearch(dto); 
	}

	@Override
	public int getBoardMaxseq(BoardDTO board) {
		return boardDAO.getBoardMaxseq(board);
	}

	@Override
	public int getBoardCount(BoardDTO board) {
		return boardDAO.getBoardCount(board);
	}

}
