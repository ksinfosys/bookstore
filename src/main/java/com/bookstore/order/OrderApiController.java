package com.bookstore.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.vo.BookstoreResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("order")
public class OrderApiController {
	
	@GetMapping({"","/"})
	public ResponseEntity<BookstoreResponse> order(HttpServletRequest req) throws Exception {
		
		String check = req.getParameter("check");
		
		BookstoreResponse res = new BookstoreResponse();
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}
	
	@PostMapping("/shippinginformation")
	public ResponseEntity<BookstoreResponse> shippinginformation(HttpServletRequest req) throws Exception {

		String check = req.getParameter("check");
		
		BookstoreResponse res = new BookstoreResponse();
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}
	
	@PostMapping("/paymentmethod")
	public ResponseEntity<BookstoreResponse> paymentmethod(HttpServletRequest req) throws Exception{

		String check = req.getParameter("check");
		
		BookstoreResponse res = new BookstoreResponse();

		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
		
	}
	
}