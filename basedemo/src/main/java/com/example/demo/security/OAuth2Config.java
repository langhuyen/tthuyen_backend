package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.test.OAuth2ContextConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
@OAuth2ContextConfiguration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter{
	@Value("${config.oauth2.clientid}")
	private String clientid;

	@Value("${config.oauth2.clientSecret}")
	private String clientSecret;

	@Value("${config.oauth2.privateKey}")
	private String privateKey;

	@Value("${config.oauth2.publicKey}")
	private String publicKey;
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
    private JwtConverter customAccessTokenConverter;


	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		clients.inMemory()
				.withClient(clientid)
				.secret(passwordEncoder.encode("loginservice"))
				.scopes("read", "write")
				.authorizedGrantTypes("password","refresh_token")
				.accessTokenValiditySeconds(10000)
				.refreshTokenValiditySeconds(10000);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		
		    endpoints.userDetailsService(userDetailsService);
		endpoints.authenticationManager(authenticationManager)
				.tokenStore(tokenStore()).accessTokenConverter(tokenEnhancer());
	}
	
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(tokenEnhancer());
	}
	
	@Bean 
	public JwtAccessTokenConverter tokenEnhancer() {
		JwtAccessTokenConverter converter=new CustomTokenEnhancer();
		converter.setSigningKey(privateKey);
		converter.setVerifierKey(publicKey);
		converter.setAccessTokenConverter(customAccessTokenConverter);
		return converter;
		
	}

}