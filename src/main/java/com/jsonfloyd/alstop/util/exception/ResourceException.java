package com.jsonfloyd.alstop.util.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class ResourceException extends RuntimeException {
    @Getter @Setter private HttpStatus httpStatus;

    public ResourceException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}
