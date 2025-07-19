package com.school.midland.authservice.service.auth;

import com.school.midland.authservice.dto.request.LoginRequest;
import com.school.midland.authservice.dto.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    public ResponseEntity userSignup(RegisterRequest request);
    public ResponseEntity userSignin(LoginRequest request);
}
