package com.cb.come_back.Exception_handling;

import org.springframework.http.HttpStatus;

public class BlogNotFoundException extends CustomExceptionHandle{
    public BlogNotFoundException(SimpleResponse simpleResponse, HttpStatus httpStatus) {
        super(new SimpleResponse("Blog Not Found"), HttpStatus.NOT_FOUND);
    }
}
