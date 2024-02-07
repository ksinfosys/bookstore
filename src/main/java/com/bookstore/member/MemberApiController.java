package com.bookstore.member;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.common.constants.ResponseCodes;
import com.bookstore.common.vo.BookstoreResponse;
import com.bookstore.member.dto.MemberDto;
import com.bookstore.member.dto.MemberInfoDto;
import com.bookstore.member.exception.MemberException;
import com.bookstore.member.service.MemberService;
import com.bookstore.util.MessageUtils;
import com.bookstore.util.ObjectUtil;
import com.bookstore.util.SecurityUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class MemberApiController {
	
	@Autowired
	MessageUtils messageUtils;
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ResponseEntity<BookstoreResponse> join(HttpServletRequest req
												, @RequestBody Map<String, Object> request) throws Exception{
		
		BookstoreResponse 	  res 		 = new BookstoreResponse();
		MemberDto 			  memberDto  = new MemberDto();	
		
		SecurityUtil security = new SecurityUtil();
		
		int idCheck = 0;

		String memberId 			 = (String)request.getOrDefault("memberId", null);
		String memberName			 = (String)request.getOrDefault("memberName", null);
		String memberEmail 			 = (String)request.getOrDefault("memberEmail", null);
		String memberPassword		 = (String)request.getOrDefault("memberPassword", null);
		String memberPasswordRe		 = (String)request.getOrDefault("memberPasswordRe", null);
		String memberPhone			 = (String)request.getOrDefault("memberPhone", null);
		String memberBirthday		 = (String)request.getOrDefault("memberBirthday", null);
		String memberGender			 = (String)request.getOrDefault("memberGender", null);
		String memberPostalCode		 = (String)request.getOrDefault("memberPostalCode", null);
		String memberPostAddress	 = (String)request.getOrDefault("memberPostAddress", null);
		String memberDetailedAddress = (String)request.getOrDefault("memberDetailedAddress", null);		
						
		try {
			idCheck = memberService.memberIdCheck(memberId);
		} catch (Exception e) {
			String messages = messageUtils.getMessage("MBB01");
			throw new MemberException(messages, "MBB01");
		}
		
		if(idCheck > 0) {
			String messages = messageUtils.getMessage("MBB01");
			throw new MemberException(messages, "MBB01");	
		}
		
		memberDto.setMemberName(memberName);
		memberDto.setMemberEmail(memberEmail);
		memberDto.setMemberId(memberId);
		
		String securePassword = security.encryptSHA256(memberPassword);
		
		if (memberPassword.equals(memberPasswordRe)) {
		    memberDto.setMemberPassword(securePassword);
		} else {
			String messages = messageUtils.getMessage("MBB03");
			throw new MemberException(messages, "MBB03");
		}

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
	public ResponseEntity<BookstoreResponse> login(HttpServletRequest req
												 , @RequestBody Map<String, Object> request) throws Exception{
		BookstoreResponse res = new BookstoreResponse();
		MemberDto memberDto   = new MemberDto();
		String memberId 	  = (String)request.getOrDefault("memberId", null);
		String memberPassword = (String)request.getOrDefault("memberPassword", null);
		String messages;
		SecurityUtil security = new SecurityUtil();
		String securePassword = security.encryptSHA256(memberPassword);
		
		try {
			memberDto = memberService.login(memberId);
		} catch (Exception e) {
			messages = messageUtils.getMessage("MBB08", new Object[] { memberId });
			throw new MemberException(messages, "MBB08");
		}
		
		if(ObjectUtil.isEmpty(memberDto)) {
			messages = messageUtils.getMessage("MBB08", new Object[] { memberId });
			throw new MemberException(messages, "MBB08");
		}
		if(!securePassword.equals(memberDto.getMemberPassword())) {
			messages = messageUtils.getMessage("MBB03");
			throw new MemberException(messages, "MBB03");
		}
		
		res.setResultMessage(messageUtils.getMessage("MBI02"));
		res.setResultCode(ResponseCodes.OK.getCode());

		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<BookstoreResponse> logout(HttpServletRequest req) throws Exception {
		BookstoreResponse res = new BookstoreResponse();
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
		
	}
	
	@PostMapping("/myaccount/checkpassword")
	public ResponseEntity<BookstoreResponse> checkpassword(HttpServletRequest req
														 , @RequestBody Map<String, Object> request) throws Exception {
		BookstoreResponse res = new BookstoreResponse();
		MemberDto memberDto   = new MemberDto();
		String messages;
		SecurityUtil security = new SecurityUtil();

		String memberId 	  = (String)request.getOrDefault("memberId", null);
		String memberPassword = (String)request.getOrDefault("memberPassword", null);
		
		String securePassword = security.encryptSHA256(memberPassword);

		try {
			memberDto = memberService.login(memberId);
		} catch (Exception e) {
			messages = messageUtils.getMessage("MBB08", new Object[] { memberId });
			throw new MemberException(messages, "MBB08");
		}
		
		if(!securePassword.equals(memberDto.getMemberPassword())) {
			messages = messageUtils.getMessage("MBB03");
			throw new MemberException(messages, "MBB03");
		}
		
		res.setResultMessage(messageUtils.getMessage("MBI01"));		
		res.setResultCode(ResponseCodes.OK.getCode());
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}
	
	@PostMapping("/myaccount")
	public ResponseEntity<BookstoreResponse> myaccount(HttpServletRequest req
													 , @RequestBody Map<String, Object> request)throws Exception {
		BookstoreResponse res 		= new BookstoreResponse();
		MemberInfoDto memberInfoDto = new MemberInfoDto();
		
		String memberId = (String)request.getOrDefault("memberId", null);
		
		memberInfoDto = memberService.myAccountInformation(memberId);
		
		res.setResultMessage(messageUtils.getMessage("MBI01"));		
		res.setResultCode(ResponseCodes.OK.getCode());
		res.setResult(memberInfoDto);

		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);	
	}

	@PostMapping("/myaccount/updatepassword")
	public ResponseEntity<BookstoreResponse> updatepassword(HttpServletRequest req
													 , @RequestBody Map<String, Object> request)throws Exception {
		BookstoreResponse res = new BookstoreResponse();
		MemberDto memberDto   = new MemberDto();
		SecurityUtil security = new SecurityUtil();
		String messages;		
		
		String memberId            = (String)request.getOrDefault("memberId", null);
		String memberPassword 	   = (String)request.getOrDefault("memberPassword", null);
		
		String passwordChange 	   = (String)request.getOrDefault("passwordChange", null);
		String passwordChangeCheck = (String)request.getOrDefault("passwordChangeCheck", null);
		
		String securePassword            = security.encryptSHA256(memberPassword);
		String securePasswordChange      = security.encryptSHA256(passwordChange);
		String securePasswordChangeCheck = security.encryptSHA256(passwordChangeCheck);
		
		try {
			memberDto = memberService.login(memberId);
		} catch (Exception e) {
			messages = messageUtils.getMessage("MBB08", new Object[] { memberId });
			throw new MemberException(messages, "MBB08");
		}
		
		if(ObjectUtil.isEmpty(memberDto)) {
			messages = messageUtils.getMessage("MBB08", new Object[] { memberId });
			throw new MemberException(messages, "MBB08");
		}
		if(!securePassword.equals(memberDto.getMemberPassword())) {
			messages = messageUtils.getMessage("MBB03");
			throw new MemberException(messages, "MBB03");
		}
		
		if (!securePasswordChange.equals(securePasswordChangeCheck)) {
		    messages = messageUtils.getMessage("MBB03");
		    throw new MemberException(messages, "MBB03");
		}
		
		try {
		    memberService.memberPasswordUpdate(memberId, securePasswordChange);
		} catch (Exception e) {
		    messages = messageUtils.getMessage("MBB09");
		    throw new MemberException(messages, "MBB09");
		}
		
		res.setResultMessage(messageUtils.getMessage("MBI01"));		
		res.setResultCode(ResponseCodes.OK.getCode());
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);	
	}
	
}
