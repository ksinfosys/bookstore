package com.bookstore.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.vo.BookstoreResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ProductApiController {

	@GetMapping("/book")
	public ResponseEntity<BookstoreResponse> book(HttpServletRequest req) throws Exception{
		
		BookstoreResponse res = new BookstoreResponse();
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}
	
	@GetMapping("/ebook")
	public ResponseEntity<BookstoreResponse> ebook(HttpServletRequest req) throws Exception{
		
		BookstoreResponse res = new BookstoreResponse();
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}

	@GetMapping("/goods")
	public ResponseEntity<BookstoreResponse> goods(HttpServletRequest req) throws Exception{
		
		BookstoreResponse res = new BookstoreResponse();
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}

	@GetMapping("/bestseller")
	public ResponseEntity<BookstoreResponse> bestseller(HttpServletRequest req) throws Exception{
		
		BookstoreResponse res = new BookstoreResponse();
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}


}
