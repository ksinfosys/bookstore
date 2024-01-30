package com.bookstore.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.common.vo.BookstoreResponse;
import com.bookstore.util.MessageUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CartApiController {
	
	@Autowired
	MessageUtils messageUtils;
	
	@GetMapping("/cart")
	public ResponseEntity<BookstoreResponse> cart(HttpServletRequest req) throws Exception{
		
		BookstoreResponse res = new BookstoreResponse();

		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
		
	}
	
}
