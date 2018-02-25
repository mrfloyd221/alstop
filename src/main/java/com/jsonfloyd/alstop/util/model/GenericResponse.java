package com.jsonfloyd.alstop.util.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.integration.dsl.http.Http;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class GenericResponse {
    @Getter @Setter private Map<String, Object> payload;
    @Getter @Setter private HttpStatus httpStatus;
    public GenericResponse(){
        this.payload = new HashMap<>();
        this.httpStatus = HttpStatus.OK;
    }
    public static GenericResponse createSimpleMessageResponse(String message, HttpStatus httpStatus){
        GenericResponse response = new GenericResponse();
        HashMap<String, Object> body = new HashMap<>();
        body.put("message", message);
        response.setHttpStatus(httpStatus);
        response.setPayload(body);
        return  response;
    }

    public static GenericResponse createSimpleErrorResponse(String message){
        GenericResponse response = new GenericResponse();
        HashMap<String, Object> body = new HashMap<>();
        body.put("message", message);
        body.put("error", "Internal Server Error");
        response.setHttpStatus(HttpStatus.BAD_REQUEST);
        response.setPayload(body);
        return  response;
    }
    public void addField(String name, Object value){
        this.payload.put(name, value);
    }

}
