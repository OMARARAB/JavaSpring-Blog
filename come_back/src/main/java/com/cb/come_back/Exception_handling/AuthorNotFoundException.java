package com.cb.come_back.Exception_handling;

import org.springframework.http.HttpStatus;

public class AuthorNotFoundException extends CustomExceptionHandle{

    public AuthorNotFoundException() {
        super(new SimpleResponse("Author Not Found"), HttpStatus.NOT_FOUND);
    }
}
