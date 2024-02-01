package com.bookstore.member.exception;

public class MemberException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;
	
	public MemberException(String message, String code) {
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
