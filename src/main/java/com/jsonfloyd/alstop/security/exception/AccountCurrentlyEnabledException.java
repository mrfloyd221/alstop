package com.jsonfloyd.alstop.security.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class AccountCurrentlyEnabledException extends RuntimeException {
	private static final long serialVersionUID = -6552202484401393598L;
	@Getter private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	public AccountCurrentlyEnabledException(String message){
		super(message);
	}
}
