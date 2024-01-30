package com.bookstore.manage.exception;

public class ManageException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;
	
	public ManageException(String message, String code) {
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
