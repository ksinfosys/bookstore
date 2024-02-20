package com.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.bookstore.util.MessageUtils;

@Configuration
public class SecurityConfig {
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	
	@Autowired
	private MessageUtils messageUtils;

	public SecurityConfig(
			AuthenticationManagerBuilder authenticationManagerBuilder) {
		this.authenticationManagerBuilder = authenticationManagerBuilder;
	}

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
        http
            .authorizeHttpRequests(authz -> authz
            		.requestMatchers(
            				"/"
            				,"/error"
            				,"/join"
            				,"/test/**"
            				,"/login"
            				,"/removeMemberCookie"
            				,"/myaccount"
            				,"/checkPassword"
            				,"/myaccount/checkpassword"
            				,"/myaccount/updatepassword"
            				,"/myaccount/update"
            				,"/myaccount/delete"
            			    ).permitAll()
            .anyRequest().authenticated());
		http.csrf(csrf -> csrf.ignoringRequestMatchers("/**"));
		return http.build();
	}
}
