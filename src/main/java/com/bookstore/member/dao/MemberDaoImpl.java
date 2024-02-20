package com.bookstore.member.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bookstore.config.HelpSqlSessionTemplate;
import com.bookstore.member.dto.MemberDto;
import com.bookstore.member.dto.MemberInfoDto;

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

	@Override
	public MemberInfoDto myAccountInformation(String memberId) {
		return getSqlSessionTemplate().selectOne("com.bookstore.member.dao.MemberDao.myAccountInformation", memberId);
	}

	@Override
	public MemberInfoDto myAccountInformationCookie(String memberCookie) {
		return getSqlSessionTemplate().selectOne("com.bookstore.member.dao.MemberDao.myAccountInformationCookie", memberCookie);
	}
	
	@Override
	public int memberPasswordCheck(String memberId, String securePassword) {
		HashMap<String , Object> memberHashMap = new HashMap<String, Object>();
		memberHashMap.put("memberId", memberId);
		memberHashMap.put("securePassword", securePassword);
		return getSqlSessionTemplate().selectOne("com.bookstore.member.dao.MemberDao.memberPasswordCheck", memberHashMap);
	}

	@Override
	public int memberPasswordUpdate(String memberId, String securePasswordChange) {
		HashMap<String, Object> memberHashMap = new HashMap<String, Object>();
		memberHashMap.put("memberId", memberId);
		memberHashMap.put("securePasswordChange", securePasswordChange);
		return getSqlSessionTemplate().update("com.bookstore.member.dao.MemberDao.memberPasswordUpdate", memberHashMap);
	}

	@Override
	public void updateMemberStatus(MemberDto memberDto) {
		getSqlSessionTemplate().update("com.bookstore.member.dao.MemberDao.updateMemberStatus", memberDto);
	}

	@Override
	public MemberDto memberInfo(String memberId) {
		return getSqlSessionTemplate().selectOne("com.bookstore.member.dao.MemberDao.memberInfo", memberId);
	}

	@Override
	public void updateMemberCookie(MemberDto memberDto) {
		getSqlSessionTemplate().update("com.bookstore.member.dao.MemberDao.updateMemberCookie", memberDto);		
	}

	@Override
	public void updateMember(MemberDto memberDto) {
		getSqlSessionTemplate().update("com.bookstore.member.dao.MemberDao.updateMember", memberDto);				
	}

}
