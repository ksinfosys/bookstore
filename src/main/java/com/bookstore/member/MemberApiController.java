package com.bookstore.member;

import java.util.Base64;
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
import com.bookstore.member.dto.MemberInfoDto;
import com.bookstore.member.exception.MemberException;
import com.bookstore.member.service.MemberService;
import com.bookstore.util.MessageUtils;
import com.bookstore.util.ObjectUtil;
import com.bookstore.util.SecurityUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class MemberApiController {
	
	@Autowired
	MessageUtils messageUtils;
	@Autowired
	MemberService memberService;
	
	@PostMapping("/join")
	public ResponseEntity<BookstoreResponse> join(HttpServletRequest req
												, @RequestBody Map<String, Object> request) throws Exception{
		
		BookstoreResponse res = new BookstoreResponse();
        MemberDto memberDto = new MemberDto();
		SecurityUtil security = new SecurityUtil();
		
		int idCheck = 0;

		String memberId = (String)request.getOrDefault("memberId", null);
		String memberName = (String)request.getOrDefault("memberName", null);
		
		String memberEmailId = (String)request.getOrDefault("memberEmailId", null);
		String memberEmailAddress = (String)request.getOrDefault("memberEmailAddress", null);
		
		String memberEmail = (memberEmailId + "@" + memberEmailAddress);
		
		String memberPassword = (String)request.getOrDefault("memberPassword", null);
		String memberPasswordRe = (String)request.getOrDefault("memberPasswordRe", null);
		
		String memberPhone1 = (String)request.getOrDefault("memberPhone1", null);
		String memberPhone2= (String)request.getOrDefault("memberPhone2", null);
		String memberPhone3= (String)request.getOrDefault("memberPhone3", null);
		
		String memberPhone = (memberPhone1 + "-" + memberPhone2 + "-" + memberPhone3) ; 
		
		String memberBirthYear = (String)request.getOrDefault("memberBirthYear", null);
		String memberBirthMonth = (String)request.getOrDefault("memberBirthMonth", null);
		String memberBirthDay = (String)request.getOrDefault("memberBirthDay", null);
		
		String memberBirthday = (memberBirthYear + memberBirthMonth + memberBirthDay);
		
		String memberGender = (String)request.getOrDefault("memberGender", null);
		String memberPostalCode = (String)request.getOrDefault("memberPostalCode", null);
		String memberPostAddress = (String)request.getOrDefault("memberPostAddress", null);
		String memberDetailedAddress = (String)request.getOrDefault("memberDetailedAddress", null);
		
		try {
			idCheck = memberService.memberIdCheck(memberId);
		} catch (Exception e) {
			String messages = messageUtils.getMessage("MBB01", new Object[] { memberId });
			throw new MemberException(messages, "MBB01");
		}
				
		if(idCheck > 0) {
			String messages = messageUtils.getMessage("MBB01", new Object[] { memberId });
			throw new MemberException(messages, "MBB01");	
		}
				
		memberDto.setMemberName(memberName);
		memberDto.setMemberEmail(memberEmail);
		memberDto.setMemberId(memberId);
				
		String securePassword = security.encryptSHA256(memberPassword);
	
		if(!memberPassword.matches("^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$")) {
			String messages = messageUtils.getMessage("MBB15");
			throw new MemberException(messages, "MBB15");		
		}
		
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
	
	@PostMapping("/idCheck")
	public ResponseEntity<BookstoreResponse> idCheck(HttpServletRequest req
												, @RequestBody Map<String, Object> request) throws Exception{
		
		BookstoreResponse res = new BookstoreResponse();
        MemberDto memberDto = new MemberDto();
		
		int idCheck = 0;

		String memberId = (String)request.getOrDefault("memberId", null);
		
		try {
			idCheck = memberService.memberIdCheck(memberId);
		} catch (Exception e) {
			String messages = messageUtils.getMessage("MBB01", new Object[] { memberId });
			throw new MemberException(messages, "MBB01");
		}
		
		if(idCheck > 0) {
			String messages = messageUtils.getMessage("MBB01", new Object[] { memberId });
			throw new MemberException(messages, "MBB01");	
		}
		
		if(!memberId.matches("^[a-zA-Z0-9]*$") || memberId.length() > 16) {
			String messages = messageUtils.getMessage("MBB13");
			throw new MemberException(messages, "MBB13");				
		}

		memberDto.setMemberId(memberId);
		
		res.setResultMessage(messageUtils.getMessage("MBB14"));		
		res.setResultCode(ResponseCodes.OK.getCode());
		res.setResult(memberDto);
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
		
	}
		
	@PostMapping("/login")
	public ResponseEntity<BookstoreResponse> login(HttpServletRequest req
												 , @RequestBody Map<String, Object> request
												 , HttpSession session) throws Exception{
		session = req.getSession();

		BookstoreResponse res = new BookstoreResponse();
		MemberDto memberDtoInfo = new MemberDto();
		MemberDto memberDto = new MemberDto();
		
		String memberId = (String)request.getOrDefault("memberId", null);
		String memberPassword = (String)request.getOrDefault("memberPassword", null);
		
		String messages = messageUtils.getMessage("MBI02");
		SecurityUtil security = new SecurityUtil();
		String securePassword = security.encryptSHA256(memberPassword);
				
		memberDtoInfo = memberService.memberInfo(memberId);
		
		if(memberDtoInfo == null) {
			messages = messageUtils.getMessage("MBB02");
			throw new MemberException(messages, "MBB02");
		}
		
		try {
			memberDto = memberService.login(memberId);
		} catch (Exception e) {
			messages = messageUtils.getMessage("MBB11");
			throw new MemberException(messages, "MBB11");
		}
		
		if(ObjectUtil.isEmpty(memberDtoInfo)) {
			messages = messageUtils.getMessage("MBB11");
			throw new MemberException(messages, "MBB11");
		}
		
		if(!securePassword.equals(memberDto.getMemberPassword())) {
			messages = messageUtils.getMessage("MBB03");
			throw new MemberException(messages, "MBB03");
		}
		
		if(memberDtoInfo.getMemberStatus().equals("1")) {
			messages = messageUtils.getMessage("MBB17");
			throw new MemberException(messages, "MBB17");			
		}
		
		String memberCookieKey = memberDtoInfo.getMemberId() + "book";
		
		String encodedCookie = Base64.getEncoder().encodeToString(memberCookieKey.getBytes());
		
		if(securePassword.equals(memberDto.getMemberPassword())) {
			memberDtoInfo.setMemberCookie(encodedCookie);
			memberDtoInfo.setMemberId(memberId);
			memberDtoInfo.setMemberPassword(securePassword);
			
			memberService.updateMemberCookie(memberDtoInfo);
		}
		
		session.setAttribute("login", 1);
		session.setAttribute("memberId", memberId);
		session.setAttribute("encodedCookie", encodedCookie);

		String savedMemberEncodedCookie = (String) session.getAttribute("encodedCookie");
		int loginStatus = (int) session.getAttribute("login");
		
		if(savedMemberEncodedCookie != encodedCookie){
			messages = messageUtils.getMessage("MBB12");
			throw new MemberException(messages, "MBB12");
		}
		
		res.setResultMessage(messages);res.setResultCode(ResponseCodes.OK.getCode());
		res.setResult(memberDtoInfo);

		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}
	
	//logout
	@PostMapping("/removeMemberCookie")
	public ResponseEntity<BookstoreResponse> logout(HttpServletRequest req
			 									  , @RequestBody Map<String, Object> request
			 									  , HttpSession session) throws Exception{
		
		BookstoreResponse res = new BookstoreResponse();
		
		session = req.getSession();
		session.invalidate();
		
		res.setResultMessage(messageUtils.getMessage("MBI02"));
		res.setResultCode(ResponseCodes.OK.getCode());		
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}
	
	@PostMapping("/checkPassword")
	public ResponseEntity<BookstoreResponse> passwordInsert(HttpServletRequest req
			 											 , @RequestBody Map<String, Object> request
			 											 , HttpSession session) throws Exception {
		session = req.getSession();

		BookstoreResponse res = new BookstoreResponse();
		MemberDto memberDto = new MemberDto();
		MemberInfoDto memberInfoDto = new MemberInfoDto();
		String messages;
		SecurityUtil security = new SecurityUtil();
		
		String memberId = (String)request.getOrDefault("memberId", null);
		String memberCookie = (String)request.getOrDefault("memberCookie", null);
		String memberPassword = (String)request.getOrDefault("memberPassword", null);
		
		String securePassword = security.encryptSHA256(memberPassword);
		
		memberInfoDto = memberService.myAccountInformation(memberId);
		memberDto = memberService.memberInfo(memberId);

		memberDto.getMemberPassword();
		
		System.out.println(memberDto.getMemberPassword());
		
		session.setAttribute("memberPassword", memberDto.getMemberPassword());
		
		String savedMemberPassword = (String) session.getAttribute("memberPassword");
				
		if(!savedMemberPassword.equals(securePassword)) {
			messages = messageUtils.getMessage("MBB03");
			throw new MemberException(messages, "MBB03");
		}
				
		session.setAttribute("memberId", memberId);

		res.setResultMessage(messageUtils.getMessage("MBI01"));		
		res.setResultCode(ResponseCodes.OK.getCode());
		res.setResult(memberDto);
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}
	
	@PostMapping("/myaccount/checkpassword")
	public ResponseEntity<BookstoreResponse> checkpassword(HttpServletRequest req
														 , @RequestBody Map<String, Object> request
														 , HttpSession session) throws Exception {
		
		session = req.getSession();

		BookstoreResponse res = new BookstoreResponse();
		MemberDto memberDto = new MemberDto();
		MemberInfoDto memberInfoDto = new MemberInfoDto();
		String messages;
		SecurityUtil security = new SecurityUtil();
		
		String memberId = (String)request.getOrDefault("memberId", null);
		String memberCookie = (String)request.getOrDefault("memberCookie", null);
		
		memberInfoDto = memberService.myAccountInformation(memberId);
		memberDto = memberService.memberInfo(memberId);

		memberDto.getMemberPassword();
		
		System.out.println(memberDto.getMemberPassword());
		
		session.setAttribute("memberPassword", memberDto.getMemberPassword());
		
		String savedMemberPassword = (String) session.getAttribute("memberPassword");
		
		System.out.println(savedMemberPassword);
						
		res.setResultMessage(messageUtils.getMessage("MBI01"));		
		res.setResultCode(ResponseCodes.OK.getCode());
		res.setResult(memberDto);
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}
	
	@PostMapping("/myaccount")
	public ResponseEntity<BookstoreResponse> myaccount(HttpServletRequest req
													 , @RequestBody Map<String, Object> request
													 , HttpSession session) throws Exception {
		
		session = req.getSession();

		BookstoreResponse res = new BookstoreResponse();
		MemberInfoDto memberInfoDto1 = new MemberInfoDto();
		MemberInfoDto memberInfoDto2 = new MemberInfoDto();
		
		String messages;

		String memberId = (String)request.getOrDefault("memberId", null);
		String memberCookie = (String)request.getOrDefault("memberCookie", null);
		
		memberInfoDto1 = memberService.myAccountInformation(memberId);
		
		memberInfoDto2 = memberService.myAccountInformationCookie(memberCookie);
		
		session.setAttribute("memberId", memberId);		
		session.setAttribute("memberCookie", memberCookie);

		String savedMemberId = (String) session.getAttribute("memberId");
		String savedMemberCookie = (String) session.getAttribute("memberCookie");
		
		System.out.println(savedMemberId);
		System.out.println(savedMemberCookie);
		
		String checkMemberCookie = memberInfoDto1.getMemberCookie();
		
		if(!savedMemberCookie.equals(checkMemberCookie)) {
			messages = messageUtils.getMessage("MBB12");
			throw new MemberException(messages, "MBB12");
		}
		
		res.setResultMessage(messageUtils.getMessage("MBI01"));		
		res.setResultCode(ResponseCodes.OK.getCode());
		res.setResult(memberInfoDto1);

		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);	
	}
	
	@PostMapping("/myaccount/update")
	public ResponseEntity<BookstoreResponse> update(HttpServletRequest req
												  , @RequestBody Map<String, Object> request
												  , HttpSession session)throws Exception {
		session = req.getSession();

		BookstoreResponse res = new BookstoreResponse();
		MemberDto memberDto = new MemberDto();
		SecurityUtil security = new SecurityUtil();
		String messages;
		
		String memberId = (String)request.getOrDefault("memberId", null);
		String memberCookie = (String)request.getOrDefault("memberCookie", null);
		
		String memberEmailId = (String)request.getOrDefault("memberEmailId", null);
		String memberEmailAddress = (String)request.getOrDefault("memberEmailAddress", null);
		
		String memberEmail = (memberEmailId + "@" + memberEmailAddress);
				
		String memberPhone1 = (String)request.getOrDefault("memberPhone1", null);
		String memberPhone2= (String)request.getOrDefault("memberPhone2", null);
		String memberPhone3= (String)request.getOrDefault("memberPhone3", null);
		
		String memberPhone = (memberPhone1 + "-" + memberPhone2 + "-" + memberPhone3) ; 
				
		String memberPostalCode = (String)request.getOrDefault("memberPostalCode", null);
		String memberPostAddress = (String)request.getOrDefault("memberPostAddress", null);
		String memberDetailedAddress = (String)request.getOrDefault("memberDetailedAddress", null);
		
		memberDto.setMemberId(memberId);
		memberDto.setMemberCookie(memberCookie);
		memberDto.setMemberEmail(memberEmail);
		memberDto.setMemberPhone(memberPhone);
		memberDto.setMemberPostalCode(memberPostalCode);
		memberDto.setMemberPostAddress(memberPostAddress);
		memberDto.setMemberDetailedAddress(memberDetailedAddress);
		
		memberService.updateMember(memberDto);

		res.setResultMessage(messageUtils.getMessage("MBB10"));		
		res.setResultCode(ResponseCodes.OK.getCode());		
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);	
	}

	@PostMapping("/myaccount/updatepassword")
	public ResponseEntity<BookstoreResponse> updatepassword(HttpServletRequest req
														  , @RequestBody Map<String, Object> request
														  , HttpSession session) throws Exception {
		
		session = req.getSession();

		BookstoreResponse res = new BookstoreResponse();
		MemberDto memberDto = new MemberDto();
		SecurityUtil security = new SecurityUtil();
		String messages;		
		
		String memberId = (String)request.getOrDefault("memberId", null);
		String memberCookie = (String)request.getOrDefault("memberCookie", null);
		
		String memberPassword = (String)request.getOrDefault("memberPassword", null);
		
		String passwordChange = (String)request.getOrDefault("memberPasswordRe", null);
		String passwordChangeCheck = (String)request.getOrDefault("memberPasswordReNew", null);
		
		String securePassword = security.encryptSHA256(memberPassword);
		String securePasswordChange = security.encryptSHA256(passwordChange);
		String securePasswordChangeCheck = security.encryptSHA256(passwordChangeCheck);
		
		int passwordCheck = 0;
		
		try {
			memberDto = memberService.login(memberId);
		} catch (Exception e) {
			messages = messageUtils.getMessage("MBB11");
			throw new MemberException(messages, "MBB11");
		}
		
		if(ObjectUtil.isEmpty(memberDto)) {
			messages = messageUtils.getMessage("MBB11");
			throw new MemberException(messages, "MBB11");
		}
		
		try {
		    passwordCheck = memberService.memberPasswordCheck(memberId, securePassword);
		} catch (Exception e) {
		    messages = messageUtils.getMessage("MBB03");
		    throw new MemberException(messages, "MBB03");
		}

		if(passwordCheck != 1) {
			messages = messageUtils.getMessage("MBB03");
			throw new MemberException(messages, "MBB03");	
		}
		
		if(!passwordChange.matches("^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$")) {
			messages = messageUtils.getMessage("MBB15");
			throw new MemberException(messages, "MBB15");		
		}

		if(!(securePassword.equals(memberDto.getMemberPassword()) && securePasswordChange.equals(securePasswordChangeCheck))) {
			messages = messageUtils.getMessage("MBB16");
			throw new MemberException(messages, "MBB16");
		}
		
		try {
		    memberService.memberPasswordUpdate(memberId, securePasswordChange);
		} catch (Exception e) {
		    messages = messageUtils.getMessage("MBB09");
		    throw new MemberException(messages, "MBB09");
		}
		
		res.setResultMessage(messageUtils.getMessage("MBI01"));		
		res.setResultCode(ResponseCodes.OK.getCode());
		res.setResult(memberDto);
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);	
	}	
	
	@PostMapping("/myaccount/delete")
	public ResponseEntity<BookstoreResponse> delete(HttpServletRequest req
												  , @RequestBody Map<String, Object> request
												  , HttpSession session)throws Exception {
		
		session = req.getSession();

		BookstoreResponse res = new BookstoreResponse();
		MemberDto memberDto = new MemberDto();
		SecurityUtil security = new SecurityUtil();
		String messages;
				
		String memberId = (String)request.getOrDefault("memberId", null);
		String memberPassword = (String)request.getOrDefault("memberPassword", null);
		String securePassword = security.encryptSHA256(memberPassword); 
		
		try {
			memberDto = memberService.login(memberId);
		} catch (Exception e) {
			messages = messageUtils.getMessage("MBB11");
			throw new MemberException(messages, "MBB11");
		}

		if(!securePassword.equals(memberDto.getMemberPassword())) {
			messages = messageUtils.getMessage("MBB03");
			throw new MemberException(messages, "MBB03");
		}
		if(securePassword.equals(memberDto.getMemberPassword())) {
			memberDto.setMemberStatus("1");
			memberDto.setMemberId(memberId);
			memberDto.setMemberPassword(securePassword);
			
			memberService.updateMemberStatus(memberDto);
			
		}
		
		session.invalidate();
		
		res.setResultMessage(messageUtils.getMessage("MBB10"));		
		res.setResultCode(ResponseCodes.OK.getCode());
		res.setResult(memberDto);
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);	
	}

}
