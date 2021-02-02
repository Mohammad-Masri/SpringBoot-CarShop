package com.example.demo.myUtile.HandlerException;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseHandlerException
{
    public NotFoundException(String message)
    {
        super(message);
    }

    public HttpStatus getStatusCode()
    {
        return HttpStatus.NOT_FOUND;
    }
}
