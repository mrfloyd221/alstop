package com.jsonfloyd.alstop.security.filter;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsonfloyd.alstop.security.model.Account;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
	
	private long expirationTime;
	
	private String secret;
	
	public JwtLoginFilter(AuthenticationManager authenticationManager, long expirationTime, String secret){
		super.setAuthenticationManager(authenticationManager);
		this.expirationTime = expirationTime;
		this.secret = secret;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			Account creds = new ObjectMapper().readValue(req.getInputStream(), Account.class);

			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
					creds.getPassword(), creds.getAuthorities()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		Calendar cd = Calendar.getInstance();
		cd.setTimeInMillis(cd.getTimeInMillis() + expirationTime);
		Date expirationDate = cd.getTime();
		String token = Jwts.builder().setSubject(((Account) auth.getPrincipal()).getUsername())
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
		String body = "{ \"message\": \"success\", \"token\": \"Bearer " + token + "\"}";
		res.addHeader("Authorization", "Bearer " + token);
		res.getWriter().write(body);
		res.getWriter().flush();
		res.getWriter().close();
	}
}
