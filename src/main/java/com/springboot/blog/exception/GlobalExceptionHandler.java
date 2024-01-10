package com.springboot.blog.exception;

import com.springboot.blog.payload.ErrorDtails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    //Handle specific Exceptions
    // as well as global exception


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDtails> handleResourcNotFoundException(ResourceNotFoundException exception, WebRequest webRequest)
    {
        ErrorDtails errorDtails= new ErrorDtails(new Date(),exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDtails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDtails> handleBlogNotFoundException(BlogApiException exception, WebRequest webRequest)
    {
        ErrorDtails errorDtails= new ErrorDtails(new Date(),exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDtails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDtails> handleGlobalException(Exception exception, WebRequest webRequest)
    {
        ErrorDtails errorDtails= new ErrorDtails(new Date(),exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDtails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
