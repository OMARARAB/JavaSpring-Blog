package com.cb.come_back.Exception_handling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHaandle {

    @ExceptionHandler(CustomExceptionHandle.class)
    public ResponseEntity<SimpleResponse> customExceptionHandler(CustomExceptionHandle customExceptionHandle) {
        return ResponseEntity.status(customExceptionHandle.getHttpStatus()).body(customExceptionHandle.getSimpleResponse());
    }
}
