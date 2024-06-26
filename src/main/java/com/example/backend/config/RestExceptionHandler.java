package com.example.backend.config;

import com.example.backend.dtos.ErrorDto;
import com.example.backend.exceptions.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {AppException.class})
    public ResponseEntity<ErrorDto> handleException(AppException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(new ErrorDto(ex.getMessage()));

    }
}
