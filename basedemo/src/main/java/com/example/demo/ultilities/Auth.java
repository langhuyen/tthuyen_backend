package com.example.demo.ultilities;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

@Component
public class Auth {

	public String getAuth(String key) {
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth=ctx.getAuthentication();
		OAuth2Authentication _auth=(OAuth2Authentication) auth;
		Map<String, String> obj=(Map<String, String>)_auth.getUserAuthentication().getDetails();
		return obj.get(key);
	
	}
}
