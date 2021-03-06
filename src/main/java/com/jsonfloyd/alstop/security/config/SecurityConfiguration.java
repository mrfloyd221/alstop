package com.jsonfloyd.alstop.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jsonfloyd.alstop.security.filter.JwtAuthorizationFilter;
import com.jsonfloyd.alstop.security.filter.JwtLoginFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private SecurityProperties properties;
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public JwtLoginFilter getJwtLoginFilter() throws Exception {
		JwtLoginFilter filter = new JwtLoginFilter(authenticationManager(), properties.getToken().getExpirationTime(),
				properties.getToken().getSecret());
		filter.setFilterProcessesUrl(properties.getLoginUrl());
		return filter;
	}

	@Bean
	public SecurityProperties getSecurityProperties(){return new SecurityProperties();}

	@Bean
	public JwtAuthorizationFilter getJwtAuthorizationFilter() throws Exception {
		JwtAuthorizationFilter filter = new JwtAuthorizationFilter(authenticationManager(),
				properties.getToken().getSecret());
		return filter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/confirm", "/reactivate").permitAll()
        .antMatchers(HttpMethod.POST, properties.getLoginUrl(), properties.getSignUpUrl()).permitAll()
		.anyRequest().authenticated()
				.and()
				.addFilter(getJwtLoginFilter())
				.addFilter(getJwtAuthorizationFilter())
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors().disable()
				.csrf().disable();
	}


	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
