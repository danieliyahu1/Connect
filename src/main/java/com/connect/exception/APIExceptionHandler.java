package com.connect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(value = { UserNotFoundException.class })
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(e.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, badRequest);
    }
}