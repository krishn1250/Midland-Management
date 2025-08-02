package com.school.midland.commonlib.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class AdminException extends  RuntimeException{
    private final HttpStatus status;

    public AdminException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
