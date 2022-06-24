package com.yyh.web.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yyh.web.board.BoardDTO;
import com.yyh.web.util.SqlSessionFactoryBean;

//@Repository("boardDAO")
public class BoardDAOMybatis extends SqlSessionDaoSupport{

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public void insertBoard(BoardDTO board) {		
		System.out.println("=>Spring Mybatis로 실행");
		this.getSqlSession().insert("BoardDAO.BOARD_INSERT", board);
	}

	public List<BoardDTO> getBoardList(BoardDTO board) {
		System.out.println("=>Spring Mybatis로 실행");
		return this.getSqlSession().selectList("BoardDAO.BOARD_LIST", board);
	}
	
	public List<BoardDTO> getBoardListSearch(BoardDTO board) {
		System.out.println("=>Spring Mybatis로 실행");
		return this.getSqlSession().selectList("BoardDAO.BOARD_LIST_SEARCH", board);}
	
	public int getBoardMaxseq(BoardDTO board) {
		System.out.println("=>Spring Mybatis로 실행");
		return this.getSqlSession().selectOne("BoardDAO.BOARD_GET_MAXSEQ", board);
	}
	
	public int getBoardCount(BoardDTO board) {
		System.out.println("=>Spring Mybatis로 실행");
		return this.getSqlSession().selectOne("BoardDAO.BOARD_GET_COUNT", board);
	}
	
	public BoardDTO getBoard(BoardDTO board) {
		System.out.println("=>Spring Mybatis로 실행");
		return this.getSqlSession().selectOne("BoardDAO.BOARD_GET", board);
	}
	
	public void updateBoardCnt(BoardDTO board) {
		System.out.println("=>Spring Mybatis로 실행");
		this.getSqlSession().update("BoardDAO.BOARD_UPDATE_CNT", board);
	}
	

	public void updateBoard(BoardDTO board) {
		System.out.println("=>Spring Mybatis로 실행");
		this.getSqlSession().delete("BoardDAO.BOARD_DELETE", board);
	}

	public void deleteBoard(BoardDTO board) {
		System.out.println("=>Spring Mybatis로 실행");
		this.getSqlSession().delete("BoardDAO.BOARD_DELETE", board);
	}
	
}
