package com.school.midland.commonlib.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ErrorResponse buildErrorResponse(Exception ex, HttpStatus status, WebRequest request) {
        return new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
    }


    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(AuthException ex, WebRequest request) {
        ErrorResponse error = buildErrorResponse(ex, ex.getStatus(), request);
        return new ResponseEntity<>(error, ex.getStatus());
    }

}
