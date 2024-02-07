package com.bookstore.myaccount;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.common.vo.BookstoreResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("myaccount")
public class MyaccountApiController {
						
	@PostMapping("/delete")
	public ResponseEntity<BookstoreResponse> delete(HttpServletRequest req) throws Exception{
		
		BookstoreResponse res = new BookstoreResponse();

		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
		
	} 
	
	
}