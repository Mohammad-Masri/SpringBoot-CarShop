package com.example.demo.myUtile.HandlerException;

import org.springframework.http.HttpStatus;

public class ConflictException extends BaseHandlerException {


    public ConflictException(String message)
    {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
