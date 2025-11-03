package com.school.midland.admin.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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
    public ResponseEntity<ErrorResponse> handleAuthException(AuthException ex, WebRequest request) {
        ErrorResponse error = buildErrorResponse(ex, ex.getStatus(), request);
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ErrorResponse error = buildErrorResponse(ex, HttpStatus.FORBIDDEN, request);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserException(UserException ex, WebRequest request) {
        ErrorResponse error = buildErrorResponse(ex, ex.getStatus(), request);
        return new ResponseEntity<>(error, ex.getStatus());
    }
    @ExceptionHandler(AdminException.class)
    public ResponseEntity<ErrorResponse> handleAdminException(AdminException ex, WebRequest request) {
        ErrorResponse error = buildErrorResponse(ex, ex.getStatus(), request);
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        ErrorResponse error = buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
