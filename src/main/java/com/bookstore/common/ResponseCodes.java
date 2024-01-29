package com.bookstore.common;

public enum ResponseCodes {

	OK("200");
	
	private String code;
	
	ResponseCodes(String code){
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
