package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.nimbusds.jose.util.Resource;
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{
	
	@Autowired
    private JwtConverter customAccessTokenConverter;
	
	 @Override
	 public void configure(HttpSecurity http) throws Exception{
	 http
	 .authorizeRequests()
	 .antMatchers(HttpMethod.POST, "/user/register")
	 .permitAll()
	 .antMatchers(HttpMethod.POST, "/user/sendTokenEmail")
	 .permitAll()
	 .antMatchers(HttpMethod.POST, "/user/verifyUser")
	 .permitAll()
	 .anyRequest()
	 .authenticated();
	 }

	 
//	 @Override
//	    public void configure(final ResourceServerSecurityConfigurer config) {
//	        config.tokenServices(tokenServices());
//	    }
//
//	    @Bean
//	    public TokenStore tokenStore() {
//	        return new JwtTokenStore(accessTokenConverter());
//	    }
//
//	    @Bean
//	    public JwtAccessTokenConverter accessTokenConverter() {
//	        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//	        converter.setAccessTokenConverter(customAccessTokenConverter);
//
//	        return converter;
//	    }
//
//	    @Bean
//	    @Primary
//	    public DefaultTokenServices tokenServices() {
//	        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//	        defaultTokenServices.setTokenStore(tokenStore());
//	        return defaultTokenServices;
//	    }
}
