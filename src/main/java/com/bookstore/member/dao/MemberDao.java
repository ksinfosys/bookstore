package com.bookstore.member.dao;

import java.util.List;

import com.bookstore.member.dto.MemberDto;

public interface MemberDao {

	public int join(MemberDto member);

	public List<MemberDto> memberList();

	public int memberIdCheck(String memberId);

//	public String memberIdCheck(String memberId);

//	public MemberDto memberIdCheck(String memberId);
	
}
