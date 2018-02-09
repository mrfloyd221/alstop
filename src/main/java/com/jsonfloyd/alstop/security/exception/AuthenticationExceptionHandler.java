package com.jsonfloyd.alstop.security.exception;

import com.jsonfloyd.alstop.util.model.GenericResponse;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class AuthenticationExceptionHandler{
    @ExceptionHandler(AccountCurrentlyEnabledException.class)
    public GenericResponse handAccountCurrentlyEnabledException(AccountCurrentlyEnabledException ex){
        log.info(ex.getMessage());
        GenericResponse response = new GenericResponse();
        response.addField("message", ex.getMessage());
        response.addField("error", "Internal Server Error");
        return response;
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public GenericResponse handleTokenExpiredException(ExpiredJwtException ex){
        log.info(ex.getMessage());
        GenericResponse response = new GenericResponse();
        response.addField("message", ex.getMessage());
        response.addField("error", "Token expired");
        return response;
    }
    @ExceptionHandler(AuthorizationServiceException.class)
    public GenericResponse handleAuthorizationException(ExpiredJwtException ex){
        log.info(ex.getMessage());
        GenericResponse response = new GenericResponse();
        response.addField("message", ex.getMessage());
        return response;
    }
}
