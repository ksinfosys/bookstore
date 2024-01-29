package com.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.vo.BookstoreResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("myaccount")
public class MyaccountApiController {
	
	@PostMapping("/checkpassword")
	public ResponseEntity<BookstoreResponse> checkpassword(HttpServletRequest req) throws Exception {
		
		String password = req.getParameter("password");
		
		BookstoreResponse res = new BookstoreResponse();
		res.setResultMessage("44562345");
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}
	
	@PostMapping({"","/"})
	public ResponseEntity<BookstoreResponse> myaccount(HttpServletRequest req) throws Exception {

		String check = req.getParameter("check");
		
		BookstoreResponse res = new BookstoreResponse();
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}
	
	@PostMapping("/updatepassowrd")
	public ResponseEntity<BookstoreResponse> updatepassowrd(HttpServletRequest req) throws Exception{
		
		BookstoreResponse res = new BookstoreResponse();

		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
		
	}
	
	@PostMapping("/delete")
	public ResponseEntity<BookstoreResponse> delete(HttpServletRequest req) throws Exception{
		
		BookstoreResponse res = new BookstoreResponse();

		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
		
	} 
	
	
}