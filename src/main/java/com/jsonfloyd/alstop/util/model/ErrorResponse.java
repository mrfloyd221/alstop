package com.jsonfloyd.alstop.util.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class ErrorResponse {
    @Getter @Setter private String message;
    @Getter @Setter private HttpStatus httpStatus;
}
