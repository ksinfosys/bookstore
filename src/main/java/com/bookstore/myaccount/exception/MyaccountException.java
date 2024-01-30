package com.bookstore.myaccount.exception;

public class MyaccountException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;
	
	public MyaccountException(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}

	public MyaccountException(String message, Throwable cause) {
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

