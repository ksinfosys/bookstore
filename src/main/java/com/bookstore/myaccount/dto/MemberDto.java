package com.bookstore.myaccount.dto;

import java.sql.Timestamp;

public class MemberDto {
	
    private int       memberIdx;	         // bigserial
    private String    memberName;	         // varchar(100)
    private String    memberEmail;	         // varchar(100)
    private String    memberId;              // varchar(100)
    private String    memberPassword;	     // char(64)
    private String    memberPhone;	         // varchar(100)
    private String    memberBirthday;	     // varchar(100)
    private String    memberGender;	         // char(1)		 DEFAULT '0'
    private String    memberPostalCode;	     // varchar(100)
    private String    memberPostAddress;	 // varchar(200)
    private String    memberDetailedAddress; // varchar(200)
    private int       memberReportCount;	 // integer      DEFAULT '0'
    private String    memberStatus;	      	 // char(1)      DEFAULT '0'
    private String 	  adminFlag;	         // char(1)      DEFAULT '0'
    private Timestamp recCreateDatetime;	 // timestamp    DEFAULT 'NOW'
    private String    recCreateId;	         // char(1)      DEFAULT '0'
    private Timestamp recUpdateDatetime;	 // timestamp    DEFAULT 'NOW'
    private String    recUpdateId;	         // char(1)      DEFAULT '0'
    private String    logicalDelFlag;	     // char(1)      DEFAULT '0'
	
    public int getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
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
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
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
	public int getMemberReportCount() {
		return memberReportCount;
	}
	public void setMemberReportCount(int memberReportCount) {
		this.memberReportCount = memberReportCount;
	}
	public String getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	public String getAdminFlag() {
		return adminFlag;
	}
	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}
	public Timestamp getRecCreateDatetime() {
		return recCreateDatetime;
	}
	public void setRecCreateDatetime(Timestamp recCreateDatetime) {
		this.recCreateDatetime = recCreateDatetime;
	}
	public String getRecCreateId() {
		return recCreateId;
	}
	public void setRecCreateId(String recCreateId) {
		this.recCreateId = recCreateId;
	}
	public Timestamp getRecUpdateDatetime() {
		return recUpdateDatetime;
	}
	public void setRecUpdateDatetime(Timestamp recUpdateDatetime) {
		this.recUpdateDatetime = recUpdateDatetime;
	}
	public String getRecUpdateId() {
		return recUpdateId;
	}
	public void setRecUpdateId(String recUpdateId) {
		this.recUpdateId = recUpdateId;
	}
	public String getLogicalDelFlag() {
		return logicalDelFlag;
	}
	public void setLogicalDelFlag(String logicalDelFlag) {
		this.logicalDelFlag = logicalDelFlag;
	}
		
}
