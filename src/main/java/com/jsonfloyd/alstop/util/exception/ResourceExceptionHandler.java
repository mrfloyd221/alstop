package com.jsonfloyd.alstop.util.exception;

import com.jsonfloyd.alstop.util.model.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponse handleResourceException(ResourceException ex){
        log.info(ex.getMessage());
        return GenericResponse.createSimpleErrorResponse(ex.getMessage());
    }
}
