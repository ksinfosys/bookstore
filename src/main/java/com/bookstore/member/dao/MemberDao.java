package com.bookstore.member.dao;

import java.util.List;

import com.bookstore.member.dto.MemberDto;
import com.bookstore.member.dto.MemberInfoDto;

public interface MemberDao {

	public int join(MemberDto member);

	public List<MemberDto> memberList();

	public int memberIdCheck(String memberId);

	public MemberDto login(String memberId);

	public MemberInfoDto myAccountInformation(String memberId);

	public MemberInfoDto myAccountInformationCookie(String memberCookie);

	public int memberPasswordCheck(String memberPassword, String securePassword);

	public int memberPasswordUpdate(String memberId, String securePasswordChange);

	public void updateMemberStatus(MemberDto memberDto);

	public MemberDto memberInfo(String memberId);

	public void updateMemberCookie(MemberDto memberDto);

	public void updateMember(MemberDto memberDto);

}
