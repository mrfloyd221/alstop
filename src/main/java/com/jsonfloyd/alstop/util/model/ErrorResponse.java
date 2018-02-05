package com.jsonfloyd.alstop.util.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
public class ErrorResponse {
    @Getter @Setter private String message;
    @Getter @Setter private HttpStatus httpStatus;

}
