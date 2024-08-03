package com.cb.come_back.Exception_handling;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomExceptionHandle extends RuntimeException{

    private SimpleResponse simpleResponse;
    private HttpStatus httpStatus;

    public CustomExceptionHandle(SimpleResponse simpleResponse, HttpStatus httpStatus) {
        this.simpleResponse = simpleResponse;
        this.httpStatus = httpStatus;
    }
}
