package com.yyh.web.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yyh.web.board.BoardDTO;

//@Repository("boardDAO")
public class BoardDAOSpring2 {
	
	private final String BOARD_INSERT = "insert into board(seq, writer, title, content, uploadFileName, ref, re_step, re_level) values(board_seq.nextval, ?, ?, ?, ?, ?, 0, 0)";
	private final String BOARD_LIST = "select * from board order by	seq desc";
	private final String BOARD_GET = "select * from board where seq = ?";
	private final String BOARD_UPDATE = "update board set title = ?, content = ? where seq = ?";
	private final String BOARD_UPDATE_CNT = "update board set readCount = readCount + 1 where seq = ?";
	private final String BOARD_DELETE = "delete from board where seq = ?";

	private final String BOARD_GET_MAXSEQ = "select max(seq) from board";
	private final String BOARD_GET_COUNT = "select count(*) from board";
	private final String BOARD_LIST_SEARCH = "select * from board where ? like '%'||?||'%' order by seq desc";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insertBoard(BoardDTO board) {		
		System.out.println("=>Spring JDBC2로 실행");
		System.out.println("------------" + board + "------------------");
		this.jdbcTemplate.update(BOARD_INSERT, board.getWriter(), board.getTitle(), board.getContent(), board.getUploadFileName(), board.getRef());
	}

	public List<BoardDTO> getBoardList(BoardDTO board) {
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		board= null;
		System.out.println("=>Spring JDBC2로 실행");
		return this.jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}
	/*
	public List<BoardDTO> getBoardListSearch(BoardDTO board) {
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		board= null;
		System.out.println("=>Spring JDBC2로 실행");
		return this.jdbcTemplate.query(BOARD_LIST_SEARCH, new BoardRowMapper(), board.getSearch_condition(), board.getSearch_keyword());
	}*/
	
	public int getBoardMaxseq(BoardDTO board) {
		System.out.println("=>Spring JDBC2로 실행");
		return this.jdbcTemplate.queryForObject(BOARD_GET_MAXSEQ, Integer.class);
	}
	
	public int getBoardCount(BoardDTO board) {
		System.out.println("=>Spring JDBC2로 실행");
		return this.jdbcTemplate.queryForObject(BOARD_GET_COUNT, Integer.class);
	}
	
	public BoardDTO getBoard(BoardDTO board) {
		System.out.println("=>Spring JDBC2로 실행");
		Object[] args = {board.getSeq()};
		return this.jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());	
	}
	
	public void updateBoardCnt(BoardDTO board) {
		System.out.println("=>Spring JDBC2로 실행");
		Object[] args = {board.getSeq()};
		this.jdbcTemplate.update(BOARD_UPDATE_CNT, board.getSeq());	
	}
	

	public void updateBoard(BoardDTO board) {
		System.out.println("=>Spring JDBC2로 실행");
		this.jdbcTemplate.update(BOARD_UPDATE, board.getTitle(), board.getContent(), board.getSeq());
	}

	public void deleteBoard(BoardDTO board) {
		System.out.println("=>Spring JDBC2로 실행");
		this.jdbcTemplate.update(BOARD_DELETE, board.getSeq());
	}
	
	//RowMapper 클래스 생성 - 리턴값을 자바객체와 매핑하는 클래스
	private class BoardRowMapper implements RowMapper<BoardDTO>{

		@Override
		public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardDTO board = new BoardDTO();
			board.setSeq(rs.getInt("seq"));
			board.setTitle(rs.getString("title"));
			board.setWriter(rs.getString("writer"));
			board.setContent(rs.getString("content"));
			board.setRegDate(rs.getTimestamp("regDate"));
			board.setReadCount(rs.getInt("readCount"));
			board.setRef(rs.getInt("ref"));
			board.setRe_step(rs.getInt("re_step"));
			board.setRe_level(rs.getInt("re_level"));
			return board;
		}
	}
	
}
