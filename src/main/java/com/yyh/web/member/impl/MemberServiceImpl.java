package com.yyh.web.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.yyh.web.member.MemberDTO;
import com.yyh.web.member.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAOMybatis2 memberDAO;
	
	@Override
	public void insertMember(MemberDTO dto) {
		memberDAO.insertMember(dto);
	}

	@Override
	public void updateMember(MemberDTO dto) {
		memberDAO.updateMember(dto);
	}

	@Override
	public void deleteMember(MemberDTO dto) {
		memberDAO.deleteMember(dto);
	}

	@Override
	public MemberDTO getMember(MemberDTO dto) {
		return memberDAO.getMember(dto);
	}

	@Override
	public int login(MemberDTO dto) {
		
		return memberDAO.login(dto);
	}
}
