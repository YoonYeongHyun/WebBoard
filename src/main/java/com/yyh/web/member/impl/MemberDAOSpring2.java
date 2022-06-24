package com.yyh.web.member.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yyh.web.board.BoardDTO;
import com.yyh.web.common.JDBCUtil;
import com.yyh.web.member.MemberDTO;

//@Repository("memberDAO")
public class MemberDAOSpring2 {

	private final String MEMBER_INSERT = "insert into member(id, password, name, email, tel, addrNum, addr1, addr2) "
			+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
	private final String MEMBER_UPDATE = "update member set name = ?, where id = ? and password = ?";
	private final String MEMBER_DELETE = "delete member where id = ? and password = ?";
	private final String MEMBER_GET = "select * from member where id = ?";
	private final String MEMBER_LOGIN = "select count(*) from member where id = ? and password = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insertMember(MemberDTO dto){		
		System.out.println("=>Spring JDBC2로 실행");
		this.jdbcTemplate.update(MEMBER_INSERT, dto.getId(), dto.getPassword(), dto.getName(), dto.getEmail(), dto.getTel(), dto.getAddrNum(), dto.getAddr1(), dto.getAddr2());
	}
	
	public void updateMember(MemberDTO dto){		
		System.out.println("=>Spring JDBC2로 실행");
		this.jdbcTemplate.update(MEMBER_UPDATE, dto.getName(), dto.getId(), dto.getPassword());
	}

	public int login(MemberDTO dto){
		System.out.println("=>Spring JDBC2로 실행");
		return this.jdbcTemplate.update(MEMBER_LOGIN, dto.getId(), dto.getPassword());
	}
	
	public MemberDTO getMember(MemberDTO dto){
		System.out.println("=>Spring JDBC2로 실행");
		Object[] args = {dto.getId(), dto.getPassword()};
		return this.jdbcTemplate.queryForObject(MEMBER_GET, args, new MemberRowMapper());
	}
	
	private class MemberRowMapper implements RowMapper<MemberDTO>{

		@Override
		public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			MemberDTO dto = new MemberDTO();
			dto.setId(rs.getString("id"));
			dto.setPassword(rs.getString("password"));
			dto.setName(rs.getString("name"));
			dto.setEmail(rs.getString("email"));
			dto.setTel(rs.getString("tel"));
			dto.setAddrNum(rs.getString("addrNum"));
			dto.setAddr1(rs.getString("addr1"));
			dto.setAddr2(rs.getString("addr2"));
			return dto;
		}
	}
	
	public void deleteMember(MemberDTO dto){		
		System.out.println("=>Spring JDBC2로 실행");
		this.jdbcTemplate.update(MEMBER_DELETE, dto.getId(), dto.getPassword());
	}
	
	
}