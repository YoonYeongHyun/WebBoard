package com.yyh.web.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yyh.web.board.BoardDTO;
import com.yyh.web.common.JDBCUtil;

//@Repository("boardDAO")
public class BoardDAO {
	
	private final String BOARD_INSERT = "insert into board(seq, writer, title, content) values(board_seq.nextval, ?, ?, ?)";
	private final String BOARD_LIST = "select * from board order by	seq desc";
	private final String BOARD_GET = "select * from board where seq = ?";
	private final String BOARD_UPDATE = "update board set title = ?, content = ?, writer = ? where seq = ?";
	private final String BOARD_DELETE = "delete from board where seq = ?";
	private final String BOARD_COUNT = "select count(*) from board";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public void insertBoard(BoardDTO board) {		

		String sql1 = "select max(seq) from board";
		String sql2 = "update board set re_step = re_step+1 where ref=? and re_step>?";
		String sql3 = "insert into board(seq, writer, title, content, ref, re_step, re_level) values(board_seq.nextval, ?, ?, ?, ?, ?, ?)";
		
		int num = board.getSeq();
		int ref = board.getRef();
		int re_step = board.getRe_step();
		int re_level = board.getRe_level();
		int number = 0;
		
		try {
			System.out.println("=>JDBC로 실행");
			conn = JDBCUtil.getConnection();
			
			//글번호의 최대값 획득
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			
			if(rs.next()) number = rs.getInt(1) +1;
			else number = 1;
			
			//댓글그룹, 댓글수, 댓글깊이 
			if(num != 0) { //댓글인 경우
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				re_step = re_step + 1;
				re_level = re_level + 1;
			}else {// 원글인 경우
				ref = number;
				re_step = 0;
				re_level = 0;
			}
			
			//글 등록 처리
			pstmt = conn.prepareStatement(sql3);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, ref);
			pstmt.setInt(5, re_step);
			pstmt.setInt(6, re_level);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	

	public List<BoardDTO> getBoardList(BoardDTO board) {
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		String sql = "select * from board order by ref desc";
		
		try {
			System.out.println("=>JDBC로 실행");
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//1 단계: 글번호, 제목, 작성자, 작성일, 조회수
			//2단계: board를 boardList에 저장
			while(rs.next()) {
				board = new BoardDTO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setRegDate(rs.getTimestamp("regDate"));
				board.setReadCount(rs.getInt("readCount"));
				board.setRef(rs.getInt("ref"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRe_level(rs.getInt("re_level"));
				boardList.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return boardList;
	}

	public int getBoardCount(BoardDTO board) {
		String sql = "select count(*) from board";
		int cnt = 0;
		try {
			System.out.println("=>JDBC로 실행");
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			rs.next();
			cnt = rs.getInt(1);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return cnt;
		
	}
	
	public BoardDTO getBoard(BoardDTO board) {
		String sql1 = "update board set readCount=readCount+1 where seq = ?";
		String sql2 = "select * from board where seq = ?";
		
		try {
			System.out.println("=>JDBC로 실행");
			conn = JDBCUtil.getConnection();
			//조회수 증가
			pstmt = conn.prepareCall(sql1);
			pstmt.setInt(1, board.getSeq());
			pstmt.executeUpdate();
			
			// 글 상세 보기
			pstmt = conn.prepareCall(sql2);
			pstmt.setInt(1, board.getSeq());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			board.setSeq(rs.getInt("seq"));
			board.setWriter(rs.getString("writer"));
			board.setTitle(rs.getString("title"));
			board.setContent(rs.getString("content"));
			board.setRegDate(rs.getTimestamp("regDate"));
			board.setReadCount(rs.getInt("readCount"));
			board.setRef(rs.getInt("ref"));
			board.setRe_step(rs.getInt("re_step"));
			board.setRe_level(rs.getInt("re_level"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return board;	
	}
	

	public void updateBoard(BoardDTO board) {
		try {
			System.out.println("=>JDBC로 실행");
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_UPDATE);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getWriter());
			pstmt.setInt(4, board.getSeq());
			pstmt.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
	}

	public void deleteBoard(BoardDTO board) {
		try {
			System.out.println("=>JDBC로 실행");
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_DELETE);
			pstmt.setInt(1, board.getSeq());
			pstmt.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
	}
	

	public int getBoardCount() {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_COUNT);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return cnt;
	}
	
}
