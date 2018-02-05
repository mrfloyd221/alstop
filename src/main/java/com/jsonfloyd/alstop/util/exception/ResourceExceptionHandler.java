package com.jsonfloyd.alstop.util.exception;

import com.jsonfloyd.alstop.util.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceException.class)
    public ErrorResponse handleResourceException(ResourceException ex){
        log.info(ex.getMessage());
        return new ErrorResponse(ex.getMessage(),  ex.getHttpStatus());
    }
}
