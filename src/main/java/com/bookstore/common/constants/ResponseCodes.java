package com.bookstore.common.constants;

public enum ResponseCodes {

	OK("200"),
	BAD_URL("400"),
	UNAUTHORIZED("401"),
	FORBIDDEN("403"),
	NOT_FOUND("404"),
	INTERNAL_SERVER_ERROR("500");
	
	private String code;
	
	ResponseCodes(String code){
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
