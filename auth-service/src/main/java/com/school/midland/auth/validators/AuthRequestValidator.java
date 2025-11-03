package com.school.midland.auth.validators;

import com.school.midland.auth.dto.request.LoginRequest;
import com.school.midland.auth.dto.request.RegisterRequest;
import com.school.midland.auth.exception.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AuthRequestValidator {

    // Validate the register request
    public static void validateRegisterRequest(RegisterRequest request) {
        if (request == null) {
            throw new AuthException("Request cannot be null", HttpStatus.BAD_REQUEST);
        }

        if (request.getUsername() == null || request.getUsername().isEmpty()) {
            throw new AuthException("Username is required", HttpStatus.BAD_REQUEST);
        }

        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new AuthException("Email is required", HttpStatus.BAD_REQUEST);
        }

        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new AuthException("Password is required", HttpStatus.BAD_REQUEST);
        }

        if (!isValidEmail(request.getEmail())) {
            throw new AuthException("Invalid email format", HttpStatus.BAD_REQUEST);
        }
    }

    // Validate the login request
    public static void validateLoginRequest(LoginRequest request) {
        if (request == null) {
            throw new AuthException("Request cannot be null", HttpStatus.BAD_REQUEST);
        }

        if (request.getUsername() == null && request.getEmail() == null) {
            throw new AuthException("Username or email is required", HttpStatus.BAD_REQUEST);
        }

        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new AuthException("Password is required", HttpStatus.BAD_REQUEST);
        }
    }

    // Validate email format
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
