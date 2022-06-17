package com.yyh.web.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.yyh.web.board.BoardDTO;

//@Repository("boardDAO")
public class BoardDAOSpring extends JdbcDaoSupport{

	private BoardDAOSpring() { }
	
	private static BoardDAOSpring boardDAOSpring = new BoardDAOSpring();

	private final String BOARD_INSERT = "insert into board(seq, writer, title, content) values(board_seq.nextval, ?, ?, ?)";
	private final String BOARD_LIST = "select * from board order by	seq desc";
	private final String BOARD_GET = "select * from board where seq = ?";
	private final String BOARD_UPDATE = "update board set title = ?, content = ? where seq = ?";
	private final String BOARD_DELETE = "delete from board where seq = ?";
	
	@Autowired
	public void setSuperDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	
	public static BoardDAOSpring getInstance() {
		return boardDAOSpring;
	}
	
	public void insertBoard(BoardDTO board) {		
			System.out.println("=>Spring JDBC로 실행");
			this.getJdbcTemplate().update(BOARD_INSERT, board.getWriter(), board.getTitle(), board.getContent());
	}
	

	public List<BoardDTO> getBoardList(BoardDTO board) {
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		board= null;
		System.out.println("=>Spring JDBC로 실행");
		return this.getJdbcTemplate().query(BOARD_LIST, new BoardRowMapper());
	}

	public int getBoardCount(BoardDTO board) {
		System.out.println("=>Spring JDBC로 실행");
		String sql = "select count(*) from board";
		int cnt = 0;
		return cnt;
		
	}
	
	public BoardDTO getBoard(BoardDTO board) {
		System.out.println("=>Spring JDBC로 실행");
		Object[] args = {board.getSeq()};
		return this.getJdbcTemplate().queryForObject(BOARD_GET, args, new BoardRowMapper());	
	}
	

	public void updateBoard(BoardDTO board) {
			System.out.println("=>Spring JDBC로 실행");
			this.getJdbcTemplate().update(BOARD_UPDATE, board.getWriter(), board.getTitle(), board.getSeq());
	}

	public void deleteBoard(BoardDTO board) {
			System.out.println("=>Spring JDBC로 실행");
			this.getJdbcTemplate().update(BOARD_DELETE, board.getSeq());
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
			return board;
		}
	}
	
}
