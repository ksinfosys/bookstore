package com.bookstore.common.exception;

public class BookstoreException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;
	
	public BookstoreException(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}
	
	public BookstoreException(String message, Throwable cause) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	public String getErrcode() {
		return code;
	}
	
}
