package com.example.demo.security;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.example.demo.model.CustomUser;


public class CustomTokenEnhancer extends JwtAccessTokenConverter {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		CustomUser user = (CustomUser) authentication.getPrincipal();
		Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());

		info.put("id", user.getId());

		info.put("mail", user.getEmail());
		info.put("username", user.getUsername());
		info.put("status", user.getStatus());
		DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
		customAccessToken.setAdditionalInformation(info);
		return super.enhance(customAccessToken, authentication);
	}
	
	
//	@Override
//	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//		CustomUser user = (CustomUser) authentication.getPrincipal();
//		Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());
//
//		info.put("id", user.getId());
//
//		info.put("mail", user.getEmail());
//		info.put("status", user.getStatus());
//		DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
//		customAccessToken.setAdditionalInformation(info);
//		return super.enhance(customAccessToken, authentication);
//	}

}
