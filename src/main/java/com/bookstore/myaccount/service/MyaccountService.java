package com.bookstore.myaccount.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.member.dto.MemberDto;
import com.bookstore.myaccount.dao.MyaccountDaoImpl;

public class MyaccountService {

	@Autowired
	public MyaccountDaoImpl myaccountDao;

	public MemberDto myAccountInformation(String memberId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	
}
