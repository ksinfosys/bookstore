package com.bookstore.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.common.vo.BookstoreResponse;
import com.bookstore.util.MessageUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class GlobalErrorController implements ErrorController {

	@Autowired
	MessageUtils messageUtils;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/error")
	public ResponseEntity<BookstoreResponse> handleError(HttpServletRequest request) { 
		BookstoreResponse res = new BookstoreResponse();
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    String errorMsg ="";
	    HttpStatus sts = HttpStatus.OK;
	    
	    
		return null;
	}    
}
	    
	       
