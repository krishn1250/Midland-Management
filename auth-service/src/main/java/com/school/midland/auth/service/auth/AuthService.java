package com.school.midland.auth.service.auth;

import com.school.midland.auth.dto.request.LoginRequest;
import com.school.midland.auth.dto.request.RegisterRequest;
import com.school.midland.auth.dto.response.LoginResponse;
import com.school.midland.auth.dto.response.UserCreationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    public UserCreationResponse userSignup(RegisterRequest request);
    public LoginResponse userSignin(LoginRequest request, String Role);
}
