package com.bookstore.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.common.constants.ResponseCodes;
import com.bookstore.common.vo.BookstoreResponse;
import com.bookstore.member.exception.MemberException;
import com.bookstore.util.MessageUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
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
	    
	    if(status != null) {
	    	int statusCode = Integer.valueOf(status.toString());
	    	
	    	if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	Object obj = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
	        	ServletException ex;
	        	if (obj instanceof ServletException) {
	        		ex = (ServletException) obj;
	    			
	    			if(ex.getCause().getClass().getName().equals(MemberException.class.getName())) {
	    				MemberException e = (MemberException) ex.getCause();
	    				res = convertException(e.getMessage(), e.getErrCode());
	    				
	    				logger.error(ex.getMessage());
	    				return new ResponseEntity<BookstoreResponse>(res, sts);
	    			}
	    		}
	    	}
	    }
	    
		errorMsg = messageUtils.getMessage("SYB01");
		logger.error(errorMsg);
		res.setResult("SYB01");
		res.setResultCode(ResponseCodes.INTERNAL_SERVER_ERROR.getCode());
		res.setResultMessage(errorMsg);
		return new ResponseEntity<BookstoreResponse>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private BookstoreResponse convertException(String errorMsg, String errCode) {
		BookstoreResponse res = new BookstoreResponse();
		res.setResultCode(errCode);
		res.setResultMessage(errorMsg);
		res.setResult("");
		return res;
	}    
}
	    
	       
