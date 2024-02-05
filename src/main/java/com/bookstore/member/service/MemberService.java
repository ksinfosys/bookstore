package com.bookstore.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.member.dao.MemberDao;
import com.bookstore.member.dto.MemberDto;
import com.bookstore.util.MessageUtils;

@Service
public class MemberService {

	@Autowired
	MessageUtils messageUtils;
	@Autowired
	public MemberDao memberDao;
	
	public int join(MemberDto memberDto) {
		int result = memberDao.join(memberDto);
		return result;
	}
	
	public List<MemberDto> memberList() {
		return memberDao.memberList();
	}

	public int memberIdCheck(String memberId) throws Exception {
		
		return memberDao.memberIdCheck(memberId);
	}

	public String login(String memberId) {

		return memberDao.login(memberId);
	}
	
}
