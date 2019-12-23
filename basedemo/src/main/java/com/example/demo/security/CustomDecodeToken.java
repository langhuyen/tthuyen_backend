package com.example.demo.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class CustomDecodeToken extends OAuth2AuthenticationDetails{

	public CustomDecodeToken(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
