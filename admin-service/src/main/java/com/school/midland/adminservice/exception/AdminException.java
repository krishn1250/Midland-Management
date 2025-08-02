package com.school.midland.adminservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AdminException extends  RuntimeException{
    private final HttpStatus status;

    public AdminException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
