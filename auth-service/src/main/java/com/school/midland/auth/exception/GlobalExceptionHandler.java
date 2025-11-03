package com.school.midland.auth.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
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

    // Custom Auth-related exceptions
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponse> handleAuthException(AuthException ex, WebRequest request) {
        log.warn("Auth error: {}", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(
                buildErrorResponse(ex, ex.getStatus(), request)
        );
    }

    // Access denied (role-based)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        log.warn("Access denied: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                buildErrorResponse(ex, HttpStatus.FORBIDDEN, request)
        );
    }

    // Invalid DTO fields (e.g. @Valid errors)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(LocalDateTime.now(), 400, "Bad Request", errors,
                        request.getDescription(false).replace("uri=", ""))
        );
    }

    // Invalid or malformed JSON
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJsonParseError(HttpMessageNotReadableException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request)
        );
    }

    // User not found (used in JwtUserDetailsService)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UsernameNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                buildErrorResponse(ex, HttpStatus.UNAUTHORIZED, request)
        );
    }

    // Database constraint violations (duplicate email/username)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                buildErrorResponse(ex, HttpStatus.CONFLICT, request)
        );
    }

    // JPA validation constraint errors
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        String violations = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + " " + v.getMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(LocalDateTime.now(), 400, "Bad Request", violations,
                        request.getDescription(false).replace("uri=", ""))
        );
    }

    // Generic fallback for unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        log.error("Unhandled exception: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request)
        );
    }
}
