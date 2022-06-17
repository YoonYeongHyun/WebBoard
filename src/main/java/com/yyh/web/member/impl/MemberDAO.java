package com.yyh.web.member.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.yyh.web.board.BoardDTO;
import com.yyh.web.common.JDBCUtil;
import com.yyh.web.member.MemberDTO;

@Repository("memberDAO")
public class MemberDAO {

	private final String MEMBER_INSERT = "insert into member values( ?, ?, ?, ?)";
	private final String MEMBER_UPDATE = "update member set name = ?, role = ? where id = ? and password = ?";
	private final String MEMBER_DELETE = "delete member where id = ? and password = ?";
	private final String MEMBER_GET = "select * from member where id = ?";
	
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public void insertMember(MemberDTO dto) {			
		try {
			System.out.println("=>JDBC로 실행");
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(MEMBER_INSERT);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getRole());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	public void updateMember(MemberDTO dto) {			
		try {
			System.out.println("=>JDBC로 실행");
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(MEMBER_UPDATE);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getRole());
			pstmt.setString(3, dto.getId());
			pstmt.setString(4, dto.getPassword());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	

	public int login(MemberDTO dto) {
		System.out.println("=>JDBC로 실행");
		String sql = "select * from member where id = ?";
		int chk = - 1;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			rs = pstmt.executeQuery();
			if(rs.next()){ // 아이디가 존재
				String dbpwd = rs.getString("password");
				if(dto.getPassword().equals(dbpwd)){//로그인 성공
					chk = 1;
				} else { // 아이디가 존재하지만 비밀번호가 틀릴 때
					chk = 0;
				}
			} else { // 아이디가 존재하지않음
				chk = 2;
			}
		} catch(Exception e){		
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
		return chk;
	}

	public MemberDTO getMember(MemberDTO dto) {
		System.out.println("=>JDBC로 실행");
		MemberDTO member = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareCall(MEMBER_GET);
			pstmt.setString(1, dto.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
				dto.setRole(rs.getString("role"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return dto;	
	}
	

	public void deleteMember(MemberDTO dto) {
		try {
			System.out.println("=>JDBC로 실행");
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(MEMBER_DELETE);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
	}
}
