package com.example.demo.security;

import java.util.Map;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import com.example.demo.model.UserModel;


	@Component
	public class JwtConverter extends DefaultAccessTokenConverter {
	 
	    @Override
	    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
	        OAuth2Authentication authentication = super.extractAuthentication(claims);
	        ((AbstractAuthenticationToken)authentication.getUserAuthentication()).setDetails(claims);
	        return authentication;
	    }
	}