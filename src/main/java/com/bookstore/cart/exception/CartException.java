package com.bookstore.cart.exception;

public class CartException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;
	
	public CartException(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}
	
	public CartException(String message, Throwable couse) {
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
