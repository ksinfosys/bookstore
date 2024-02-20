package com.bookstore.member.dto;

public class MemberInfoDto {

    private String    memberName;	         // varchar(100)
    private String    memberEmail;	         // varchar(100)
    private String    memberId;              // varchar(100)
    private String    memberPhone;	         // varchar(100)
    private String    memberBirthday;	     // varchar(100)
    private String    memberGender;	         // char(1)		 DEFAULT '0'
    private String    memberPostalCode;	     // varchar(100)
    private String    memberPostAddress;	 // varchar(200)
    private String    memberDetailedAddress; // varchar(200)
    private String    memberCookie;			 // varchar(64)  DEFAULT '0'
    
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberBirthday() {
		return memberBirthday;
	}
	public void setMemberBirthday(String memberBirthday) {
		this.memberBirthday = memberBirthday;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public String getMemberPostalCode() {
		return memberPostalCode;
	}
	public void setMemberPostalCode(String memberPostalCode) {
		this.memberPostalCode = memberPostalCode;
	}
	public String getMemberPostAddress() {
		return memberPostAddress;
	}
	public void setMemberPostAddress(String memberPostAddress) {
		this.memberPostAddress = memberPostAddress;
	}
	public String getMemberDetailedAddress() {
		return memberDetailedAddress;
	}
	public void setMemberDetailedAddress(String memberDetailedAddress) {
		this.memberDetailedAddress = memberDetailedAddress;
	}
	public String getMemberCookie() {
		return memberCookie;
	}
	public void setMemberCookie(String memberCookie) {
		this.memberCookie = memberCookie;
	}
    
}

