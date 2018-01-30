package com.jsonfloyd.alstop.security.exception;

public class AccountCurrentlyEnabledException extends RuntimeException {
	private static final long serialVersionUID = -6552202484401393598L;
	public AccountCurrentlyEnabledException(){
		super();
	}
	public AccountCurrentlyEnabledException(String message){
		super(message);
	}
}
