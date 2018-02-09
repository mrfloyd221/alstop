package com.jsonfloyd.alstop.security.exception;

public class TokenNotFoundException extends Exception {
    public TokenNotFoundException() {
        super();
    }

    public TokenNotFoundException(String message) {
        super(message);
    }
}
