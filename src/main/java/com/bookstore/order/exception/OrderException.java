package com.bookstore.order.exception;

public class OrderException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;
	
	public OrderException(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}
	
	public OrderException(String message, Throwable cause) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	public String gettErrocde() {
		return code;
	}
}
