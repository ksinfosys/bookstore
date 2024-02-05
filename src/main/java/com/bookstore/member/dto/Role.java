package com.bookstore.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	USER("ROLE_USER", "member"),
	ADMIN("ROLE_ADMIN", "admin");
	
	private final String key;
	private final String title;
}
