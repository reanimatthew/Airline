package com.example.airline.api.advice;

import com.example.airline.dto.ExceptionDTO;
import com.example.airline.exceptions.EntityNotFoundByIdException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class APIAdvice extends ResponseEntityExceptionHandler {

    //TODO переопределить этот метод - он перехватывает constraint!
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundByIdException.class)
    public ExceptionDTO handlerEntityNotFoundByIdException(EntityNotFoundByIdException e, WebRequest webRequest) {
        return ExceptionDTO
                .builder()
                .message(e.getMessage())
                .dateTime(ZonedDateTime.now())
                .type(e.getClass().getSimpleName())
                .path(webRequest.getDescription(true))
                .build();
    }

}
