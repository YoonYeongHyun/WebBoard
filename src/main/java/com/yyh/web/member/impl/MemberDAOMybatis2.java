package com.yyh.web.member.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yyh.web.board.BoardDTO;
import com.yyh.web.common.JDBCUtil;
import com.yyh.web.member.MemberDTO;

@Repository("memberDAO")
public class MemberDAOMybatis2 {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertMember(MemberDTO dto){		
		System.out.println("=>Spring JDBC2로 실행");
		mybatis.insert("MEMBER_INSERT", dto);
	}
	
	public void updateMember(MemberDTO dto){		
		System.out.println("=>Spring JDBC2로 실행");
		mybatis.update("MEMBER_UPDATE", dto);
	}

	public int login(MemberDTO dto){
		System.out.println("=>Spring JDBC2로 실행");
		return mybatis.selectOne("MEMBER_LOGIN", dto);
	}
	
	public MemberDTO getMember(MemberDTO dto){
		System.out.println("=>Spring JDBC2로 실행");
		return mybatis.selectOne("MEMBER_GET", dto);
	}
	
	public void deleteMember(MemberDTO dto){		
		System.out.println("=>Spring JDBC2로 실행");
		mybatis.delete("MEMBER_DELETE", dto);
	}
	
	
}