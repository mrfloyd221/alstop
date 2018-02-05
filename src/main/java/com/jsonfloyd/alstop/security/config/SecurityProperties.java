package com.jsonfloyd.alstop.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
@ConfigurationProperties(prefix="jwt")
public class SecurityProperties {
	@Getter @Setter private String loginUrl;
	@Getter @Setter private String signUpUrl;
	@Getter @Setter private Token token = new Token();
	public static class Token{
		@Getter @Setter private long expirationTime;
		@Getter @Setter private String secret;
	}
}
