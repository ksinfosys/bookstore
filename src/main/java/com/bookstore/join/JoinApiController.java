package com.bookstore.join;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.common.constants.ResponseCodes;
import com.bookstore.util.MessageUtils;
import com.bookstore.vo.BookstoreResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class JoinApiController {
	
	@Autowired
	MessageUtils messageUtils;
	
	@PostMapping("/login")
	public ResponseEntity<BookstoreResponse> login(HttpServletRequest req) throws Exception{
		
		BookstoreResponse res = new BookstoreResponse();

		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
		
	}
	
	@PostMapping("/join")
	public ResponseEntity<BookstoreResponse> join(HttpServletRequest req) throws Exception{
		
		BookstoreResponse res = new BookstoreResponse();

		String name = req.getParameter("name");
		String id = req.getParameter("id");
		
		String messages = messageUtils.getMessage("SYI01");
		res.setResultMessage(messages);
		
		res.setResultCode(ResponseCodes.OK.getCode());
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
		
	} 
	
}
