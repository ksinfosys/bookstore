package com.bookstore.product.exception;

public class ProductException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;

	public ProductException(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	public String getErrCode() {
		return code;
	}
	
}
