package com.example.demo.myUtile.HandlerException;

import org.springframework.http.HttpStatus;

public abstract class BaseHandlerException extends RuntimeException {

    public BaseHandlerException(String message) {
        super(message);
    }

    public abstract HttpStatus getStatusCode();
}
