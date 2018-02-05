package com.jsonfloyd.alstop.security.exception;

import com.jsonfloyd.alstop.security.model.Account;
import com.jsonfloyd.alstop.util.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class AuthenticationExceptionHandler {
    @ExceptionHandler(AccountCurrentlyEnabledException.class)
    public ErrorResponse handAccountCurrentlyEnabledException(AccountCurrentlyEnabledException ex){
        log.info(ex.getMessage());
        return new ErrorResponse(ex.getMessage(), ex.getHttpStatus());
    }
}
