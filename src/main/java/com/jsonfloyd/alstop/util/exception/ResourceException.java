package com.jsonfloyd.alstop.util.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class ResourceException extends RuntimeException {


    public ResourceException(String message){
        super(message);

    }
}
