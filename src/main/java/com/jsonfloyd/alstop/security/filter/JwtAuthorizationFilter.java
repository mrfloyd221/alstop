package com.jsonfloyd.alstop.security.filter;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	
	final private String secret;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, String secret) {
		super(authenticationManager);
		this.secret = secret;

	}


	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

			String header = req.getHeader("Authorization");
			if (header == null || !header.startsWith("Bearer ")) {

				chain.doFilter(req, res);
				return;
			}
			UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

			SecurityContextHolder.getContext().setAuthentication(authentication);

			chain.doFilter(req, res);


	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null)
			return null;
		String user = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token.replace("Bearer " , ""))
                .getBody().getSubject();
		if(user == null)
			return null;
		return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
	}

}
