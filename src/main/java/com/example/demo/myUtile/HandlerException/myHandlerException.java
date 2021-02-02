package com.example.demo.myUtile.HandlerException;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // Sheared by all Controllers and have access to it
public class myHandlerException extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(BaseHandlerException.class)
    public ResponseEntity<ErrorDetiles> handlerException(BaseHandlerException ex , WebRequest request)
    {
        ErrorDetiles errorDetiles = new ErrorDetiles(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetiles,ex.getStatusCode());
    }
}
