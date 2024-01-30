package com.bookstore.manage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.common.vo.BookstoreResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("manage")
public class ManageApiController {
	
	@GetMapping("/member")
	public ResponseEntity<BookstoreResponse> managemember(HttpServletRequest req) throws Exception {
		
		String check = req.getParameter("check");
		
		BookstoreResponse res = new BookstoreResponse();
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}
	
	@GetMapping("/book")
	public ResponseEntity<BookstoreResponse> managebook(HttpServletRequest req) throws Exception {

		String check = req.getParameter("check");
		
		BookstoreResponse res = new BookstoreResponse();
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}
	
	@GetMapping("/ebook")
	public ResponseEntity<BookstoreResponse> manageebook(HttpServletRequest req) throws Exception{

		String check = req.getParameter("check");
		
		BookstoreResponse res = new BookstoreResponse();

		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
		
	}

	@GetMapping("/goods")
	public ResponseEntity<BookstoreResponse> managegoods(HttpServletRequest req) throws Exception{

		String check = req.getParameter("check");
		
		BookstoreResponse res = new BookstoreResponse();

		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
		
	}

	@GetMapping("/book/update")
	public ResponseEntity<BookstoreResponse> managebookupdate(HttpServletRequest req) throws Exception {

		String check = req.getParameter("check");
		
		BookstoreResponse res = new BookstoreResponse();
		
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
	}
	
	@GetMapping("/ebook/update")
	public ResponseEntity<BookstoreResponse> manageebookupdate(HttpServletRequest req) throws Exception{

		String check = req.getParameter("check");
		
		BookstoreResponse res = new BookstoreResponse();

		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
		
	}

	@GetMapping("/goods/update")
	public ResponseEntity<BookstoreResponse> managegoodsupdate(HttpServletRequest req) throws Exception{

		String check = req.getParameter("check");
		
		BookstoreResponse res = new BookstoreResponse();

		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.OK);
		
	}
}