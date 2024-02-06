package com.bookstore.member.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bookstore.config.HelpSqlSessionTemplate;
import com.bookstore.member.dto.MemberDto;

@Repository
public class MemberDaoImpl extends HelpSqlSessionTemplate implements MemberDao{
	
	@Override
	public int join(MemberDto memberDto) {
		return getSqlSessionTemplate().insert("com.bookstore.member.dao.MemberDao.join", memberDto);
	}
	
	@Override
	public List<MemberDto> memberList() {
		return getSqlSessionTemplate().selectList("com.bookstore.member.dao.MemberDao.memberList");
	}
	
	@Override
	public int memberIdCheck(String memberId) {
		int dto = getSqlSessionTemplate().selectOne("com.bookstore.member.dao.MemberDao.idCheck", memberId);
		return dto;
	}

	@Override
	public MemberDto login(String memberId) {
		
		return getSqlSessionTemplate().selectOne("com.bookstore.member.dao.MemberDao.login", memberId);
	}

}
