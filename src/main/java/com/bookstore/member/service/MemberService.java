package com.bookstore.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.member.dao.MemberDao;
import com.bookstore.member.dto.MemberDto;
import com.bookstore.member.dto.MemberInfoDto;
import com.bookstore.util.MessageUtils;

@Service
public class MemberService {

	@Autowired
	MessageUtils messageUtils;
	@Autowired
	public MemberDao memberDao;
	
	public int join(MemberDto memberDto) throws Exception {
		int result = memberDao.join(memberDto);
		return result;
	}
	
	public List<MemberDto> memberList() throws Exception {
		return memberDao.memberList();
	}

	public int memberIdCheck(String memberId) throws Exception {
		return memberDao.memberIdCheck(memberId);
	}

	public MemberDto login(String memberId) throws Exception{
		return memberDao.login(memberId);
	}

	public MemberInfoDto myAccountInformation(String memberId) throws Exception{
		return memberDao.myAccountInformation(memberId);
	}

	public MemberInfoDto myAccountInformationCookie(String memberCookie) throws Exception{
		return memberDao.myAccountInformationCookie(memberCookie);
	}

	public int memberPasswordCheck(String memberPassword, String securePassword) throws Exception {
		return memberDao.memberPasswordCheck(memberPassword, securePassword);
	}

	public int memberPasswordUpdate(String memberId, String securePasswordChange) {
		return memberDao.memberPasswordUpdate(memberId, securePasswordChange);
	}

	public void updateMemberStatus(MemberDto memberDto) {
		memberDao.updateMemberStatus(memberDto);		
	}

	public MemberDto memberInfo(String memberId) {
		return memberDao.memberInfo(memberId);
	}

	public void updateMemberCookie(MemberDto memberDto) {
		memberDao.updateMemberCookie(memberDto);
	}

	public void updateMember(MemberDto memberDto) {
		memberDao.updateMember(memberDto);		
	}
	
}
