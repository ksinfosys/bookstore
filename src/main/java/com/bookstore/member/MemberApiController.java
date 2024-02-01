package com.bookstore.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.common.constants.ResponseCodes;
import com.bookstore.common.vo.BookstoreResponse;
import com.bookstore.member.dto.MemberDto;
import com.bookstore.member.exception.MemberException;
import com.bookstore.member.service.MemberService;
import com.bookstore.util.MessageUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class MemberApiController {
	
	@Autowired
	MessageUtils messageUtils;
	@Autowired
	MemberService memberService;
	
	@PostMapping("/join")
	public ResponseEntity<BookstoreResponse> join(HttpServletRequest req
												, @RequestBody Map<String, Object> request) {
		
		BookstoreResponse res = new BookstoreResponse();
		MemberDto memberDto   = new MemberDto();
		
		int idCheck = 0;		
//		String idCheck = null;
//		MemberDto idCheck = new MemberDto(); 	
		
		List<MemberDto> memberList = memberService.memberList();

		// @RequestBody -> postman (key, value)
		// getOrDefault -> null exception try catch
		// get value from postman
		String memberId 			 = (String)request.getOrDefault("memberId", null);
		String memberName 		  	 = (String)request.getOrDefault("memberName", null);
		String memberEmail 			 = (String)request.getOrDefault("memberEmail", null);
		String memberPassword 		 = (String)request.getOrDefault("memberPassword", null);
		String memberPhone 		  	 = (String)request.getOrDefault("memberPhone", null);
		String memberBirthday 		 = (String)request.getOrDefault("memberBirthday", null);
		String memberGender 		 = (String)request.getOrDefault("memberGender", null);
		String memberPostalCode 	 = (String)request.getOrDefault("memberPostalCode", null);
		String memberPostAddress 	 = (String)request.getOrDefault("memberPostAddress", null);
		String memberDetailedAddress = (String)request.getOrDefault("memberDetailedAddress", null);		
		
		// sql check Exception e 
		try {
			idCheck = memberService.memberIdCheck(memberId);
		} catch (Exception e) {
			String messages = messageUtils.getMessage("MBI06");
			throw new MemberException(messages, "MBI06");
		}
		
		if(idCheck > 0) {
			String messages = messageUtils.getMessage("MBB01");
			throw new MemberException(messages, "MBB01");	
		}
		
		// set value into memberDto
		memberDto.setMemberName(memberName);
		memberDto.setMemberEmail(memberEmail);
		memberDto.setMemberId(memberId);
		memberDto.setMemberPassword(memberPassword);
		memberDto.setMemberPhone(memberPhone);
		memberDto.setMemberBirthday(memberBirthday);
		memberDto.setMemberGender(memberGender);
		memberDto.setMemberPostalCode(memberPostalCode);
		memberDto.setMemberPostAddress(memberPostAddress);
		memberDto.setMemberDetailedAddress(memberDetailedAddress);
				
		int result = memberService.join(memberDto);
		
		res.setResultMessage(messageUtils.getMessage("MBI05"));		
		res.setResultCode(ResponseCodes.OK.getCode());
		res.setResult(result);
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<BookstoreResponse> login(HttpServletRequest req) throws Exception{
		
		BookstoreResponse res = new BookstoreResponse();

		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
		
	}
	
	
}
