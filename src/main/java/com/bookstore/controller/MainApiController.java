package com.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.vo.BookstoreResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class MainApiController {
	
	@GetMapping("/main")
	public ResponseEntity<BookstoreResponse> main(HttpServletRequest req) throws Exception{
		
		BookstoreResponse res = new BookstoreResponse();
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}

}
