package com.jsonfloyd.alstop.security.exception;

import com.jsonfloyd.alstop.security.model.Account;
import com.jsonfloyd.alstop.util.model.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthenticationExceptionHandler {
    @ExceptionHandler(AccountCurrentlyEnabledException.class)
    public ErrorResponse handAccountCurrentlyEnabledException(AccountCurrentlyEnabledException ex){
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        response.setHttpStatus(ex.getHttpStatus());
        return response;
    }
}
