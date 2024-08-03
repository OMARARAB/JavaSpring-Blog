package com.cb.come_back.Exception_handling;

import org.springframework.http.HttpStatus;

public class AuthorCannotBeNull extends CustomExceptionHandle{
    public AuthorCannotBeNull() {
        super(new SimpleResponse("Author cannot be null"), HttpStatus.FORBIDDEN);
    }
}
